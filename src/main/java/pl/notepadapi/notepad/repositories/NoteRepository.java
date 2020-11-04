package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
}
