package pl.notepadapi.notepad.models;

import javax.persistence.*;


@Entity
public class CityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CityId;

    private String City;

    @OneToOne
    private DateModel dateModel;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Weather weather = new Weather();
    @OneToOne(cascade = CascadeType.REMOVE)
    private WeatherTempInfo weatherTempInfo = new WeatherTempInfo();

    public CityModel() {
    }

    public long getCityId() {
        return CityId;
    }

    public void setCityId(long cityId) {
        CityId = cityId;
    }

    public DateModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(DateModel dateModel) {
        this.dateModel = dateModel;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public WeatherTempInfo getWeatherTempInfo() {
        return weatherTempInfo;
    }

    public void setWeatherTempInfo(WeatherTempInfo weatherTempInfo) {
        this.weatherTempInfo = weatherTempInfo;
    }
}
