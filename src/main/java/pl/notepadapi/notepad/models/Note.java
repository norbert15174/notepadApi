package pl.notepadapi.notepad.models;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noteId;

    public Note(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    private String topic;

    private String content;

    @ManyToOne
    private Author author;

    @OneToOne
    DateModel dateModel;

    public Note() {
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getTopic() {
        return topic;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public DateModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(DateModel dateModel) {
        this.dateModel = dateModel;
    }
}
