package com.gmail.everloser12;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * класс описывает сотрудника
 * @author al-ev
 *
 */
public class Employees implements Comparable<Employees>
	{
		private long id;
		private String name;
		private String degree;
		private Date dateOfBirth;
		private int yearEperience;
		private double rate;
		private String[] emails;
		private boolean visible;

		@Override
		public int hashCode()
			{
				final int prime = 31;
				int result = 1;
				result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
				result = prime * result + ((degree == null) ? 0 : degree.hashCode());
				result = prime * result + Arrays.hashCode(emails);
				result = prime * result + (int) (id ^ (id >>> 32));
				result = prime * result + ((name == null) ? 0 : name.hashCode());
				long temp;
				temp = Double.doubleToLongBits(rate);
				result = prime * result + (int) (temp ^ (temp >>> 32));
				result = prime * result + (visible ? 1231 : 1237);
				result = prime * result + yearEperience;
				return result;
			}

		@Override
		public boolean equals(Object obj)
			{
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Employees other = (Employees) obj;
				if (dateOfBirth == null)
					{
						if (other.dateOfBirth != null)
							return false;
					} else if (!dateOfBirth.equals(other.dateOfBirth))
					return false;
				if (degree == null)
					{
						if (other.degree != null)
							return false;
					} else if (!degree.equals(other.degree))
					return false;
				if (!Arrays.equals(emails, other.emails))
					return false;
				if (id != other.id)
					return false;
				if (name == null)
					{
						if (other.name != null)
							return false;
					} else if (!name.equals(other.name))
					return false;
				if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate))
					return false;
				if (visible != other.visible)
					return false;
				if (yearEperience != other.yearEperience)
					return false;
				return true;
			}

		public long getId()
			{
				return id;
			}

		public void setId(long id)
			{
				this.id = id;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public String getDegree()
			{
				return degree;
			}

		public void setDegree(String degree)
			{
				this.degree = degree;
			}

		public Date getDateOfBirth()
			{
				return dateOfBirth;
			}

		public void setDateOfBirth(Date dateOfBirth)
			{
				this.dateOfBirth = dateOfBirth;
			}

		public int getYearEperience()
			{
				return yearEperience;
			}

		public void setYearEperience(int yearEperience)
			{
				this.yearEperience = yearEperience;
			}

		public double getRate()
			{
				return rate;
			}

		public void setRate(double rate)
			{
				this.rate = rate;
			}

		public String[] getEmails()
			{
				return emails;
			}

		public void setEmails(String[] emails)
			{
				this.emails = emails;
			}

		public boolean isVisible()
			{
				return visible;
			}

		public void setVisible(boolean visible)
			{
				this.visible = visible;
			}

		@Override
		public String toString()
			{
				SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
				String textDate = formats.format(dateOfBirth);
				String salary = String.format("%.1f", rate);
				return "\nEmployee id = " + id + "\n\tname = " + name + "\trate = " + salary + "\n\tdegree = " + degree + "\t\tdateOfBirth = " + textDate
						+ "\n\tyearEperience = " + yearEperience + "\tvisible = " + visible + "\n\temails = " + Arrays.toString(emails);
			}

		@Override
		public int compareTo(Employees o)
			{
				int result = name.compareTo(o.getName());
				return result;
			}
		
		
	}
