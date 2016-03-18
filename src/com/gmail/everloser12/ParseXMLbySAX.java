package com.gmail.everloser12;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * класс скачивает и парсит указанный файл в объект класса Root с помощью SAX парсера
 * @author al-ev
 *
 */
public class ParseXMLbySAX extends Parse
	{

		Root root = null;
		File salary = null;

		public ParseXMLbySAX()
			{
				salary = Manage.download("http://kiparo.ru/t/salary.xml", "salary.xml");
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = null;
				try
					{
						parser = factory.newSAXParser();
					} catch (ParserConfigurationException e1)
					{
						JOptionPane.showMessageDialog(null, "ParserConfiguration error = " + e1.getMessage());
					} catch (SAXException e1)
					{
						JOptionPane.showMessageDialog(null, "SAX error = " + e1.getMessage());
					}
				// Создаем объект класса SAXPars extends DefaultHandler в котором
				// переопределены методы DefaultHandler'а для парсинга нашего файла
				SAXPars saxp = new SAXPars();

				try
					{
						// парсинг файла, используя переопределенные методы
						parser.parse(salary, saxp);
					} catch (SAXException e)
					{
						JOptionPane.showMessageDialog(null, "SAX error = " + e.getMessage());
					} catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "IO error = " + e.getMessage());
					}
				root = saxp.result();
				salary.delete();

			}

		@Override
		public Root getResult()
			{
				// TODO Auto-generated method stub
				return root;
			}

	}

class SAXPars extends DefaultHandler
	{
		Root root = null;
		List<Employees> employeesList = new ArrayList<>();
		Employees employees = null;
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		String thisElement = "";
		StringBuilder email = new StringBuilder();
		String[] emails = null;

		boolean checkemp = false;

		public Root result()
			{
				return root;
			}

		public void startDocument() throws SAXException
			{
				root = new Root();
				// System.out.println("Start parse XML...");
			}

		@Override
		public void endDocument() throws SAXException
			{
				root.setEmployees(employeesList);
				// System.out.println("Stop parse XML...");
			}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
			{
				thisElement = qName;
				if (thisElement.equals("employees"))
					{
						checkemp = true;
					}
			}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException
			{
				thisElement = "";
				if (qName.equals("/employees"))
					{
						checkemp = false;
					}
			}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException
			{
				if (thisElement.equals("name") && !checkemp)
					{
						root.setName(new String(ch, start, length));
					}
				if (thisElement.equals("location"))
					{
						root.setLocation(new String(ch, start, length));
					}
				if (thisElement.equals("baseSalary"))
					{
						root.setBaseSalary(Long.valueOf(new String(ch, start, length)));
					}
				if (thisElement.equals("id"))
					{
						employees = new Employees();
						employees.setId(Long.valueOf(new String(ch, start, length)));
					}
				if (thisElement.equals("name") && checkemp)
					{
						employees.setName(new String(ch, start, length));
					}
				if (thisElement.equals("degree"))
					{
						employees.setDegree(new String(ch, start, length));
					}
				if (thisElement.equals("dateOfBirth"))
					{
						try
							{
								employees.setDateOfBirth(formats.parse(new String(ch, start, length)));
							} catch (ParseException e)
							{
								JOptionPane.showMessageDialog(null, "Parse Date error = " + e.getMessage());
							}
					}
				if (thisElement.equals("yearEperience"))
					{
						employees.setYearEperience(Integer.valueOf(new String(ch, start, length)));
					}
				if (thisElement.equals("rate"))
					{
						employees.setRate(Double.valueOf(new String(ch, start, length)));
					}
				if (thisElement.equals("emails"))
					{

						email.append(new String(ch, start, length));
						email.append("|");
					}
				if (thisElement.equals("visible"))
					{
						email.deleteCharAt(email.length() - 1);
						emails = email.toString().split("\\|");
						employees.setEmails(emails);
						email = new StringBuilder();
						employees.setVisible(Boolean.valueOf(new String(ch, start, length)));
						employeesList.add(employees);

					}

			}

	}
