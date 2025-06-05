package com.journal.journal.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RedisListService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // Push to the right (like a queue)
    public void cacheListPush(String key, Object o) {
        try {
            redisTemplate.opsForList().rightPush(key, o);
            log.info("[REDIS LIST PUSH] Key: {}", key);
        } catch (Exception e) {
            log.error("[REDIS LIST ERROR] Failed to push key: {} - Exception: ", key, e);
        }
    }

    // Pop from the left
    public Object cacheListPop(String key) {
        try {
            Object val = redisTemplate.opsForList().leftPop(key);
            if (val == null) {
                log.info("[REDIS LIST EMPTY] Key: {}", key);
                return null;
            }
            log.info("[REDIS LIST POP] Key: {}", key);
            return val;
        } catch (Exception e) {
            log.error("[REDIS LIST ERROR] Failed to pop key: {} - Exception: ", key, e);
            return null;
        }
    }
    public List<Object> getListRange(String key, long start, long end) {
        try {
            List<Object> list = redisTemplate.opsForList().range(key, start, end);
            if (list == null || list.isEmpty()) {
                log.info("[REDIS LIST MISS] Key: {}", key);
                return Collections.emptyList();
            }

            log.info("[REDIS LIST HIT] Key: {}, Count: {}", key, list.size());
            return list;
        } catch (Exception e) {
            log.error("[REDIS LIST ERROR] Failed to fetch {} - Exception: ", key, e);
            return Collections.emptyList();
        }
    }
    // Remove specific value
    public void removeListValue(String key, Object o) {
        try {
            Long removed = redisTemplate.opsForList().remove(key, 0, o); // 0 = remove all matching
            log.info("[REDIS LIST REMOVE] Key: {} Count: {}", key, removed);
        } catch (Exception e) {
            log.error("[REDIS LIST ERROR] Failed to remove from list: {} - Exception: ", key, e);
        }
    }
}
