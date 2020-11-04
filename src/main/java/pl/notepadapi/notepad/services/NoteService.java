package pl.notepadapi.notepad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.Note;
import pl.notepadapi.notepad.repositories.NoteRepository;

@Service
public class NoteService {

    NoteRepository noteRepository;
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    void addNewNote(Note note){
        noteRepository.save(note);
    }


}
