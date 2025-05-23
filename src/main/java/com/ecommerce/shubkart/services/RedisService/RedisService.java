package com.ecommerce.shubkart.services.RedisService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public <T> T get(String key, Class<T> entityClass) {
        try{
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        }catch (Exception e){
            log.error("Exception in redis get: "+e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl){
        try{
            redisTemplate.opsForValue().set(key, o, ttl, TimeUnit.MINUTES);
        }catch (Exception e){
            log.error("Exception in redis set: "+e);
        }
    }
}
