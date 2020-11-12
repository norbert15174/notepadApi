package pl.notepadapi.notepad.models;

import javax.persistence.*;

@Entity
@Table(name = "cities_to_save")
public class CityToSave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String cityName;

    public CityToSave(long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "CityToSave{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }

    public CityToSave() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
