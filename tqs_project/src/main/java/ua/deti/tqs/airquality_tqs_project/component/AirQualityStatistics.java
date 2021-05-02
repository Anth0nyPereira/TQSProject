package ua.deti.tqs.airquality_tqs_project.component;

import org.springframework.stereotype.Component;

@Component
public class AirQualityStatistics {
    private int countRequests;
    private int hits;
    private int misses;

    public AirQualityStatistics() {

    }

    public int getCountRequests() {
        return countRequests;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int updateRequests() {
        this.countRequests = this.countRequests + 1;
        return this.countRequests;
    }

    public int updateHits() {
        this.hits = this.hits + 1;
        return this.hits;
    }

    public int updateMisses() {
        System.out.println("update: " + this);
        this.misses = this.misses + 1;
        return this.misses;
    }

    @Override
    public String toString() {
        return "AirQualityStatistics{" +
                "countRequests=" + countRequests +
                ", hits=" + hits +
                ", misses=" + misses +
                '}';
    }
}
