package com.sylinx.springbootstudy.testcacheaop;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService2 {

    public Map<String, String> getData1(){
        System.out.println("21");
        return Map.of("CacheService2-getData1-key1", "CacheService2-getData1-value1", "CacheService2-getData1-key2", "CacheService2-getData1-value2");
    }

    public Map<String, String> getData2(){
        System.out.println("22");
        return Map.of("CacheService2-getData2-key1", "CacheService2-getData2-value1", "CacheService2-getData2-key2", "CacheService2-getData2-value2");
    }
}
