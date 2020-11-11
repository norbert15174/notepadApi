package pl.notepadapi.notepad.services;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.Author;
import pl.notepadapi.notepad.models.DateModel;
import pl.notepadapi.notepad.models.Note;
import pl.notepadapi.notepad.repositories.AuthorRepository;
import pl.notepadapi.notepad.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NoteService {


    NoteRepository noteRepository;
    AuthorService authorService;
    DateModelService dateModelService;

    @Autowired
    AuthorRepository authorRepository;


    @Autowired
    public NoteService(NoteRepository noteRepository, AuthorService authorService, DateModelService dateModelService) {
        this.noteRepository = noteRepository;
        this.authorService = authorService;
        this.dateModelService = dateModelService;
    }




    public boolean addNewNote(Note note){

        if (authorService.findAuthorByName(note.getAuthor().getAuthor()).isPresent() && !noteRepository.findById(note.getNoteId()).isPresent()) {
            DateModel dateModel = new DateModel();
            dateModelService.addDateModel(dateModel);
            note.setDateModel(dateModel);
            noteRepository.save(note);
            return true;
        } else {
            return false;
        }
    }

    public boolean modifyNote(Note note){
        if (authorService.findAuthorByName(note.getAuthor().getAuthor()).isPresent() && noteRepository.findById(note.getNoteId()).isPresent()) {
            DateModel dateModel = new DateModel();
            dateModelService.addDateModel(dateModel);
            note.setDateModel(dateModel);
            noteRepository.save(note);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteNote(long id){
        if(noteRepository.findById(id).isPresent()){
            noteRepository.deleteById(id);
            return true;
        }
        return false;

    }

    public List<Note> findAll(){
        return noteRepository.findAllNotes().get();
//        if(noteRepository.findAllNotes().isPresent()){
//            return noteRepository.findAllNotes().get();
//        }else{
//            return noteRepository.findAllNotes().orElseThrow();
//        }
    }

    public Note findById(long id){
        return noteRepository.findById(id).orElseThrow();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void check(){
        Note note = new Note();
        note.setDateModel(new DateModel());
        Author author = new Author();
        author.setAuthor("Norbert");
        note.setAuthor(author);
        note.setContent("asd");
        note.setTopic("lolek");
        noteRepository.save(note);




    }




}
