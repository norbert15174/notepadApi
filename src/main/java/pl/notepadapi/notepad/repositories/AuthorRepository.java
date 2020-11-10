package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.Author;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {


    @Query("select a,n from Author a, Note n" +
     " left join fetch a.notes left join fetch n.dateModel")
    public Optional<List<Author>> findAllAuthor();

    public Optional<Author> findAuthorByAuthor(String name);


}
