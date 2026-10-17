/**
 * @author jawc
 */
package br.com.jawc.api_clima.domain;

import br.com.jawc.api_clima.dto.WeatherResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document(collection = "weather_history")
public class WeatherHistory {

    @Id
    private String id;

    private String city;
    private String description;
    private Double tempInCelsius;
    private Double humidity;
    private LocalDateTime consultDate;

    private WeatherResponseDTO originalPayLoad;
}
