/**
 * @author jawc
 */
package br.com.jawc.api_clima.repository;

import br.com.jawc.api_clima.domain.WeatherHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IWeatherRepository extends MongoRepository<WeatherHistory, String> {
}
