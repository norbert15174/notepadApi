package pl.notepadapi.notepad.services;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.notepadapi.notepad.models.Author;
import pl.notepadapi.notepad.models.DateModel;
import pl.notepadapi.notepad.models.Note;
import pl.notepadapi.notepad.repositories.AuthorRepository;
import pl.notepadapi.notepad.repositories.NoteRepository;


import java.util.*;

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



    @Transactional
    public boolean addNewNote(Note note){

        if (!authorService.findAuthorByName(note.getAuthor().getAuthor()).isPresent() && !noteRepository.findById(note.getNoteId()).isPresent()) {
            System.out.println(note);

            DateModel dateModel = new DateModel();
            note.setDateModel(dateModel);
            noteRepository.save(note);
            return true;
        } else if(authorService.findAuthorByName(note.getAuthor().getAuthor()).isPresent()){

            Author author = authorService.findAuthorByName(note.getAuthor().getAuthor()).get().get(0);
            DateModel dateModel = new DateModel();
            note.setDateModel(dateModel);

            Set<Note> notes = author.getNotes();
            note.setAuthor(author);
            notes.add(note);
            author.setNotes(notes);

            authorService.modifyAuthor(Optional.of(author));
            return true;
        }else{
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
            return null;
        }
    }

    public Note findById(long id){
        return noteRepository.findById(id).orElseThrow();
    }


    public List<Note> findNoteByAuthor(String name){
        if(noteRepository.findNotesByAuthor(name).isPresent()) return noteRepository.findNotesByAuthor(name).get();
        return null;
    }



}
