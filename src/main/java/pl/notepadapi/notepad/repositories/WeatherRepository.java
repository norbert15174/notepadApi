package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.notepadapi.notepad.models.Weather;

public interface WeatherRepository extends JpaRepository<Weather,Long> {
}
