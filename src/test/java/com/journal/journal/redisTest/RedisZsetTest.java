package com.journal.journal.redisTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.journal.journal.redis.RedisZsetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisZsetTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ObjectMapper objectMapper;
    @Autowired
    private RedisZsetService redisZset;

    @Test
    public void setCache(){
        Map<String,Integer> map = new HashMap<>();
        map.put("three",3);
        redisZset.cacheZSet("hardik" ,Double.valueOf(4) , map);
        Object result = redisZset.zGetRange("hardik",0,-1);
        assertFalse(((java.util.Set<?>) result).isEmpty(), "Expected non-empty result set");
        assertTrue(((java.util.Set<?>) result).contains(map), "Expected result to contain the inserted object");
    }


}
