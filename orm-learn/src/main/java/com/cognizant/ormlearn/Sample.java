package com.cognizant.ormlearn;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;

public class Sample {
	
	private static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public static void main(String[] args)
	{
		Employee employee = new Employee("John", 25000.00, true, parseDate("1979-04-19"));
		Department department = new Department("Analyst Trainee");
		employee.setDepartment(department);	
		System.out.println(employee);
	}

}
