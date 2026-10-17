/**
 * @author jawc
 */
package br.com.jawc.api_clima.feign;

import br.com.jawc.api_clima.dto.WeatherResponseDTO;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "open-weather-api", url = "https://api.openweathermap.org/data/2.5")
public interface OpenApiClient {

    @GetMapping("/weather")
    WeatherResponseDTO getWeather(
            @RequestParam("q") String city,
            @RequestParam("appid") String apiKey
    );

}
