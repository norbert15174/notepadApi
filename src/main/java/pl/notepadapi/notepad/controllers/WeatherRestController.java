package pl.notepadapi.notepad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.notepadapi.notepad.models.CityModel;
import pl.notepadapi.notepad.models.CityToSave;
import pl.notepadapi.notepad.services.CityModelService;
import pl.notepadapi.notepad.services.CityToSaveService;
import java.util.List;

@RestController
@RequestMapping(path = "weather",consumes = MediaType.APPLICATION_JSON_VALUE)
public class WeatherRestController {

    CityToSaveService cityToSaveService;
    CityModelService cityModelService;
    @Autowired
    public WeatherRestController(CityToSaveService cityToSaveService, CityModelService cityModelService) {
        this.cityToSaveService = cityToSaveService;
        this.cityModelService = cityModelService;
    }

    @GetMapping
    public List<CityModel> getAllCitiesWeather(){
        return cityModelService.getCityModels();
    }

    @GetMapping("/{id}")
    public List<CityModel> getAllCitiesWithPage(@PathVariable int id){
        return cityModelService.getCityModels(id);
    }

    @DeleteMapping("/{id}")
    public void delCitiesWeather(@PathVariable long id){
        cityModelService.delCityModel(id);
    }

    @GetMapping("/cities")
    public List<CityToSave> getAllCities(){
        return cityToSaveService.getAllCities();
    }
    @PostMapping("/cities")
    public CityToSave addCity(@RequestBody CityToSave cityToSave){
        return cityToSaveService.addCityToSave(cityToSave.getCityName());
    }
    @DeleteMapping("/cities")
    public void delCity(@RequestParam(required = true) String name){
        cityToSaveService.delCityToSave(name);
    }



}
