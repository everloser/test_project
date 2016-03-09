package com.gmail.everloser12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParseJSONbyGson extends Parse
	{

		Root root = null;
		File salary = null;

		public ParseJSONbyGson()
			{
				salary = Manage.download("http://kiparo.ru/t/salary.json", "salary.json");
				try
					{
						BufferedReader reader = new BufferedReader(new FileReader(salary));
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
						root = gson.fromJson(reader, Root.class);
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
