package pl.notepadapi.notepad.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.CityToSave;
import pl.notepadapi.notepad.repositories.CityToSaveRepository;
import java.util.List;
@Service
public class CityToSaveService {

    CityToSaveRepository cityToSaveRepository;
    @Autowired
    public CityToSaveService(CityToSaveRepository cityToSaveRepository) {
        this.cityToSaveRepository = cityToSaveRepository;
    }


    public CityToSave addCityToSave(String name){
        if(name.isBlank() || cityToSaveRepository.findCityToSaveByCityName(name).isPresent()) return null;

        CityToSave city = new CityToSave();
        city.setCityName(name);
        return cityToSaveRepository.save(city);

    }

    public void delCityToSave(String name){
        if(cityToSaveRepository.findCityToSaveByCityName(name).isPresent()) cityToSaveRepository.delete(cityToSaveRepository.findCityToSaveByCityName(name).get());
    }

    public List<CityToSave> getAllCities(){

        return cityToSaveRepository.findAll();
    }



}
