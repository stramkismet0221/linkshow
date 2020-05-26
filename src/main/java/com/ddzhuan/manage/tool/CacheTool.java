package com.ddzhuan.manage.tool;

import net.sf.ehcache.Cache;

public interface CacheTool {

	public void put(String cacheName, String key, Object value);

	public Object get(String cacheName, String key);

	public Cache get(String cacheName);

	public void remove(String cacheName, String key);
	
}
