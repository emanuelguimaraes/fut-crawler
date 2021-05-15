package br.com.fifa.futcrawler.domain.position;

import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;
import br.com.fifa.futcrawler.domain.attributes.weight.WeightGoalkeeper;
import br.com.fifa.futcrawler.domain.card.util.CardUtil;

import java.math.BigDecimal;

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
    public BigDecimal getOverralByAttributes(AttributesWeight weight) {
        try {
            WeightGoalkeeper attributes = (WeightGoalkeeper) weight;
            BigDecimal overral = BigDecimal.ZERO;

            overral.add(multiplyAttribute(this.diving, attributes.getDiving()));
            overral.add(multiplyAttribute(this.handling, attributes.getHandling()));
            overral.add(multiplyAttribute(this.kicking, attributes.getKicking()));
            overral.add(multiplyAttribute(this.positionning, attributes.getPositionning()));
            overral.add(multiplyAttribute(this.reflexes, attributes.getReflexes()));
            overral.add(multiplyAttribute(this.speed, attributes.getSpeed()));

            return overral.divide(CardUtil.TOTAL_ATTRIBUTES_GOALKEEPER);

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
