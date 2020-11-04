package pl.notepadapi.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.notepadapi.notepad.models.DateModel;

@Repository
public interface DateModelRepository extends JpaRepository<DateModel,Long> {
}
