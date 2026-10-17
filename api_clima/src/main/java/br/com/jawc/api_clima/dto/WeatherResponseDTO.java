/**
 * @author jawc
 */
package br.com.jawc.api_clima.dto;

import java.util.List;

public record WeatherResponseDTO(
        String name,
        MainDTO main,
        List<WeatherDTO> weather

) {
}
