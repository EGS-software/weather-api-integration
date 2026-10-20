/**
 * @author jawc
 */
package br.com.jawc.api_clima.controller;

import br.com.jawc.api_clima.domain.WeatherHistory;
import br.com.jawc.api_clima.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @Operation(summary = "Get the weather for the city and save to history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the current weather data for the specified city"),
            @ApiResponse(responseCode = "404", description = "City not found in OpenWeather database",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "{\"timestamp\": \"2026-10-16T15:30:00\", \"status\": 404, \"error\": \"Cidade não encontrada na base de dados da OpenWeather.\"}"))),
            @ApiResponse(responseCode = "500", description = "Internal server error or external API failure",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "{\"timestamp\": \"2026-10-16T15:30:00\", \"status\": 500, \"error\": \"Erro de comunicação com o serviço externo.\"}"))),
    })
    public ResponseEntity<WeatherHistory> getWeatherByCity(@PathVariable String city) {
        WeatherHistory history = weatherService.fetchAndSaveWeather(city);
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
}
