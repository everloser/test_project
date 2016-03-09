package com.gmail.everloser12;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParseJSONSimple extends Parse
	{
		Root root = null;
		File salary = null;

		public ParseJSONSimple()
			{
				salary = Manage.download("http://kiparo.ru/t/salary.json", "salary.json");
				JSONParser parser = new JSONParser();
				List<Employees> employeesList = new ArrayList<>();
				Employees employees = null;
				SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
				try
					{
						JSONObject obj = (JSONObject) parser.parse(new FileReader(salary));
						root = new Root();
						String name = (String) obj.get("name");
						root.setName(name);
						String loc = (String) obj.get("location");
						root.setLocation(loc);
						long sal = (long) obj.get("baseSalary");
						root.setBaseSalary(sal);
						JSONArray jsonarray = (JSONArray) obj.get("employees");
						for (int i = 0; i < jsonarray.size(); i++)
							{
								employees = new Employees();
								JSONObject jsonObject = (JSONObject) jsonarray.get(i);
								long id = (long) jsonObject.get("id");
								employees.setId(id);
								String name2 = (String) jsonObject.get("name");
								employees.setName(name2);
								String degree = (String) jsonObject.get("degree");
								employees.setDegree(degree);
								String dateOfBirth = (String) jsonObject.get("dateOfBirth");
								Date date = formats.parse(dateOfBirth);
								employees.setDateOfBirth(date);
								int yearEperience = (int) ((long) jsonObject.get("yearEperience"));
								employees.setYearEperience(yearEperience);
								if (jsonObject.get("rate").getClass() == Long.class)
									{
										double rate = (double) ((long) jsonObject.get("rate"));
										employees.setRate(rate);
									} else
									{
										double rate = (double) jsonObject.get("rate");
										employees.setRate(rate);
									}
								JSONArray emailsarray = (JSONArray) jsonObject.get("emails");
								String[] emails = new String[emailsarray.size()];
								for (int j = 0; j < emailsarray.size(); j++)
									{
										emails[j] = (String) emailsarray.get(j);
									}
								employees.setEmails(emails);
								boolean visible = (Boolean) jsonObject.get("visible");
								employees.setVisible(visible);
								employeesList.add(employees);
							}
						root.setEmployees(employeesList);

					} catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "JSON error = " + e.getMessage());
					}
				salary.delete();
			}

		@Override
		public Root getResult()
			{
				return root;
			}
	}
