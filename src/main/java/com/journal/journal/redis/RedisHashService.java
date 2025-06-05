package com.journal.journal.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.journal.journal.producer.RabbitMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RedisHashService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitMqProducer rabbitMqP;

    public Map<String, Object> convertToMap(Object o) {
        return objectMapper.convertValue(o, new TypeReference<>() {});
    }

    public void cacheSet(String userId, Object o, String[] fieldsToSet ) {
        String key = "cache:user:" + userId;
        Map<String,Object> objectMap = convertToMap(o);
        Map<String, Object> updatedMap = Arrays.stream(fieldsToSet)
                .collect(Collectors.toMap(
                        field -> field,
                        field -> objectMap.getOrDefault(field, null)
                ));
        objectMap.clear();
        objectMap.putAll(updatedMap);
        try {
            redisTemplate.opsForHash().putAll(key, objectMap);
            log.info("[REDIS CACHE SET] Key: {} with TTL: {} ms", key);


        } catch (Exception e) {
            log.error("[REDIS CACHE ERROR] Failed to set key: {} - Exception: ", key, e);
        }
    }

    public Object hGetAll(String userId){
        String key = "cache:user:" + userId;
        Map<Object, Object> userMap = redisTemplate.opsForHash().entries(key);
        if (!userMap.isEmpty()) {
            log.info("[REDIS CACHE MISS] Key: {}", key);
            return null;
        }
        log.info("[REDIS CACHE HIT] Key: {}", key);
        return userMap;
    }

    public Object hGet(String userId, String field){
        String key = "cache:user:" + userId;
        Object value = redisTemplate.opsForHash().get(key, field);
        if (value == null) {
            log.info("[REDIS CACHE MISS] Key: {}", key);
            return null;
        }
        log.info("[REDIS CACHE HIT] Key: {}", key);
        return value;
    }
    public Object HmGet(String userId, String[] fieldsToGet) {
        String key = "cache:user:" + userId;
        List<Object> values = redisTemplate.opsForHash().multiGet(key, Arrays.asList(fieldsToGet));
        if (!values.isEmpty()) {
            log.info("[REDIS CACHE MISS] Key: {}", key);
            return null;
        }
        log.info("[REDIS CACHE HIT] Key: {}", key);
        return values;
    }
}



