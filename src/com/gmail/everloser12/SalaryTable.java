package com.gmail.everloser12;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class SalaryTable extends AbstractTableModel
	{

	private Root root;
	private List<Employees> empList;
	
		@Override
	public int getColumnCount()
		{
			// TODO Auto-generated method stub
			return 2;
		}

	@Override
	public int getRowCount()
		{
			root = Manage.getRoot();
			if (root != null)
				{
					empList = root.getEmployees();
					return empList.size();
				}
			else return 0;
		}

	@Override
	public Object getValueAt(int r, int c)
		{
			root = Manage.getRoot();
			if (root != null)
				{
					empList = root.getEmployees();
					if (c == 0)
				    	  return empList.get(r).getName();
				    if (c == 1)
				    	  return String.format("%.2f", empList.get(r).getRate()*root.getBaseSalary());
				    else return 0;
				}
			else return 0;
			
			
			
		}
	public String getColumnName(int c)
		   {
		      if (c == 0)
		    	  return " Name ";
		      else
		    	  return " Salary ";
		   }
	

	}
