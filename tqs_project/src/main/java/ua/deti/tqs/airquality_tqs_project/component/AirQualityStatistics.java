package ua.deti.tqs.airquality_tqs_project.component;

import org.springframework.stereotype.Component;

// class that consists of 3 int counters  -  number of requests (Controller + RestController), number of hits (when the user requested for a city that already was in cache, and the AirQuality object is an up-to-date one,
// and finally the number of misses (when the user requested for a city that is present in cache but is outdated)
@Component
public class AirQualityStatistics {
    private int countRequests;
    private int hits;
    private int misses;

    public AirQualityStatistics() {

    }
    // simple getters
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
