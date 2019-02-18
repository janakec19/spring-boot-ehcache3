package com.example.demo.ehcache3.components;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;


public class CacheProvider implements CommandLineRunner {
    private static final Log logger = LogFactory.getLog(CacheProvider.class);

    private final CacheManager cacheManager;

    public CacheProvider(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Using cache manager: " + this.cacheManager.getClass().getName());
    }
}
