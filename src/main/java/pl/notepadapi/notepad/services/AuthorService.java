package pl.notepadapi.notepad.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.notepadapi.notepad.models.Author;

import pl.notepadapi.notepad.repositories.AuthorRepository;


import java.util.Optional;


@Service
public class AuthorService {

    AuthorRepository authorRepository;
    NoteService noteService;
    DateModelService dateModelService;
    @Autowired
    public AuthorService(AuthorRepository authorRepository, NoteService noteService, DateModelService dateModelService) {
        this.authorRepository = authorRepository;
        this.noteService = noteService;
        this.dateModelService = dateModelService;
    }

    public Optional<Author> findAuthorByName(String name){
        if(name.isBlank()) return null;
        return authorRepository.findAuthorByAuthor(name);
    }

    public boolean modifyAuthor(Optional<Author> author){
        if(author.isPresent()){
            if(authorRepository.findById(author.get().getAuthorId()).isPresent()) {
                authorRepository.save(author.get());
                return true;
            }
                return false;
        }
        return false;
    }
    public Author addAuthor(Author author){
        if(author.getAuthor().isBlank()) return null;
        if(authorRepository.findAuthorByAuthor(author.getAuthor()).isPresent()) return null;
        if((Long)author.getAuthorId() == null || !authorRepository.findById(author.getAuthorId()).isPresent()) return authorRepository.save(author);
        return null;
    }




}
