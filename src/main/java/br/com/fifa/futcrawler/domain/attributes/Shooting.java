package br.com.fifa.futcrawler.domain.attributes;

public class Shooting {

    private int positioning;
    private int finishing;
    private int shotPower;
    private int longShots;
    private int volleys;
    private int penalties;

    public Shooting(int positioning, int finishing, int shotPower, int longShots, int volleys,
                    int penalties) {
        this.positioning = positioning;
        this.finishing = finishing;
        this.shotPower = shotPower;
        this.longShots = longShots;
        this.volleys = volleys;
        this.penalties = penalties;
    }

    public int getPositioning() {
        return positioning;
    }

    public int getFinishing() {
        return finishing;
    }

    public int getShotPower() {
        return shotPower;
    }

    public int getLongShots() {
        return longShots;
    }

    public int getVolleys() {
        return volleys;
    }

    public int getPenalties() {
        return penalties;
    }
}
