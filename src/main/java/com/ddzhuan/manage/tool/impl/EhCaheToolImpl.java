package com.ddzhuan.manage.tool.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddzhuan.manage.tool.CacheTool;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component
public class EhCaheToolImpl implements CacheTool{

	@Autowired
	CacheManager cacheManager;

//	public EhCaheToolImpl() {
//		cacheManager = CacheManager.getInstance();
//	}

	public void put(String cacheName, String key, Object value) {
		Cache cache = cacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	public Object get(String cacheName, String key) {
		Cache cache = cacheManager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public Cache get(String cacheName) {
		return cacheManager.getCache(cacheName);
	}

	public void remove(String cacheName, String key) {
		Cache cache = cacheManager.getCache(cacheName);
		cache.remove(key);
	}
}
