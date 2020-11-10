package pl.notepadapi.notepad.services;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.DateModel;
import pl.notepadapi.notepad.models.Note;
import pl.notepadapi.notepad.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;
    AuthorService authorService;
    DateModelService dateModelService;


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
        if(noteRepository.findAllNotes().isPresent()){
            return noteRepository.findAllNotes().get();
        }else{
            return noteRepository.findAllNotes().orElseThrow();
        }
    }

    public Note findById(long id){
        return noteRepository.findById(id).orElseThrow();
    }



}
