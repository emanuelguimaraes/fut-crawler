package br.com.fifa.futcrawler.domain.attributes;

public class Defending<T> {

    private T interceptions;
    private T headingAccuracy;
    private T marking;
    private T stadingTackle;
    private T slidingTackle;

    public Defending(T interceptions, T headingAccuracy, T marking, T stadingTackle, T slidingTackle) {
        this.interceptions = interceptions;
        this.headingAccuracy = headingAccuracy;
        this.marking = marking;
        this.stadingTackle = stadingTackle;
        this.slidingTackle = slidingTackle;
    }

    public T getInterceptions() {
        return interceptions;
    }

    public T getHeadingAccuracy() {
        return headingAccuracy;
    }

    public T getMarking() {
        return marking;
    }

    public T getStadingTackle() {
        return stadingTackle;
    }

    public T getSlidingTackle() {
        return slidingTackle;
    }
}
