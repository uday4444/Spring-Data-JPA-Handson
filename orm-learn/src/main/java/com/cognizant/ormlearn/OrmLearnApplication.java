package com.cognizant.ormlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OrmLearnApplication {

	private static CountryService countryService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	public static void main(String[] args) {
		// SpringApplication.run(OrmLearnApplication.class, args);
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);

		LOGGER.info("Inside main");

		/*
		 * Invoking testAddCountry method
		 */
		 testGetAllCountries();
		/*
		 * Invoking getAllCountriesTest method
		 */

		 getAllCountriesTest();

		/*
		 * Invoking testAddCountry method
		 */
		 testAddCountry();

		/*
		 * Invoking testUpdateCountry method
		 */
		testUpdateCountry();
		
		/*
		 * Invoking testDeleteCountry method
		 */
		testDeleteCountry();

	}

	/*
	 * This method displays all the countries that are available in DataBase
	 * Invokes the getAllCountries method from CountryService
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
	 * @param 'IN' is a country code to find
	 * If country is not found, User Defined exception CountryNotFoundException is raised
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
	 * This method displays the newly Added country
	 * By using setters add the new country by invoking addCountry method from CountryService
	 * Invokes the findCountryByCode method from CountryService
	 * @param 'NC' is country code that is  added
	 * If country is not found, User Defined exception CountryNotFoundException is raised
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
	 * Updates the country in database based on country Code
	 * Invoking updateCountry method in CountryService 
	 * @param country code and country name
	 */
	private static void testUpdateCountry() {
		LOGGER.info("Started testing testUpdateCountry method");

		countryService.updateCountry("NC", "UpdatedCN");
		LOGGER.info("End of testing testUpdateCountry method");
	}
	/*
	 * Deletes the Country from database 
	 * @param country code
	 */

	private static void testDeleteCountry() {
		LOGGER.info("Started testing testDeleteCountry method");

		countryService.deleteCountry("NC");
		LOGGER.info("End of testing testDeleteCountry method");
	}

}
