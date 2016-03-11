package com.gmail.everloser12;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

public class Manage
	{

		private static Manage instance = new Manage();
		private static Root root = null;

		private Manage()
			{
			}

		public static Manage getInstance()
			{
				return instance;
			}

		public static Root getRoot()
			{
				return root;
			}

		public static void setRoot(Root root)
			{
				Manage.root = root;
			}

		public static File download(String string, String filez)
			{
				InputStream inpStrm = null;
				FileOutputStream otpStrm = null;
				File file = new File(filez);
				try
					{
						URL url = new URL(string);
						HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
						int responseCode = httpURLConnection.getResponseCode();
						if (responseCode == HttpURLConnection.HTTP_OK)
							{
								inpStrm = httpURLConnection.getInputStream();

								otpStrm = new FileOutputStream(file);
								int byteRead = -1;
								byte[] buffer = new byte[512];
								while ((byteRead = inpStrm.read(buffer)) != -1)
									{
										otpStrm.write(buffer, 0, byteRead);
									}
							} else
							{
								JOptionPane.showMessageDialog(null, "ERROR in connection = " + responseCode);
							}
					} catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "ERROR in connection = " + e.getMessage());
						
					} finally
					{
						if (inpStrm != null)
						{
						try
							{
								inpStrm.close();
							} catch (Exception e)
							{
								JOptionPane.showMessageDialog(null, "ERROR inputStream close = " + e.getMessage());
							}
						try
							{
								otpStrm.close();
							} catch (Exception e)
							{
								JOptionPane.showMessageDialog(null, "ERROR outputStream close = " + e.getMessage());
							}
						}
					}
				return file;
			}

		public static void parseXML()
			{
				Parse parse = new ParseXMLbySAX();
				root = parse.getResult();
			}

		public static void parseJSONGson()
			{
				Parse parse = new ParseJSONbyGson();
				root = parse.getResult();
			}

		public static void parseJSONSimpl()
			{
				Parse parse = new ParseJSONSimple();
				root = parse.getResult();
			}

		public static void calculate()
			{
				long c = root.getBaseSalary();
				List<Employees> e = root.getEmployees();
				for (Employees em : e)
					{
						em.setRate(em.getRate() * c);
					}
				root.setEmployees(e);
			}

		public static void sortName()
			{
				List<Employees> e = root.getEmployees();
				Collections.sort(e);
				root.setEmployees(e);
			}

		public static void sortSalary()
			{
				List<Employees> e = root.getEmployees();
				Comparator<Employees> comporator = new Comparator<Employees>()
					{
						@Override
						public int compare(Employees o1, Employees o2)
							{
								int result = 0;
								if (o1.getRate() < o2.getRate())
									result = -1;
								if (o1.getRate() > o2.getRate())
									result = 1;
								// int result =
								// ((Double)(o1.getRate())).compareTo((Double)(o2.getRate()));
								return result;
							}
					};
				Collections.sort(e, comporator);
				root.setEmployees(e);
			}
	}
