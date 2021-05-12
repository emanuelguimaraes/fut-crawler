package br.com.fifa.futcrawler.domain.position;

public class Goalkeeper extends Position {

    private int diving;
    private int handling;
    private int kicking;
    private int positionning;
    private int reflexes;
    private int speed;

    public Goalkeeper(int diving, int handling, int kicking, int positionning, int reflexes, int speed) {
        super(Role.GK);
        this.diving = diving;
        this.handling = handling;
        this.kicking = kicking;
        this.positionning = positionning;
        this.reflexes = reflexes;
        this.speed = speed;
    }

    public int getDiving() {
        return diving;
    }

    public int getHandling() {
        return handling;
    }

    public int getKicking() {
        return kicking;
    }

    public int getPositionning() {
        return positionning;
    }

    public int getReflexes() {
        return reflexes;
    }

    public int getSpeed() {
        return speed;
    }
}
