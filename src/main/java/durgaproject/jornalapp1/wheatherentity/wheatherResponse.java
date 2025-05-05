package durgaproject.jornalapp1.wheatherentity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class WeatherResponse {
    private Main main;

    @Data
    public static class Main {
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;
    }

    @Override
    public String toString() {
        return main.toString(); // So that printing WeatherResponse prints main values
    }
}














