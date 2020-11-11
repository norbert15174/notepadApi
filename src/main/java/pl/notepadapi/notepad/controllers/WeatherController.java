package pl.notepadapi.notepad.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.notepadapi.notepad.models.CityModel;
import pl.notepadapi.notepad.models.DateModel;
import pl.notepadapi.notepad.models.Weather;
import pl.notepadapi.notepad.models.WeatherTempInfo;
import pl.notepadapi.notepad.repositories.CityModelRepository;
import pl.notepadapi.notepad.repositories.DateModelRepository;
import pl.notepadapi.notepad.repositories.WeatherRepository;
import pl.notepadapi.notepad.repositories.WeatherTempInfoRepository;

@Controller
public class WeatherController {
    //ApiKey that allows you to use WeatherApi
    @Value("${appid}")
    private String apiKey;


    WeatherRepository weatherRepository;
    WeatherTempInfoRepository weatherTempInfoRepository;
    CityModelRepository cityModelRepository;
    DateModelRepository dateModelRepository;
    @Autowired
    public WeatherController(WeatherRepository weatherRepository, WeatherTempInfoRepository weatherTempInfoRepository,CityModelRepository cityModelRepository, DateModelRepository dateModelRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherTempInfoRepository = weatherTempInfoRepository;
        this.cityModelRepository = cityModelRepository;
        this.dateModelRepository = dateModelRepository;
    }

    public boolean setWeatherInformationToDB(String cityName) throws JsonProcessingException {
        //connecting to WeatherApi
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://api.openweathermap.org/data/2.5/weather")
                .queryParam("q",cityName)
                .queryParam("appid",apiKey)
                .queryParam("lang","pl")
                .queryParam("units","metric");
        HttpEntity httpEntity = new HttpEntity(headers);

        //get data from Api
        JsonNode jsonNode = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, httpEntity, JsonNode.class).getBody();
        if(jsonNode.isEmpty()) return false;

        //Change data type
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        Weather weather[] = objectMapper.convertValue(jsonNode.get("weather"), Weather[].class);
        WeatherTempInfo weatherTempInfo[] = objectMapper.convertValue(jsonNode.get("main"), WeatherTempInfo[].class);


        //saving data to DB
        CityModel cityModel = new CityModel();
        DateModel dateModel = new DateModel();
        dateModelRepository.save(dateModel);
        cityModel.setDateModel(dateModel);
        cityModel.setCity(cityName);
        for(Weather weatherObject : weather){
            weatherRepository.save(weatherObject);
            cityModel.setWeather(weatherObject);
        }

        for(WeatherTempInfo weatherTempInfoObject : weatherTempInfo){
            weatherTempInfoRepository.save(weatherTempInfoObject);
            cityModel.setWeatherTempInfo(weatherTempInfoObject);
        }
        cityModelRepository.save(cityModel);
        return true;
    }

}
