package durgaproject.jornalapp1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import durgaproject.jornalapp1.wheatherentity.wheatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j

@Component
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    public <T>  T get(String key, Class<T> entityClass)  {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper= new ObjectMapper();
            return mapper.readValue(o.toString(),entityClass) ;
        }catch (Exception e){
           log.error("exception",e);
           return null;
        }

    }
    public void set(String key, Object o,Long ttl1)  {
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            String jsonvalue =objectMapper.writeValueAsString(o);
             redisTemplate.opsForValue().set(key, jsonvalue,ttl1, TimeUnit.SECONDS);

        }catch (Exception e){
            log.error("exception",e);
        }

    }
}
