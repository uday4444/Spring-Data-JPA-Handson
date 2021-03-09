package com.cognizant.ormlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OrmLearnApplication {

	private static CountryService countryService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	public static void main(String[] args) {
		// SpringApplication.run(OrmLearnApplication.class, args);
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		LOGGER.info("Inside main");

		/*
		 * Invoking testAddCountry method
		 */
		// testGetAllCountries();
		/*
		 * Invoking getAllCountriesTest method
		 */

		// getAllCountriesTest();

		/*
		 * Invoking testAddCountry method
		 */
		// testAddCountry();

		/*
		 * Invoking testUpdateCountry method
		 */
		// testUpdateCountry();

		/*
		 * Invoking testDeleteCountry method
		 */
		// testDeleteCountry();

		
		
		
		// testFindByNameContaining();
		// testFindByNameStartingWith();
		// testAddEmployee();
		// testGetEmployee();
		// testUpdateEmployee();
		// testGetDepartment();
		// testAddSkillToEmployee();
		// testGetAllPermanentEmployees();
		// testGetAverageSalary();
		// testGetAverageSalaryByDepartmentId();
		// testGetAllEmployeesNative();

	}

	/*
	 * This method displays all the countries that are available in DataBase Invokes
	 * the getAllCountries method from CountryService
	 */
	private static void testGetAllCountries() {

		LOGGER.info("Start");

		List<Country> countries = countryService.getAllCountries();

		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");

	}

	/*
	 * This method displays the country which matches with given country code
	 * Invokes the findCountryByCode method from CountryService
	 * 
	 * @param 'IN' is a country code to find If country is not found, User Defined
	 * exception CountryNotFoundException is raised
	 */
	private static void getAllCountriesTest() {

		LOGGER.info("Started testing getallCountriestest method");

		Country country = null;
		try {
			country = countryService.findCountryByCode("IN");
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End of testing getallCountriestest method");

	}

	/*
	 * This method displays the newly Added country By using setters add the new
	 * country by invoking addCountry method from CountryService Invokes the
	 * findCountryByCode method from CountryService
	 * 
	 * @param 'NC' is country code that is added If country is not found, User
	 * Defined exception CountryNotFoundException is raised
	 */
	private static void testAddCountry() {
		LOGGER.info("Started testing Add Country Method");

		Country country = new Country();
		country.setCode("NC");
		country.setName("newCountryName");

		countryService.addCountry(country);
		try {
			country = countryService.findCountryByCode("NC");
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End of testing Add Country Method");
	}

	/*
	 * Updates the country in database based on country Code Invoking updateCountry
	 * method in CountryService
	 * 
	 * @param country code and country name
	 */
	private static void testUpdateCountry() {
		LOGGER.info("Started testing testUpdateCountry method");

		countryService.updateCountry("NC", "UpdatedCN");
		LOGGER.info("End of testing testUpdateCountry method");
	}
	/*
	 * Deletes the Country from database
	 * 
	 * @param country code
	 */

	private static void testDeleteCountry() {
		LOGGER.info("Started testing testDeleteCountry method");

		countryService.deleteCountry("NC");
		LOGGER.info("End of testing testDeleteCountry method");
	}

	/*
	 * This method displays the countries that contains 'ia' substring
	 * 
	 * @param substring
	 */
	private static void testFindByNameContaining() {
		LOGGER.info("Started testing testFindByNameContaining method");

		List<Country> countries = countryService.findByNameContaining("ia");
		for (Country c : countries) {
			System.out.println(c.getCode() + " " + c.getName());
		}
		LOGGER.info("End of testing testFindByNameContaining method");

	}

	/*
	 * This method displays the countries that starts with 'I'
	 * 
	 * @param 'Letter starts with'
	 */
	private static void testFindByNameStartingWith() {
		LOGGER.info("Start testing testFindByNameStartingWith method");

		List<Country> countries = countryService.findByNameStartingWith("I");
		for (Country c : countries) {
			System.out.println(c.getCode() + " " + c.getName());
		}
		LOGGER.info("End of testing testFindByNameStartingWith method");

	}

	/*
	 * Setting the Date Format for Entire Application
	 */
	private static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/*
	 * This method adds the new Employee to the database Creating instance of
	 * Employee with by invoking parameterized constructor Setting Department by
	 * employee object invoking save method from employeeService
	 * 
	 * @param employee object
	 */

	private static void testAddEmployee() {
		LOGGER.info("start of testing Add Employee method");
		Employee employee = new Employee("Uday", 45000.00, true, parseDate("1998-12-30"));
		Department department = new Department("Analyst Trainee");
		employee.setDepartment(department);
		System.out.println(employee);
		employeeService.save(employee);
		LOGGER.info("end of testing Add Employee method");
	}

	/*
	 * This method displays the employees from database in console Invoking get
	 * method from employee service
	 */

	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(3);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		// LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");
	}

	/*
	 * This method updates the employee department based on employee id given as
	 * input getmethod() is invoked from EmployeeService
	 * 
	 * @param employeeId
	 * 
	 * @param 'Developer' -> new department name invoking save method from
	 * employeeService
	 * 
	 * @param employee object
	 */

	private static void testUpdateEmployee() {
		LOGGER.info("start of testing updateEmployee method");
		int employeeId = 3;
		Employee employee = employeeService.get(employeeId);
		employee.setDepartment(new Department("Developer"));
		employeeService.save(employee);
		LOGGER.debug("Employee={}", employee);
		LOGGER.debug("Department={}", employee.getDepartment());
		LOGGER.info("end of testing updateEmployee method");
	}

	/*
	 * This method displays the Department names for given DepartmentId get method
	 * is invoked from Department Service
	 * 
	 * @param departmentId
	 */
	private static void testGetDepartment() {
		LOGGER.info("Start");
		int departmentId = 4;
		Department department = departmentService.get(departmentId);
		LOGGER.debug("Department={}", department);
		// LOGGER.debug("Employees={}", department.getEmployeeList());
		LOGGER.info("end");
	}

	/*
	 * This method adds the skills to the employee for a particular given employeeId
	 * skills is the HashSet having ManytoMany relationship with employee table save
	 * method is invoked from Skill Service which saves the skills in skill table
	 */

	private static void testAddSkillToEmployee() {
		LOGGER.info("start of testing testAddSkillToEmployee method");
		int employeeId = 3;
		Employee employee = employeeService.get(employeeId);
		Set<Skill> skills = new HashSet<>();
		Skill skill = new Skill("JAVA");
		Skill skill2 = new Skill("MySQL");
		skills.add(skill);
		skills.add(skill2);
		skillService.save(skill);
		skillService.save(skill2);
		employee.setSkillList(skills);
		employeeService.save(employee);
		LOGGER.info("end of testing testAddSkillToEmployee method");
	}

	/*
	 * This method displays all the permanent employees in console
	 */
	public static void testGetAllPermanentEmployees() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}

	/*
	 * This method displays the average salary of an employees
	 */
	public static void testGetAverageSalary() {
		LOGGER.info("Start");
		double averageSalary = employeeService.getAverageSalary();
		LOGGER.debug("Average ", averageSalary);
		LOGGER.info("End");
	}

	/*
	 * This method displays the average salary of an employees based on departmentId
	 */
	public static void testGetAverageSalaryByDepartmentId() {
		LOGGER.info("Start");
		double averageSalary = employeeService.getAverageSalary();
		LOGGER.debug("Average ", averageSalary);
		LOGGER.info("End");
	}

	/*
	 * This method displays all the employees from the employee table
	 */
	public static void testGetAllEmployeesNative() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllEmployeesNative();
		LOGGER.debug("Employees: {}", employees);
		LOGGER.info("End");
	}

}
