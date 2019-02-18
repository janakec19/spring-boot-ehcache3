package com.example.demo.ehcache3;

import com.example.demo.ehcache3.entity.Country;
import com.example.demo.ehcache3.repository.CountryRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private CountryRepository countryRepository;

	@Test
	public void validateCache() {
		Cache countries = this.cacheManager.getCache("countries");
		assertThat(countries).isNotNull();
		countries.clear(); // Simple test assuming the cache is empty
		assertThat(countries.get("BE")).isNull();
		Country be = this.countryRepository.findByCode("BE");
		assertThat((Country) countries.get("BE").get()).isEqualTo(be);
	}

}
