package br.com.fifa.futcrawler.application.attributes.request;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;
import br.com.fifa.futcrawler.domain.attributes.weight.WeightGoalkeeper;
import br.com.fifa.futcrawler.domain.attributes.weight.WeightPlayer;
import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;

public class AttributesWeightRequest {

    private Role position;
    private BigDecimal acceleration;
    private BigDecimal sprintSpeed;
    private BigDecimal positioning;
    private BigDecimal finishing;
    private BigDecimal shotPower;
    private BigDecimal longShots;
    private BigDecimal volleys;
    private BigDecimal penalties;
    private BigDecimal vision;
    private BigDecimal crossing;
    private BigDecimal fkAccuracy;
    private BigDecimal shortPassing;
    private BigDecimal longPassing;
    private BigDecimal curve;
    private BigDecimal agility;
    private BigDecimal balance;
    private BigDecimal reactions;
    private BigDecimal ballControl;
    private BigDecimal dribbling;
    private BigDecimal composure;
    private BigDecimal interceptions;
    private BigDecimal headingAccuracy;
    private BigDecimal marking;
    private BigDecimal stadingTackle;
    private BigDecimal slidingTackle;
    private BigDecimal jumping;
    private BigDecimal stamina;
    private BigDecimal strength;
    private BigDecimal aggression;
    private BigDecimal diving;
    private BigDecimal handling;
    private BigDecimal kicking;
    private BigDecimal positionningGoalkeeper;
    private BigDecimal reflexesGoalkeeper;
    private BigDecimal speed;

    public AttributesWeight parseFromAttributesWeight() {
        if (Role.GK.equals(this.position)) {
            return new WeightGoalkeeper(
                    this.position,
                    this.diving,
                    this.handling,
                    this.kicking,
                    this.positionningGoalkeeper,
                    this.reflexesGoalkeeper,
                    this.speed
            );
        } else {
            return new WeightPlayer(
                    this.position,
                    createPaceAttributes(),
                    createShootingAttributes(),
                    createPassingAttributes(),
                    createDribblingAttributes(),
                    createDefendingAttributes(),
                    createPhysicalityAttributes()
            );
        }
    }

    private Pace createPaceAttributes() {
        return new Pace(
                this.acceleration,
                this.sprintSpeed
        );
    }

    private Shooting createShootingAttributes() {
        return new Shooting(
                this.positioning,
                this.finishing,
                this.shotPower,
                this.longShots,
                this.volleys,
                this.penalties
        );
    }

    private Passing createPassingAttributes() {
        return new Passing(
                this.vision,
                this.crossing,
                this.fkAccuracy,
                this.shortPassing,
                this.longPassing,
                this.curve
        );
    }

    private Dribbling createDribblingAttributes() {
        return new Dribbling(
                this.agility,
                this.balance,
                this.reactions,
                this.ballControl,
                this.dribbling,
                this.composure
        );
    }

    private Defending createDefendingAttributes() {
        return new Defending(
                this.interceptions,
                this.headingAccuracy,
                this.marking,
                this.stadingTackle,
                this.slidingTackle
        );
    }

    private Physicality createPhysicalityAttributes() {
        return new Physicality(
                this.jumping,
                this.stamina,
                this.strength,
                this.aggression
        );
    }

    public Role getPosition() {
        return position;
    }

    public BigDecimal getAcceleration() {
        return acceleration;
    }

    public BigDecimal getSprintSpeed() {
        return sprintSpeed;
    }

    public BigDecimal getPositioning() {
        return positioning;
    }

    public BigDecimal getFinishing() {
        return finishing;
    }

    public BigDecimal getShotPower() {
        return shotPower;
    }

    public BigDecimal getLongShots() {
        return longShots;
    }

    public BigDecimal getVolleys() {
        return volleys;
    }

    public BigDecimal getPenalties() {
        return penalties;
    }

    public BigDecimal getVision() {
        return vision;
    }

    public BigDecimal getCrossing() {
        return crossing;
    }

    public BigDecimal getFkAccuracy() {
        return fkAccuracy;
    }

    public BigDecimal getShortPassing() {
        return shortPassing;
    }

    public BigDecimal getLongPassing() {
        return longPassing;
    }

    public BigDecimal getCurve() {
        return curve;
    }

    public BigDecimal getAgility() {
        return agility;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getReactions() {
        return reactions;
    }

    public BigDecimal getBallControl() {
        return ballControl;
    }

    public BigDecimal getDribbling() {
        return dribbling;
    }

    public BigDecimal getComposure() {
        return composure;
    }

    public BigDecimal getInterceptions() {
        return interceptions;
    }

    public BigDecimal getHeadingAccuracy() {
        return headingAccuracy;
    }

    public BigDecimal getMarking() {
        return marking;
    }

    public BigDecimal getStadingTackle() {
        return stadingTackle;
    }

    public BigDecimal getSlidingTackle() {
        return slidingTackle;
    }

    public BigDecimal getJumping() {
        return jumping;
    }

    public BigDecimal getStamina() {
        return stamina;
    }

    public BigDecimal getStrength() {
        return strength;
    }

    public BigDecimal getAggression() {
        return aggression;
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

    public BigDecimal getPositionningGoalkeeper() {
        return positionningGoalkeeper;
    }

    public BigDecimal getReflexesGoalkeeper() {
        return reflexesGoalkeeper;
    }

    public BigDecimal getSpeed() {
        return speed;
    }
}
