package pl.notepadapi.notepad.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.CityModel;
import pl.notepadapi.notepad.repositories.CityModelRepository;
import java.util.List;
@Service
public class CityModelService {
    @Autowired
    public CityModelService(CityModelRepository cityModelRepository) {
        this.cityModelRepository = cityModelRepository;
    }

    CityModelRepository cityModelRepository;

    public List<CityModel> getCityModels(int page){
        if(!cityModelRepository.findAllCityWeather(PageRequest.of(page,5)).isPresent()) return null;
        return cityModelRepository.findAllCityWeather(PageRequest.of(page,5)).get();
    }

    public List<CityModel> getCityModels(){
        if(!cityModelRepository.findAllCityWeather(PageRequest.of(0,5)).isPresent()) return null;
        return cityModelRepository.findAllCityWeather(PageRequest.of(0,5)).get();
    }

    public void delCityModel(long id){
        if(cityModelRepository.findById(id).isPresent()) cityModelRepository.delete(cityModelRepository.findById(id).get());
    }




}
