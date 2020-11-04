package pl.notepadapi.notepad.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.DateModel;
import pl.notepadapi.notepad.repositories.DateModelRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class DateModelService {

    DateModelRepository dateModelRepository;
    @Autowired
    public DateModelService(DateModelRepository dateModelRepository) {
        this.dateModelRepository = dateModelRepository;
    }


    Optional<DateModel> findDateModelById(long id) throws Exception {
        if(dateModelRepository.findById(id).isPresent()) {
            return dateModelRepository.findById(id);
        }
        return null;
    }

    void addDateModel(DateModel dateModel){
        dateModelRepository.save(dateModel);
    }



}
