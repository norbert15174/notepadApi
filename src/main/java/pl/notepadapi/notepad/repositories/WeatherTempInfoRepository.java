package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.WeatherTempInfo;

@Repository
public interface WeatherTempInfoRepository extends JpaRepository<WeatherTempInfo,Long>{
}
