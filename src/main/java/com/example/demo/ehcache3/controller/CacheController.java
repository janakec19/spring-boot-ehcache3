package com.example.demo.ehcache3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ehcache3.entity.Country;
import com.example.demo.ehcache3.repository.CountryRepository;

@RestController
@RequestMapping("/cache")
public class CacheController {

	@Autowired
	private CountryRepository countryRepository;

	@RequestMapping(value = "getCountry/{code}", method = RequestMethod.GET)
	public Country get(@PathVariable String code) {
		return countryRepository.findByCode(code);
	}

}
