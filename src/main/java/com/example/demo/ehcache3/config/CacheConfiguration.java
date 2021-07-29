package com.example.demo.ehcache3.config;

import java.time.Duration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.event.CacheEntryEventFilter;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {

	@Value("${ehcache.config.timeToLive}")
	private String timeToLive;

	@Value("${ehcache.config.cacheName}")
	private String cacheName;

	private static final Factory<? extends CacheEntryEventFilter<Object, Object>> NO_FILTER = null;
	private static final boolean IS_OLD_VALUE_REQUIRED = false;
	private static final boolean IS_SYNCHRONOUS = false;


	CacheEntryListenerConfiguration<Object, Object> listenerConfiguration = new MutableCacheEntryListenerConfiguration<>(
			FactoryBuilder.factoryOf(CacheEventLogger.class), NO_FILTER, IS_OLD_VALUE_REQUIRED, IS_SYNCHRONOUS);
	
	@Bean
	public javax.cache.configuration.Configuration<Object, Object> getCacheConfiguration(){
		javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration = Eh107Configuration
				.fromEhcacheCacheConfiguration(CacheConfigurationBuilder
						.newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(200))
						.withExpiry(
								ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(Long.valueOf(timeToLive))))
						.build());
		return jcacheConfiguration;
	}

	@Bean
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		return cm -> {
			createCache(cm, cacheName);
		};
	}

	private void createCache(CacheManager cm, String cacheName) {
		Cache<Integer, Integer> cache = cm.getCache(cacheName);
		if (cache == null) {
			cm.createCache(cacheName, getCacheConfiguration()).registerCacheEntryListener(listenerConfiguration);
		}
	}

}
