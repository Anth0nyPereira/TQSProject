package ua.deti.tqs.airquality_tqs_project;

import java.util.Objects;

public class AirQuality {

    private int aqIndex;
    private double co;
    private double o3;
    private double so2;
    private double no2;
    private double pm10;
    private double pm25;
    private String predominantPollenType;
    private int pollenLevelTree;
    private int pollenLevelWeed;
    private int PollenLevelGrass;
    private int PollenLevelMold;


    public AirQuality() {

    }

    public AirQuality(int aqIndex, double co, double o3, double so2, double no2, double pm10, double pm25, String predominantPollenType, int pollenLevelTree, int pollenLevelWeed, int pollenLevelGrass, int pollenLevelMold) {
        this.aqIndex = aqIndex;
        this.co = co;
        this.o3 = o3;
        this.so2 = so2;
        this.no2 = no2;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.predominantPollenType = predominantPollenType;
        this.pollenLevelTree = pollenLevelTree;
        this.pollenLevelWeed = pollenLevelWeed;
        PollenLevelGrass = pollenLevelGrass;
        PollenLevelMold = pollenLevelMold;
    }

    public int getAqIndex() {
        return aqIndex;
    }

    public double getCo() {
        return co;
    }

    public double getO3() {
        return o3;
    }

    public double getSo2() {
        return so2;
    }

    public double getNo2() {
        return no2;
    }

    public double getPm10() {
        return pm10;
    }

    public double getPm25() {
        return pm25;
    }

    public String getPredominantPollenType() {
        return predominantPollenType;
    }

    public int getPollenLevelTree() {
        return pollenLevelTree;
    }

    public int getPollenLevelWeed() {
        return pollenLevelWeed;
    }

    public int getPollenLevelGrass() {
        return PollenLevelGrass;
    }

    public int getPollenLevelMold() {
        return PollenLevelMold;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirQuality that = (AirQuality) o;
        return aqIndex == that.aqIndex && Double.compare(that.co, co) == 0 && Double.compare(that.o3, o3) == 0 && Double.compare(that.so2, so2) == 0 && Double.compare(that.no2, no2) == 0 && Double.compare(that.pm10, pm10) == 0 && Double.compare(that.pm25, pm25) == 0 && pollenLevelTree == that.pollenLevelTree && pollenLevelWeed == that.pollenLevelWeed && PollenLevelGrass == that.PollenLevelGrass && PollenLevelMold == that.PollenLevelMold && predominantPollenType.equals(that.predominantPollenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aqIndex, co, o3, so2, no2, pm10, pm25, predominantPollenType, pollenLevelTree, pollenLevelWeed, PollenLevelGrass, PollenLevelMold);
    }

    @Override
    public String toString() {
        return "AirQuality{" +
                "aqIndex=" + aqIndex +
                ", co=" + co +
                ", o3=" + o3 +
                ", so2=" + so2 +
                ", no2=" + no2 +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                ", predominantPollenType='" + predominantPollenType + '\'' +
                ", pollenLevelTree=" + pollenLevelTree +
                ", pollenLevelWeed=" + pollenLevelWeed +
                ", PollenLevelGrass=" + PollenLevelGrass +
                ", PollenLevelMold=" + PollenLevelMold +
                '}';
    }
}
