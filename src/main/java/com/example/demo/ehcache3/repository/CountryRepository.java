package com.example.demo.ehcache3.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.demo.ehcache3.entity.Country;

@Component
public class CountryRepository {

	// @Cacheable(value = "countries",key = "#code")
	@Cacheable(cacheResolver = "customCacheResolver", key = "#code")
	public Country findByCode(String code) {
		System.out.println("Loading country with code '" + code + "'");
		return new Country(code);
	}

}
