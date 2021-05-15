package br.com.fifa.futcrawler.domain.position;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;
import br.com.fifa.futcrawler.domain.attributes.weight.WeightPlayer;
import br.com.fifa.futcrawler.domain.card.util.CardUtil;

import java.math.BigDecimal;

public class Player extends Position {

    private Pace<Integer> pace;
    private Shooting<Integer> shooting;
    private Passing<Integer> passing;
    private Dribbling<Integer> dribbling;
    private Defending<Integer> defending;
    private Physicality<Integer> physicality;

    public Player(Role nome, Pace<Integer> pace, Shooting<Integer> shooting, Passing<Integer> passing,
                  Dribbling<Integer> dribbling, Defending<Integer> defending, Physicality<Integer> physicality) {
        super(nome);
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defending = defending;
        this.physicality = physicality;
    }

    @Override
    public BigDecimal getOverralByAttributes(AttributesWeight weight) {
        try {
            WeightPlayer attributes = (WeightPlayer) weight;
            BigDecimal overral = BigDecimal.ZERO;

            overral.add(multiplyAttribute(this.pace.getAcceleration(), attributes.getPace().getAcceleration()));
            overral.add(multiplyAttribute(this.pace.getSprintSpeed(), attributes.getPace().getSprintSpeed()));
            overral.add(multiplyAttribute(this.shooting.getPositioning(), attributes.getShooting().getPositioning()));
            overral.add(multiplyAttribute(this.shooting.getFinishing(), attributes.getShooting().getFinishing()));
            overral.add(multiplyAttribute(this.shooting.getShotPower(), attributes.getShooting().getShotPower()));
            overral.add(multiplyAttribute(this.shooting.getLongShots(), attributes.getShooting().getLongShots()));
            overral.add(multiplyAttribute(this.shooting.getVolleys(), attributes.getShooting().getVolleys()));
            overral.add(multiplyAttribute(this.shooting.getPenalties(), attributes.getShooting().getPenalties()));
            overral.add(multiplyAttribute(this.passing.getVision(), attributes.getPassing().getVision()));
            overral.add(multiplyAttribute(this.passing.getCrossing(), attributes.getPassing().getCrossing()));
            overral.add(multiplyAttribute(this.passing.getFkAccuracy(), attributes.getPassing().getFkAccuracy()));
            overral.add(multiplyAttribute(this.passing.getShortPassing(), attributes.getPassing().getShortPassing()));
            overral.add(multiplyAttribute(this.passing.getLongPassing(), attributes.getPassing().getLongPassing()));
            overral.add(multiplyAttribute(this.passing.getCurve(), attributes.getPassing().getCurve()));
            overral.add(multiplyAttribute(this.dribbling.getAgility(), attributes.getDribbling().getAgility()));
            overral.add(multiplyAttribute(this.dribbling.getBalance(), attributes.getDribbling().getBalance()));
            overral.add(multiplyAttribute(this.dribbling.getReactions(), attributes.getDribbling().getReactions()));
            overral.add(multiplyAttribute(this.dribbling.getBallControl(), attributes.getDribbling().getBallControl()));
            overral.add(multiplyAttribute(this.dribbling.getDribbling(), attributes.getDribbling().getDribbling()));
            overral.add(multiplyAttribute(this.dribbling.getComposure(), attributes.getDribbling().getComposure()));
            overral.add(multiplyAttribute(this.defending.getInterceptions(), attributes.getDefending().getInterceptions()));
            overral.add(multiplyAttribute(this.defending.getHeadingAccuracy(), attributes.getDefending().getHeadingAccuracy()));
            overral.add(multiplyAttribute(this.defending.getMarking(), attributes.getDefending().getMarking()));
            overral.add(multiplyAttribute(this.defending.getStadingTackle(), attributes.getDefending().getStadingTackle()));
            overral.add(multiplyAttribute(this.defending.getSlidingTackle(), attributes.getDefending().getSlidingTackle()));
            overral.add(multiplyAttribute(this.physicality.getJumping(), attributes.getPhysicality().getJumping()));
            overral.add(multiplyAttribute(this.physicality.getStamina(), attributes.getPhysicality().getStamina()));
            overral.add(multiplyAttribute(this.physicality.getStrength(), attributes.getPhysicality().getStrength()));
            overral.add(multiplyAttribute(this.physicality.getAggression(), attributes.getPhysicality().getAggression()));

            return overral.divide(CardUtil.TOTAL_ATTRIBUTES_PLAYER);

        } catch (ClassCastException | NullPointerException e) {
            throw new RuntimeException("Erro durante o calculo do overral");
        }
    }

    public Pace<Integer> getPace() {
        return pace;
    }

    public Shooting<Integer> getShooting() {
        return shooting;
    }

    public Passing<Integer> getPassing() {
        return passing;
    }

    public Dribbling<Integer> getDribbling() {
        return dribbling;
    }

    public Defending<Integer> getDefending() {
        return defending;
    }

    public Physicality<Integer> getPhysicality() {
        return physicality;
    }
}
