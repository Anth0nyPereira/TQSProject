package ua.deti.tqs.airquality_tqs_project;

import java.util.Objects;

public class City {
    private String city;
    private long time;
    private double latitude;
    private double longitude;

    public City(String city, long time, double latitude, double longitude) {
        this.city = city;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public long getTime() {
        return time;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return time == city1.time && Double.compare(city1.latitude, latitude) == 0 && Double.compare(city1.longitude, longitude) == 0 && city.equals(city1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, time, latitude, longitude);
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", time=" + time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
