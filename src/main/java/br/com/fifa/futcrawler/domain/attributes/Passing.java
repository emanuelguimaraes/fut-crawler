package br.com.fifa.futcrawler.domain.attributes;

public class Passing<T> {

    private T vision;
    private T crossing;
    private T fkAccuracy;
    private T shortPassing;
    private T longPassing;
    private T curve;

    public Passing(T vision, T crossing, T fkAccuracy, T shortPassing, T longPassing, T curve) {
        this.vision = vision;
        this.crossing = crossing;
        this.fkAccuracy = fkAccuracy;
        this.shortPassing = shortPassing;
        this.longPassing = longPassing;
        this.curve = curve;
    }

    public T getVision() {
        return vision;
    }

    public T getCrossing() {
        return crossing;
    }

    public T getFkAccuracy() {
        return fkAccuracy;
    }

    public T getShortPassing() {
        return shortPassing;
    }

    public T getLongPassing() {
        return longPassing;
    }

    public T getCurve() {
        return curve;
    }
}
