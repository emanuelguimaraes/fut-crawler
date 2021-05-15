package br.com.fifa.futcrawler.domain.attributes.weight;

import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;

public class WeightGoalkeeper extends AttributesWeight {

    private BigDecimal diving;
    private BigDecimal handling;
    private BigDecimal kicking;
    private BigDecimal positionning;
    private BigDecimal reflexes;
    private BigDecimal speed;

    public WeightGoalkeeper(Role position, BigDecimal diving, BigDecimal handling, BigDecimal kicking,
                            BigDecimal positionning, BigDecimal reflexes, BigDecimal speed) {
        super(position);
        this.diving = diving;
        this.handling = handling;
        this.kicking = kicking;
        this.positionning = positionning;
        this.reflexes = reflexes;
        this.speed = speed;
    }

    public BigDecimal getDiving() {
        return diving;
    }

    public BigDecimal getHandling() {
        return handling;
    }

    public BigDecimal getKicking() {
        return kicking;
    }

    public BigDecimal getPositionning() {
        return positionning;
    }

    public BigDecimal getReflexes() {
        return reflexes;
    }

    public BigDecimal getSpeed() {
        return speed;
    }
}
