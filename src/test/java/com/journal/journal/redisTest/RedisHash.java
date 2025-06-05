package com.journal.journal.redisTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journal.journal.redis.RedisHashService;
import com.journal.journal.util.Json;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RedisHash
{

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ObjectMapper objectMapper;
    @Autowired
    private RedisHashService redisHashService;

    @Test
    public void setCache(){
        Json json = new Json();
        json.setName("5");
        json.setTitle("Hello");
        json.setContent("This is content");
        redisHashService.cacheSet("hardik678588",json,new String[]{"title","content"});
        assertNotNull(redisHashService.hGet("hardik678588","title"));
    }
}
