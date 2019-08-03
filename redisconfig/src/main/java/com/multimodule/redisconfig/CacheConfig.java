package com.multimodule.redisconfig;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * 缓存配置类
 * CachingConfigurerSupport是spring封装好的缓存类，继承后直接重写相关方法即可，也可不继承,按相关方法返回的bean，直接写类似的方法返回。
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    /**
     * 设置自动key的生成规则，配置spring boot的注解(@Cacheable的key属性可以选择此bean)，进行方法级别的缓存
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        // 使用：进行分割，可以很多显示出层级关系
        // 这里其实就是new了一个KeyGenerator对象，只是这是lambda表达式的写法,最下面是不使用lambda的写法,涉及到lambda对内部类的简写
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":" + String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            return rsToUse;
        };

       /* return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };*/
    }

    /**
     * 缓存管理器，在这里我们可以缓存的整体过期时间什么的，默认没有配置
     * @return
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(jedisConnectionFactory);
        return builder.build();
    }
}
