package com.journal.journal.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public <T> T get(String key ){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            log.info("[here: {}", key);
            if (o == null) {
                log.info("[REDIS CACHE MISS] Key: {}", key);
                return null;
            }
            log.info("[REDIS CACHE HIT] Key: {}", key);
            return (T) o;
        }
        catch (Exception e) {
            log.error("[REDIS CACHE ERROR] Key: {} - Exception: ", key, e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttlInMillis) {
        try {
            redisTemplate.opsForValue().set(key, o, Duration.ofMillis(ttlInMillis));
            log.info("[REDIS CACHE SET] Key: {} with TTL: {} ms", key, ttlInMillis);
        } catch (Exception e) {
            log.error("[REDIS CACHE ERROR] Failed to set key: {} - Exception: ", key, e);
        }
    }
}





