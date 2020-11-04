package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.CityModel;

@Repository
public interface CityModelRepository extends JpaRepository<CityModel,Long> {
}
