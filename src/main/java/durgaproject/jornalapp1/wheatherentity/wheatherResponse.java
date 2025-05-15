package durgaproject.jornalapp1.wheatherentity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class   wheatherResponse {
    private Main main;
    @Data
    public static class Main {
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        @Override
        public String toString() {
            return "Temperature: " + temp + "°C, Feels Like: " + feelsLike + "°C";
        }
    }
    @Override
    public String toString() {
        return main.toString(); // So that printing WeatherResponse prints main values
    }
}














