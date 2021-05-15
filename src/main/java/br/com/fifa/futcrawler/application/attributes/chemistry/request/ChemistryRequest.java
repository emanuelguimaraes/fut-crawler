package br.com.fifa.futcrawler.application.attributes.chemistry.request;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.attributes.chemistry.Chemistry;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryGoalkeeper;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryPlayer;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;

public class ChemistryRequest {

    private ChemistryType name;
    private int acceleration;
    private int sprintSpeed;
    private int positioning;
    private int finishing;
    private int shotPower;
    private int longShots;
    private int volleys;
    private int penalties;
    private int vision;
    private int crossing;
    private int fkAccuracy;
    private int shortPassing;
    private int longPassing;
    private int curve;
    private int agility;
    private int balance;
    private int reactions;
    private int ballControl;
    private int dribbling;
    private int composure;
    private int interceptions;
    private int headingAccuracy;
    private int marking;
    private int stadingTackle;
    private int slidingTackle;
    private int jumping;
    private int stamina;
    private int strength;
    private int aggression;
    private int diving;
    private int handling;
    private int kicking;
    private int positionningGoalkeeper;
    private int reflexesGoalkeeper;
    private int speed;

    public Chemistry parseFromChemistry() {
        if (ChemistryType.WALL.equals(this.name) || ChemistryType.SHIELD.equals(this.name)
                || ChemistryType.CAT.equals(this.name) || ChemistryType.GLOVE.equals(this.name)
                || ChemistryType.GK_BASIC.equals(this.name)) {
            return new ChemistryGoalkeeper(this.name,
                    this.diving,
                    this.handling,
                    this.kicking,
                    this.positionningGoalkeeper,
                    this.reflexesGoalkeeper,
                    this.speed);
        } else {
            return new ChemistryPlayer(this.name,
                    createPaceAttributes(),
                    createShootingAttributes(),
                    createPassingAttributes(),
                    createDribblingAttributes(),
                    createDefendingAttributes(),
                    createPhysicalityAttributes());
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

    public ChemistryType getName() {
        return name;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getSprintSpeed() {
        return sprintSpeed;
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

    public int getVision() {
        return vision;
    }

    public int getCrossing() {
        return crossing;
    }

    public int getFkAccuracy() {
        return fkAccuracy;
    }

    public int getShortPassing() {
        return shortPassing;
    }

    public int getLongPassing() {
        return longPassing;
    }

    public int getCurve() {
        return curve;
    }

    public int getAgility() {
        return agility;
    }

    public int getBalance() {
        return balance;
    }

    public int getReactions() {
        return reactions;
    }

    public int getBallControl() {
        return ballControl;
    }

    public int getDribbling() {
        return dribbling;
    }

    public int getComposure() {
        return composure;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public int getHeadingAccuracy() {
        return headingAccuracy;
    }

    public int getMarking() {
        return marking;
    }

    public int getStadingTackle() {
        return stadingTackle;
    }

    public int getSlidingTackle() {
        return slidingTackle;
    }

    public int getJumping() {
        return jumping;
    }

    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public int getAggression() {
        return aggression;
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

    public int getPositionningGoalkeeper() {
        return positionningGoalkeeper;
    }

    public int getReflexesGoalkeeper() {
        return reflexesGoalkeeper;
    }

    public int getSpeed() {
        return speed;
    }
}
