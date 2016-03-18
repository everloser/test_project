package com.gmail.everloser12;

import java.util.List;


/**
 * класс хранящий структуру распарсиваемых файлов
 * содержит базовые данные и коллекцию сотрудников
 * @author al-ev
 *
 */
public class Root
	{
		private String name;
		private String location;
		private long baseSalary;
		private List<Employees> employees;
		public String getName()
			{
				return name;
			}
		public void setName(String name)
			{
				this.name = name;
			}
		public String getLocation()
			{
				return location;
			}
		public void setLocation(String location)
			{
				this.location = location;
			}
		public long getBaseSalary()
			{
				return baseSalary;
			}
		public void setBaseSalary(long baseSalary)
			{
				this.baseSalary = baseSalary;
			}
		public List<Employees> getEmployees()
			{
				return employees;
			}
		public void setEmployees(List<Employees> employees)
			{
				this.employees = employees;
			}
		@Override
		public String toString()
			{
				return "name = " + name + ",  location = " + location + ",  baseSalary = " + baseSalary + "\n"+ employees +"\n";
			}
		

	}
