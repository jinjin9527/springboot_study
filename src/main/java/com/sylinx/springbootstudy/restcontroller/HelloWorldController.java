package com.sylinx.springbootstudy.restcontroller;

import com.sylinx.springbootstudy.testaop.Test1;
import com.sylinx.springbootstudy.testaop.Test2;
import com.sylinx.springbootstudy.testcacheaop.CacheService1;
import com.sylinx.springbootstudy.testcacheaop.CacheService2;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloWorldController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Test1 test1;

    @Autowired
    private Test2 test2;

    @Autowired
    private CacheService1 cacheService1;
    @Autowired
    private CacheService2 cacheService2;

    @GetMapping("/helloworld")
    public String helloWorld(){
        Map<String, String> s1Map = cacheService1.getData1();
        Map<String, String> s2Map = cacheService2.getData2();
        Map<String, String> s3Map = cacheService1.aaa2();
        Map interceptors = context.getBeansOfType(CacheInterceptor.class);
        return s1Map.toString() + " : " + s2Map.toString() + " : " + s3Map.toString();
    }

    @GetMapping("/shutdown")
    public void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}
