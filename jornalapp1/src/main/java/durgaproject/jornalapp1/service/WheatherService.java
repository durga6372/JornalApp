package durgaproject.jornalapp1.service;
import durgaproject.jornalapp1.wheatherentity.wheatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WheatherService {
    @Value("${wheather.api.key}")
    private   String apiKey;
    private static final String API="https://api.openweathermap.org/data/2.5/weather?q=city name&appid=API key";
    @Autowired
    private  RestTemplate restTemplate;

    public  wheatherResponse getWheather(String city){
        String finalApi=API.replace("city",city).replace("API key",apiKey);
        ResponseEntity<wheatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, wheatherResponse.class);
           wheatherResponse body = response.getBody();
            return body;


       }

    }

