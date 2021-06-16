package br.com.fifa.futcrawler.domain.attributes.weight;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;

public class WeightPlayer extends AttributesWeight {

    private Pace<BigDecimal> pace;
    private Shooting<BigDecimal> shooting;
    private Passing<BigDecimal> passing;
    private Dribbling<BigDecimal> dribbling;
    private Defending<BigDecimal> defending;
    private Physicality<BigDecimal> physicality;

    public WeightPlayer(Role position, Pace<BigDecimal> pace, Shooting<BigDecimal> shooting,
                        Passing<BigDecimal> passing, Dribbling<BigDecimal> dribbling,
                        Defending<BigDecimal> defending, Physicality<BigDecimal> physicality) {
        super(position);
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defending = defending;
        this.physicality = physicality;
    }

    public Pace<BigDecimal> getPace() {
        return pace;
    }

    public Shooting<BigDecimal> getShooting() {
        return shooting;
    }

    public Passing<BigDecimal> getPassing() {
        return passing;
    }

    public Dribbling<BigDecimal> getDribbling() {
        return dribbling;
    }

    public Defending<BigDecimal> getDefending() {
        return defending;
    }

    public Physicality<BigDecimal> getPhysicality() {
        return physicality;
    }

    @Override
    public BigDecimal sumAttributes() {
        return this.pace.getAcceleration()
                .add(this.pace.getSprintSpeed())
                .add(this.shooting.getFinishing())
                .add(this.shooting.getLongShots())
                .add(this.shooting.getPenalties())
                .add(this.shooting.getPositioning())
                .add(this.shooting.getShotPower())
                .add(this.shooting.getVolleys())
                .add(this.passing.getCrossing())
                .add(this.passing.getCurve())
                .add(this.passing.getFkAccuracy())
                .add(this.passing.getLongPassing())
                .add(this.passing.getShortPassing())
                .add(this.passing.getVision())
                .add(this.dribbling.getAgility())
                .add(this.dribbling.getBalance())
                .add(this.dribbling.getBallControl())
                .add(this.dribbling.getComposure())
                .add(this.dribbling.getDribbling())
                .add(this.dribbling.getReactions())
                .add(this.defending.getHeadingAccuracy())
                .add(this.defending.getInterceptions())
                .add(this.defending.getMarking())
                .add(this.defending.getSlidingTackle())
                .add(this.defending.getStadingTackle())
                .add(this.physicality.getAggression())
                .add(this.physicality.getJumping())
                .add(this.physicality.getStamina())
                .add(this.physicality.getStrength());
    }
}
