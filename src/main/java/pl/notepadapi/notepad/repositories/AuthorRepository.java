package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
