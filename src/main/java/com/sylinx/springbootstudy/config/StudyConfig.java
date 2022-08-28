package com.sylinx.springbootstudy.config;


import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.cache.jcache.interceptor.BeanFactoryJCacheOperationSourceAdvisor;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedHashSet;

@Configuration
public class StudyConfig {

    @Bean
    public KeyGenerator customKeyGenerator(){
        return new CustomKeyGenerator();
    }
    @Bean
    public MyRedisCacheWriter redisCacheWriter(RedisConnectionFactory redisConnectionFactory){
        return new MyRedisCacheWriter(redisConnectionFactory);
    }
    @Bean
    public CacheManager cacheManager(RedisCacheWriter myRedisCacheWriter) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()

                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        CacheManager cacheManager = RedisCacheManager.builder()
                .withCacheConfiguration("itemRedisCache", redisCacheConfiguration)
                .cacheWriter(myRedisCacheWriter).build();
        return new TransactionAwareCacheManagerProxy(cacheManager);
    }

    @Bean
    public NameMatchCacheOperationSource nameMatchCacheOperationSource(){
        NameMatchCacheOperationSource nameMatchCacheOperationSource = new NameMatchCacheOperationSource();
        Collection<CacheOperation> cols = new LinkedHashSet<>();
        CacheableOperation.Builder builder = new CacheableOperation.Builder();
        builder.setKeyGenerator("customKeyGenerator");
        builder.setCacheNames("books");
        builder.build();
        cols.add(builder.build());
        nameMatchCacheOperationSource.addCacheMethod("*", cols);
        return nameMatchCacheOperationSource;
    }


    @Bean
    public CacheInterceptor cacheInterceptor(BeanFactory beanFactory, CacheManager cacheManager, NameMatchCacheOperationSource nameMatchCacheOperationSource) {
        var cacheInterceptor = new CacheInterceptor();

        cacheInterceptor.setBeanFactory(beanFactory);
        cacheInterceptor.setCacheManager(cacheManager);
        cacheInterceptor.setCacheOperationSource(nameMatchCacheOperationSource);
        cacheInterceptor.afterSingletonsInstantiated();
        return cacheInterceptor;
    }

    @Bean
    public Advisor myCacheAdvisor(CacheInterceptor interceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.sylinx.springbootstudy.testcacheaop.*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, interceptor);
    }
}
