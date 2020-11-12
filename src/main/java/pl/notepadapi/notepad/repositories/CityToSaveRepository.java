package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.CityToSave;

import java.util.Optional;

@Repository
public interface CityToSaveRepository extends JpaRepository<CityToSave,Long> {

    Optional<CityToSave> findCityToSaveByCityName(String name);

}
