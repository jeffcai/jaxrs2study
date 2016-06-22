package com.jeffcaijf.jaxrs2.cxf;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jeffcai on 6/21/2016.
 */
public class TokenGenerator {

    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                public String load(String key) {
                    return UUID.randomUUID().toString();
                }
            });

    public static String getToken(String key) throws Exception {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e);
        }
    }

    public static Cache getCache() {
        return cache;
    }

}
