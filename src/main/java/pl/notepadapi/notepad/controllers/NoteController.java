package pl.notepadapi.notepad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping(path = "notes")
public class NoteController {

    NoteService noteService;
    NoteRepository noteRepository;
    AuthorService authorService;

    @Autowired
    public NoteController(NoteService noteService, NoteRepository noteRepository, AuthorService authorService) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.authorService = authorService;
    }







}
