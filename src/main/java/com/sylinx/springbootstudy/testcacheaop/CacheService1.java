package com.sylinx.springbootstudy.testcacheaop;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService1 {
    public Map<String, String> getData1(){
        System.out.println("11");
        return Map.of("CacheService1-getData1-key1", "CacheService1-getData1-value1", "CacheService1-getData1-key2", "CacheService1-getData1-value2");
    }

    public Map<String, String> getData2(){
        System.out.println("12");
        return Map.of("CacheService1-getData2-key1", "CacheService1-getData2-value1", "CacheService1-getData2-key2", "CacheService1-getData2-value2");
    }

    public Map<String, String> aaa2(){
        System.out.println("12");
        return Map.of("CacheService1-aaa2-key1", "CacheService1-aaa2-value1", "CacheService1-aaa2-key2", "CacheService1-aaa2-value2");
    }
}
