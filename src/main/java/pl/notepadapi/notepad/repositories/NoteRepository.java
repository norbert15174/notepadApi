package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.Note;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    @Query("select n from Note n left join fetch n.dateModel left join fetch n.author where n.noteId = :id")
    public Optional<Note> findById(@Param("id") long id);

    @Query("select n from Note n left join fetch n.dateModel left join fetch n.author")
    public Optional<List<Note>> findAllNotes();

    @Query("select n from Note n left join fetch n.dateModel left join fetch n.author where n.author.author = :aut")
    public Optional<List<Note>> findNotesByAuthor(@Param("aut") String aut);

}
