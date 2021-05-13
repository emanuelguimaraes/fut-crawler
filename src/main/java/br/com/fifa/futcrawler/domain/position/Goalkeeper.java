package br.com.fifa.futcrawler.domain.position;

import java.math.BigDecimal;
import java.util.Map;

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

    @Override
    public BigDecimal getOverralByAttributes(Map<String, Integer> attributesLevel) {
        try {
            BigDecimal overral = BigDecimal.ZERO;

            overral.add(multiplyAttribute(this.diving, attributesLevel.get("diving")));
            overral.add(multiplyAttribute(this.handling, attributesLevel.get("handling")));
            overral.add(multiplyAttribute(this.kicking, attributesLevel.get("kicking")));
            overral.add(multiplyAttribute(this.positionning, attributesLevel.get("positionning")));
            overral.add(multiplyAttribute(this.reflexes, attributesLevel.get("reflexes")));
            overral.add(multiplyAttribute(this.speed, attributesLevel.get("speed")));

            return overral.divide(new BigDecimal(attributesLevel.size()));

        } catch (ClassCastException | NullPointerException e) {
            throw new RuntimeException("Erro durante o calculo do overral");
        }
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
