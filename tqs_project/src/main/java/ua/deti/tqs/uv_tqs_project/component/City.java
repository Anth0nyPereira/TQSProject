package ua.deti.tqs.uv_tqs_project.component;

public class City {
    private String city;
    private long time;

    public City(String city, long time) {
        this.city = city;
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", time=" + time +
                '}';
    }
}
