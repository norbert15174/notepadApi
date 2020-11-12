package pl.notepadapi.notepad.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.notepadapi.notepad.models.Author;

import pl.notepadapi.notepad.repositories.AuthorRepository;


import java.util.Optional;
import java.util.List;


@Service
public class AuthorService {

    AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> findAll(){
        if(!authorRepository.findAllAuthor().isPresent()) return null;
        return authorRepository.findAllAuthor().get();
    }

    public Optional<List<Author>> findAuthorByName(String name){
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
    @Transactional
    public boolean delAuthor(long id){
        if(authorRepository.findById(id).isPresent()) authorRepository.delete(authorRepository.findById(id).get());
        return true;
    }




}
