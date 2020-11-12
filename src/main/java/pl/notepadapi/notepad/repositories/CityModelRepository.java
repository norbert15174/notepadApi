package pl.notepadapi.notepad.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.CityModel;


import java.util.List;
import java.util.Optional;

@Repository
public interface CityModelRepository extends JpaRepository<CityModel,Long> {

    @Query("SELECT w from CityModel w left join fetch w.dateModel left join fetch w.weather left join fetch w.weatherTempInfo ORDER BY w.CityId DESC ")
    Optional<List<CityModel>> findAllCityWeather(Pageable pageable);



}
