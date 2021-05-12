package br.com.fifa.futcrawler.domain.attributes;

public class Defending {

    private int interceptions;
    private int headingAccuracy;
    private int marking;
    private int stadingTackle;
    private int slidingTackle;

    public Defending(int interceptions, int headingAccuracy, int marking, int stadingTackle, int slidingTackle) {
        this.interceptions = interceptions;
        this.headingAccuracy = headingAccuracy;
        this.marking = marking;
        this.stadingTackle = stadingTackle;
        this.slidingTackle = slidingTackle;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public int getHeadingAccuracy() {
        return headingAccuracy;
    }

    public int getMarking() {
        return marking;
    }

    public int getStadingTackle() {
        return stadingTackle;
    }

    public int getSlidingTackle() {
        return slidingTackle;
    }
}
