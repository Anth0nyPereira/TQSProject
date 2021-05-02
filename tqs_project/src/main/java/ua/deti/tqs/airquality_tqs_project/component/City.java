package ua.deti.tqs.airquality_tqs_project.component;

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
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", time=" + time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
