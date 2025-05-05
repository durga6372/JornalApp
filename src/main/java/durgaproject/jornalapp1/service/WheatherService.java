package durgaproject.jornalapp1.service;
import durgaproject.jornalapp1.wheatherentity.wheatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
@Slf4j
public class WheatherService {
    @Value("${wheather.api.key}")
    private String apiKey;
    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=city&appid=API key&units=metric";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;
    public wheatherResponse getWheather(String city) {
        try {
            wheatherResponse wheatherResponse = redisService.get(city, wheatherResponse.class);
            if (wheatherResponse!=null){
                return wheatherResponse;
            }
            else {
                String finalApi = API.replace("city",city).replace("API key", apiKey);
                ResponseEntity<wheatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, wheatherResponse.class);
                wheatherResponse body = response.getBody();
                if (body !=null){
                    redisService.set(city,body,300l);
                }
                return body;
            }

        } catch (Exception e) {
            log.error("an error ocured", e);
        }
        return null;
    }
}

