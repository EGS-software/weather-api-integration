/**
 * @author jawc
 */
package br.com.jawc.api_clima.controller;

import br.com.jawc.api_clima.domain.WeatherHistory;
import br.com.jawc.api_clima.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherHistory> getWeatherByCity(@PathVariable String city) {
        WeatherHistory history = weatherService.fetchAndSaveWeather(city);
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
}
