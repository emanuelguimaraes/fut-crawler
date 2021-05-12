package br.com.fifa.futcrawler.domain.attributes;

public class Passing {

    private int vision;
    private int crossing;
    private int fkAccuracy;
    private int shortPassing;
    private int longPassing;
    private int curve;

    public Passing(int vision, int crossing, int fkAccuracy, int shortPassing, int longPassing, int curve) {
        this.vision = vision;
        this.crossing = crossing;
        this.fkAccuracy = fkAccuracy;
        this.shortPassing = shortPassing;
        this.longPassing = longPassing;
        this.curve = curve;
    }

    public int getVision() {
        return vision;
    }

    public int getCrossing() {
        return crossing;
    }

    public int getFkAccuracy() {
        return fkAccuracy;
    }

    public int getShortPassing() {
        return shortPassing;
    }

    public int getLongPassing() {
        return longPassing;
    }

    public int getCurve() {
        return curve;
    }
}
