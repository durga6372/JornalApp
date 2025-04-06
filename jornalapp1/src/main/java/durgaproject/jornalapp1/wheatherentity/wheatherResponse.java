package durgaproject.jornalapp1.wheatherentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class wheatherResponse {
        private double temp;
        @JsonProperty("feels-like")
        private double feelsLike;

        }













