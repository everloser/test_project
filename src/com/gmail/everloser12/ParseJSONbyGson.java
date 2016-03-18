package com.gmail.everloser12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * класс скачивает и парсит указанный файл в объект класса Root с помощью Gson парсера
 * @author al-ev
 *
 */
public class ParseJSONbyGson extends Parse
	{

		Root root = null;
		File salary = null;

		public ParseJSONbyGson()
			{
				salary = Manage.download("http://kiparo.ru/t/salary.json", "salary.json");
				try
					{
						FileReader rsalary = new FileReader(salary);
						BufferedReader reader = new BufferedReader(rsalary);
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
						root = gson.fromJson(reader, Root.class);
						reader.close();
						rsalary.close();
					} catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "GSON error = " + e.getMessage());
					}
				salary.delete();
			}

		@Override
		public Root getResult()
			{
				return root;
			}

	}
