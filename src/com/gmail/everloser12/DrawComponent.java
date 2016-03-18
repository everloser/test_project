package com.gmail.everloser12;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
/**
 * класс конструирует диаграмму с уровнями зарплат каждого Employees
 * @author al-ev
 *
 */
public class DrawComponent extends JComponent
{
	
		private static final int DEFAULT_WIDTH = 500;
	    private static final int DEFAULT_HEIGHT = 400;

	    private double[] values;
	    private String[] names;
	    private String title = "Salary Rates";
	    
	 
        public DrawComponent()
	    {
	    int n = Manage.getRoot().getEmployees().size();
        // создаем массив чисел с именем values, количество значений n
        values = new double[n];
        // создаем массив строк с именем names, количество значений n
        names = new String[n];
        // заполняем массивы значениями соответствующих параметров
        for (int i = 0; i < n; i++)
        {
           values[i] = Manage.getRoot().getEmployees().get(i).getRate();
           names[i] = Manage.getRoot().getEmployees().get(i).getName();
        }
	    }
	    
	    public void paintComponent(Graphics g)
	    {
	       Graphics2D g2 = (Graphics2D) g;

	       // находим мин и макс значения в массиве values
	       // если массив пустой - выходим из цикла
	       if (values == null) return;
	       double minValue = 0;
	       double maxValue = 0;
	       for (double v : values)
	       {
	          if (minValue > v) minValue = v;
	          if (maxValue < v) maxValue = v;
	       }
	       if (maxValue == minValue) return;

	       // получаем значения размеров компонента
	       int panelWidth = DEFAULT_WIDTH;
	       int panelHeight = DEFAULT_HEIGHT;

	       // обозначаем рабочие шрифты
	       Font titleFont = new Font("SansSerif", Font.BOLD, 20);
	       Font labelFont = new Font("SansSerif", Font.PLAIN, 10);

	       FontRenderContext context = g2.getFontRenderContext();
	       // прямоугольник по размерам строки title
	       Rectangle2D titleBounds = titleFont.getStringBounds(title, context);
	       // величина равная длине строки
	       double titleWidth = titleBounds.getWidth();
	       // величина равная высоте строки
	       double top = titleBounds.getHeight();

	       // рисуем title
	       // минус координата левого верхнего угла прямоугольника
	       double y = -titleBounds.getY(); // ascent
	       // рассчет х, чтобы title был по центру панели
	       double x = (panelWidth - titleWidth) / 2;
	       // устанавливаем шрифт и и пишем строку title
	       g2.setFont(titleFont);
	       g2.drawString(title, (float) x, (float) y);

	       // получаем значение, равное высоте шрифта меток
	       LineMetrics labelMetrics = labelFont.getLineMetrics("", context);
	       double bottom = labelMetrics.getHeight();

	       // значение равно высоте панели минус "величина опускания ниже базовой линии" для labelfont 
	       y = panelHeight - labelMetrics.getDescent();
	       g2.setFont(labelFont);

	       // рассчет коэфициента уменьшения и ширины столбиков
	       // высота панели минус высота title минус высота names (labelfont) делим на разницу значений
	       // рассчет уменьшения, чтобы влезли значения values
	       double scale = (panelHeight - top - bottom) / (maxValue - minValue);
	       // ширина столбика 
	       int barWidth = panelWidth / values.length;

	       // рисуем столбики
	       for (int i = 0; i < values.length; i++)
	       {
	          // координаты прямоугольника
	          double x1 = i * barWidth + 1;
	          double y1 = top;
	          // высота столбика
	          double height = values[i] * scale;
	          // т.к. столбики выровнены по низу, то координата левого верхнего равна top плюс
	          // масштабированная разница между максимумом и данным стодбом
	          if (values[i] >= 0) y1 += (maxValue - values[i]) * scale;
	          // для отрицательных величин
	          else
	          {
	             y1 += maxValue * scale;
	             height = -height;
	          }

	          // заливаем прямоугольник и делаем рамку
	          Rectangle2D rect = new Rectangle2D.Double(x1, y1, barWidth - 2, height);
	          g2.setPaint(Color.RED);
	          g2.fill(rect);
	          g2.setPaint(Color.BLACK);
	          g2.draw(rect);

	          // рисуем метки имен
	          Rectangle2D labelBounds = labelFont.getStringBounds(names[i], context);

	          // длина строки, содержащей данную names
	          double labelWidth = labelBounds.getWidth();
	          // центрируем текст относительно столбика
	          x = x1 + (barWidth - labelWidth) / 2;
	          // пишем строку. у был описан выше
	          g2.drawString(names[i], (float) x, (float) y);
	       }
	    }
	   
	   public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}
