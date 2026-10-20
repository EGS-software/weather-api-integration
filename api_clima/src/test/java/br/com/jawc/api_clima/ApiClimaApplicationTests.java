/**
 * @author jawc
 */
package br.com.jawc.api_clima;

import br.com.jawc.api_clima.domain.WeatherHistory;
import br.com.jawc.api_clima.dto.MainDTO;
import br.com.jawc.api_clima.dto.WeatherDTO;
import br.com.jawc.api_clima.dto.WeatherResponseDTO;
import br.com.jawc.api_clima.feign.OpenApiClient;
import br.com.jawc.api_clima.repository.IWeatherRepository;
import br.com.jawc.api_clima.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(TestcontainersConfiguration.class) // Garante que o Testcontainers suba o Mongo no Docker
class ApiClimaApplicationTests {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private IWeatherRepository weatherRepository;

    @MockitoBean
    private OpenApiClient openApiClient;

    @Test
    void shouldFetchWeatherConvertTemperatureAndSaveToDatabase() {
        // 1. ARRANGE (Preparação): Simulamos a resposta da API externa
        // Mandamos 288.15 Kelvin para testar se o Service converte para 15.0 Celsius
        WeatherResponseDTO mockResponse = new WeatherResponseDTO(
                "Ijuí",
                new MainDTO(288.15, 90.0),
                List.of(new WeatherDTO("nuvens dispersas"))
        );

        // Quando o serviço chamar o Feign, o Mockito devolve a nossa resposta falsa
        when(openApiClient.getWeather(anyString(), anyString())).thenReturn(mockResponse);

        // 2. ACT (Ação): Chamamos o seu mét odo principal no Service
        WeatherHistory savedHistory = weatherService.fetchAndSaveWeather("Ijuí");

        // 3. ASSERT (Verificação): Garantimos que a lógica e o banco funcionaram
        Assertions.assertNotNull(savedHistory.getId(), "O ID não deveria ser nulo após salvar no Mongo");
        Assertions.assertEquals("Ijuí", savedHistory.getCity());
        Assertions.assertEquals(15.0, savedHistory.getTempInCelsius(), 0.1, "A conversão de Kelvin para Celsius falhou");

        // Verificamos se o dado realmente foi persistido no contêiner do MongoDB
        long count = weatherRepository.count();
        Assertions.assertTrue(count > 0, "Deveria haver pelo menos um registro no banco de dados");
    }
}