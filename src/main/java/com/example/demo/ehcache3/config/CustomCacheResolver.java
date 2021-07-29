package com.example.demo.ehcache3.config;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

@Component
public class CustomCacheResolver implements CacheResolver {

	@Autowired
    private CacheManager cacheManager;


    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<Cache> caches = new ArrayList<>();
		/*
		 * if(context.getTarget().getClass() == GatewayRepository.class){
		 * if(context.getMethod().getName().equals("findByBulkId")){
		 * caches.add(cacheManager.getCache("gatewayCache")); } }
		 */
        caches.add(cacheManager.getCache("countries"));
        return caches;
    }



}
