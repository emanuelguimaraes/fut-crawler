package br.com.fifa.futcrawler.domain.attributes.chemistry;

public class ChemistryGoalkeeper extends Chemistry {

    private int diving;
    private int handling;
    private int kicking;
    private int positioning;
    private int reflexes;
    private int speed;

    public ChemistryGoalkeeper(ChemistryType name, int diving, int handling, int kicking,
                               int positioning, int reflexes, int speed) {
        super(name);
        this.diving = diving;
        this.handling = handling;
        this.kicking = kicking;
        this.positioning = positioning;
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

    public int getPositioning() {
        return positioning;
    }

    public int getReflexes() {
        return reflexes;
    }

    public int getSpeed() {
        return speed;
    }
}
