package pl.notepadapi.notepad.models;





import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;

    private String author;

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", author='" + author + '\'' +
                ", notes=" + notes +
                '}';
    }

    public Author(String author) {
        this.author = author;
    }


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "noteId")
    private Set<Note> notes = new HashSet<>();


    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Author() {
    }
}
