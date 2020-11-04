package pl.notepadapi.notepad.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "dates")
public class DateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dateId;

    private LocalDate date;

    private LocalTime time;

    public DateModel() {
        this.date = LocalDate.now();
        this.time = LocalTime.now().withSecond(0);
    }

    public void setDateId(long dateId) {
        this.dateId = dateId;
    }

    public long getDateId() {
        return dateId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
