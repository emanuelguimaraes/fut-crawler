package br.com.fifa.futcrawler.domain.attributes;

public class Shooting<T> {

    private T positioning;
    private T finishing;
    private T shotPower;
    private T longShots;
    private T volleys;
    private T penalties;

    public Shooting(T positioning, T finishing, T shotPower, T longShots, T volleys,
                    T penalties) {
        this.positioning = positioning;
        this.finishing = finishing;
        this.shotPower = shotPower;
        this.longShots = longShots;
        this.volleys = volleys;
        this.penalties = penalties;
    }

    public T getPositioning() {
        return positioning;
    }

    public T getFinishing() {
        return finishing;
    }

    public T getShotPower() {
        return shotPower;
    }

    public T getLongShots() {
        return longShots;
    }

    public T getVolleys() {
        return volleys;
    }

    public T getPenalties() {
        return penalties;
    }
}
