package pl.notepadapi.notepad.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.notepadapi.notepad.controllers.WeatherController;
import pl.notepadapi.notepad.models.CityToSave;
import pl.notepadapi.notepad.services.CityToSaveService;

import java.util.List;



@Component
public class Schedule {


    WeatherController weatherController;
    CityToSaveService cityToSaveService;

    @Autowired
    public Schedule(WeatherController weatherController, CityToSaveService cityToSaveService) {
        this.weatherController = weatherController;
        this.cityToSaveService = cityToSaveService;
    }

    @Scheduled(fixedDelay = 5000)
    public void setWeather(){

        if(!cityToSaveService.getAllCities().isEmpty()){
            List<CityToSave> cityToSaveList = cityToSaveService.getAllCities();
            cityToSaveList.forEach(city -> {
                try {
                    weatherController.setWeatherInformationToDB(city.getCityName());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        }



    }


}
