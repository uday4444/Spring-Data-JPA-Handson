package com.cognizant.ormlearn.service;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRepository;

	@Transactional
	public List<Country> getAllCountries()
	{
		List<Country> result= countryRepository.findAll();
		return result;
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException
	{
		Optional<Country> result = countryRepository.findById(countryCode);
		if(!result.isPresent())
		{
			throw new CountryNotFoundException("No Country Found");
		}
		Country country = result.get();
		return country;
	}
	
	@Transactional
	public void addCountry(Country country)
	{
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String code,String name)
	{
		
		Optional<Country> result = countryRepository.findById(code);
		Country country = result.get();
		country.setName(name);
	}
	
	@Transactional
	public void deleteCountry(String code)
	{
		countryRepository.deleteById(code);
	}
	
	@Transactional
	public List<Country> findByNameContaining(String word)
	{
		List<Country> countries = countryRepository.findByNameContainingOrderByNameAsc(word);
		return countries;
	}
	@Transactional
	public List<Country> findByNameStartingWith(String letter)
	{
		List<Country> countries = countryRepository.findByNameStartingWith(letter);
		return countries;
	}
	
}
