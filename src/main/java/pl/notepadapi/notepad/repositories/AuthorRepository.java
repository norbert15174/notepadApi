package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.Author;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {


    @Query("select a from Author a left join fetch a.notes")
    public Optional<List<Author>> findAllAuthor();

    @Query("select a from Author a left join fetch a.notes where a.author = :nameAuthor")
    public Optional<List<Author>> findAuthorByAuthor(@Param("nameAuthor") String name);


}
