package pl.notepadapi.notepad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.notepadapi.notepad.models.Author;
import pl.notepadapi.notepad.models.Note;
import pl.notepadapi.notepad.repositories.AuthorRepository;
import pl.notepadapi.notepad.repositories.NoteRepository;
import pl.notepadapi.notepad.services.AuthorService;
import pl.notepadapi.notepad.services.NoteService;

import java.util.Optional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "notes",consumes = MediaType.APPLICATION_JSON_VALUE)
public class NoteController {

    NoteService noteService;
    AuthorService authorService;

    @Autowired
    public NoteController(NoteService noteService, AuthorService authorService) {
        this.noteService = noteService;
        this.authorService = authorService;
    }

    @GetMapping
    public List<Note> getAllNotes(){
        return noteService.findAll();
    }

    @PostMapping
    public List<Note> addNote(@RequestBody Note note){
        noteService.addNewNote(note);
        return noteService.findNoteByAuthor(note.getAuthor().getAuthor());
    }

    @DeleteMapping
    public void delNote(@RequestParam(required = true) long id){
        noteService.deleteNote(id);
    }

    @PutMapping
    public Note updateNote(@RequestBody Note note){
        noteService.modifyNote(note);
        return noteService.findById(note.getNoteId());
    }

    @GetMapping("/{name}")
    public List<Note> getNoteByAuthor(@PathVariable String name){
        return noteService.findNoteByAuthor(name);
    }


}
