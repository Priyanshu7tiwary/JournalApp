package com.journal.journal.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
public class RedisZsetService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void cacheZSet(String key, Double score, Object o) {
        try {
            redisTemplate.opsForZSet().add(key, o, score);
            log.info("[REDIS CACHE SET] Key: {} Score: {}", key, score);
        } catch (Exception e) {
            log.error("[REDIS CACHE ERROR] Failed to set key: {} - Exception: ", key, e);
        }
    }

    public void zRem(String key,Object o){
        Double score = redisTemplate.opsForZSet().score(key, o);
        if (score!=null) {
            redisTemplate.opsForZSet().remove(key,o);
            log.info("[Deleted] Key: {}", key);
        }
        else{
            log.info("[REDIS CACHE NOT FOUND] Key: {}", key);
        }
    }
    public Set<Object> zGetRange(String key, long start, long end) {
        Set<Object> values = redisTemplate.opsForZSet().range(key,start,end);
        log.info("Fetched values: {}", values);
        if (values.isEmpty()) {
            log.info("[REDIS ZSET CACHE MISS] Key: {}", key);
            return values;
        }
        log.info("[REDIS ZSET CACHE HIT] Key: {}, Count: {}", key, values.size());
        return values;
    }
}
