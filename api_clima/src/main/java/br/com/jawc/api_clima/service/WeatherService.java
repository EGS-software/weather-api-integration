/**
 * @author jawc
 */
package br.com.jawc.api_clima.service;

import br.com.jawc.api_clima.domain.WeatherHistory;
import br.com.jawc.api_clima.dto.WeatherResponseDTO;
import br.com.jawc.api_clima.feign.OpenApiClient;
import br.com.jawc.api_clima.repository.IWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final OpenApiClient openApiClient;
    private final IWeatherRepository weatherRepository;

    @Value("${open-weather.api-key}")
    private String apiKey;

    public WeatherHistory fetchAndSaveWeather(String city){
        WeatherResponseDTO response = openApiClient.getWeather(city, apiKey);

        //CONVERSAO DE KELVIN PARA CELSIUS
        double tempInCelsius = response.main().temp() - 273.15;

        String description = response.weather().get(0).description();

        //MONTANDO ENTITY PARA SALVAR
        WeatherHistory history = WeatherHistory.builder()
                .city(city)
                .description(description)
                .tempInCelsius(tempInCelsius)
                .humidity(response.main().humidity())
                .consultDate(LocalDateTime.now())
                .build();

        history.setOriginalPayLoad(response);

        return weatherRepository.save(history);
    }


}
