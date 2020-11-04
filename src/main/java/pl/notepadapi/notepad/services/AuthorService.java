package pl.notepadapi.notepad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.Author;
import pl.notepadapi.notepad.models.DateModel;
import pl.notepadapi.notepad.models.Note;
import pl.notepadapi.notepad.repositories.AuthorRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorService {

    AuthorRepository authorRepository;
    NoteService noteService;
    DateModelService dateModelService;

    public AuthorService(AuthorRepository authorRepository, NoteService noteService, DateModelService dateModelService) {
        this.authorRepository = authorRepository;
        this.noteService = noteService;
        this.dateModelService = dateModelService;
    }

    @EventListener(ApplicationReadyEvent.class)
    void AddAuthor(){
        Author author = new Author("Norbert");
        Note note = new Note("Brak","asdddd");
        DateModel dateModel = new DateModel();
        dateModelService.addDateModel(dateModel);
        note.setDateModel(dateModel);
        Set<Note> notes = new HashSet<>();
        notes.add(note);
        author.setNotes(notes);
        authorRepository.save(author);
        note.setAuthor(author);
        noteService.addNewNote(note);


        System.out.println(authorRepository.findById(1L).get().getAuthor());


    }


}
