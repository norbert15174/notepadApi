package pl.notepadapi.notepad;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.notepadapi.notepad.controllers.WeatherController;

@SpringBootApplication
public class NotepadApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotepadApplication.class, args);
    }

    @Autowired
    WeatherController weatherController;

    @EventListener(ApplicationReadyEvent.class)
    void start() throws JsonProcessingException {
        weatherController.setWeatherInformationToDB("Warszawa");
    }

}
