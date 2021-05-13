package br.com.fifa.futcrawler.domain.position;

import br.com.fifa.futcrawler.domain.attributes.Defending;
import br.com.fifa.futcrawler.domain.attributes.Dribbling;
import br.com.fifa.futcrawler.domain.attributes.Shooting;
import br.com.fifa.futcrawler.domain.attributes.Physicality;
import br.com.fifa.futcrawler.domain.attributes.Passing;
import br.com.fifa.futcrawler.domain.attributes.Pace;

import java.math.BigDecimal;
import java.util.Map;

public class Player extends Position {

    private Pace pace;
    private Shooting shooting;
    private Passing passing;
    private Dribbling dribbling;
    private Defending defending;
    private Physicality physicality;

    public Player(Role nome, Pace pace, Shooting shooting, Passing passing, Dribbling dribbling,
                  Defending defending, Physicality physicality) {
        super(nome);
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defending = defending;
        this.physicality = physicality;
    }

    @Override
    public BigDecimal getOverralByAttributes(Map<String, Integer> attributesLevel) {
        try {
            BigDecimal overral = BigDecimal.ZERO;

            overral.add(multiplyAttribute(this.pace.getAcceleration(), attributesLevel.get("acceleration")));
            overral.add(multiplyAttribute(this.pace.getSprintSpeed(), attributesLevel.get("sprintSpeed")));
            overral.add(multiplyAttribute(this.shooting.getPositioning(), attributesLevel.get("positioning")));
            overral.add(multiplyAttribute(this.shooting.getFinishing(), attributesLevel.get("finishing")));
            overral.add(multiplyAttribute(this.shooting.getShotPower(), attributesLevel.get("shotPower")));
            overral.add(multiplyAttribute(this.shooting.getLongShots(), attributesLevel.get("longShots")));
            overral.add(multiplyAttribute(this.shooting.getVolleys(), attributesLevel.get("volleys")));
            overral.add(multiplyAttribute(this.shooting.getPenalties(), attributesLevel.get("penalties")));
            overral.add(multiplyAttribute(this.passing.getVision(), attributesLevel.get("vision")));
            overral.add(multiplyAttribute(this.passing.getFkAccuracy(), attributesLevel.get("fkAccuracy")));
            overral.add(multiplyAttribute(this.passing.getCrossing(), attributesLevel.get("crossing")));
            overral.add(multiplyAttribute(this.passing.getShortPassing(), attributesLevel.get("shortPassing")));
            overral.add(multiplyAttribute(this.passing.getLongPassing(), attributesLevel.get("longPassing")));
            overral.add(multiplyAttribute(this.passing.getCurve(), attributesLevel.get("curve")));
            overral.add(multiplyAttribute(this.dribbling.getAgility(), attributesLevel.get("agility")));
            overral.add(multiplyAttribute(this.dribbling.getBalance(), attributesLevel.get("balance")));
            overral.add(multiplyAttribute(this.dribbling.getReactions(), attributesLevel.get("reactions")));
            overral.add(multiplyAttribute(this.dribbling.getBallControl(), attributesLevel.get("ballControl")));
            overral.add(multiplyAttribute(this.dribbling.getDribbling(), attributesLevel.get("dribbling")));
            overral.add(multiplyAttribute(this.dribbling.getComposure(), attributesLevel.get("composure")));
            overral.add(multiplyAttribute(this.defending.getInterceptions(), attributesLevel.get("interceptions")));
            overral.add(multiplyAttribute(this.defending.getHeadingAccuracy(), attributesLevel.get("headingAccuracy")));
            overral.add(multiplyAttribute(this.defending.getMarking(), attributesLevel.get("marking")));
            overral.add(multiplyAttribute(this.defending.getStadingTackle(), attributesLevel.get("stadingTackle")));
            overral.add(multiplyAttribute(this.defending.getSlidingTackle(), attributesLevel.get("slidingTackle")));
            overral.add(multiplyAttribute(this.physicality.getJumping(), attributesLevel.get("jumping")));
            overral.add(multiplyAttribute(this.physicality.getStamina(), attributesLevel.get("stamina")));
            overral.add(multiplyAttribute(this.physicality.getStrength(), attributesLevel.get("strength")));
            overral.add(multiplyAttribute(this.physicality.getAggression(), attributesLevel.get("aggression")));

            return overral.divide(new BigDecimal(attributesLevel.size()));

        } catch (ClassCastException | NullPointerException e) {
            throw new RuntimeException("Erro durante o calculo do overral");
        }
    }

    public Pace getPace() {
        return pace;
    }

    public Shooting getShooting() {
        return shooting;
    }

    public Passing getPassing() {
        return passing;
    }

    public Dribbling getDribbling() {
        return dribbling;
    }

    public Defending getDefending() {
        return defending;
    }

    public Physicality getPhysicality() {
        return physicality;
    }
}
