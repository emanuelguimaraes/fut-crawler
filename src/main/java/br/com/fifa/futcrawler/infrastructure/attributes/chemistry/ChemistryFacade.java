package br.com.fifa.futcrawler.infrastructure.attributes.chemistry;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.attributes.chemistry.Chemistry;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryGoalkeeper;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryPlayer;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;

public class ChemistryFacade {

    public static Chemistry create(ChemistryEntity entity) {
        if (ChemistryType.WALL.equals(entity) || ChemistryType.SHIELD.equals(entity)
                || ChemistryType.CAT.equals(entity) || ChemistryType.GLOVE.equals(entity)
                || ChemistryType.GK_BASIC.equals(entity)) {
            return new ChemistryGoalkeeper(entity.getName(),
                    entity.getDiving(),
                    entity.getHandling(),
                    entity.getKicking(),
                    entity.getPositioningGoalkeeper(),
                    entity.getReflexesGoalkeeper(),
                    entity.getSpeed());
        } else {
            return new ChemistryPlayer(entity.getName(),
                    createPaceAttributes(entity),
                    createShootingAttributes(entity),
                    createPassingAttributes(entity),
                    createDribblingAttributes(entity),
                    createDefendingAttributes(entity),
                    createPhysicalityAttributes(entity));
        }
    }

    private static Pace createPaceAttributes(ChemistryEntity entity) {
        return new Pace(
                entity.getAcceleration(),
                entity.getSprintSpeed()
        );
    }

    private static Shooting createShootingAttributes(ChemistryEntity entity) {
        return new Shooting(
                entity.getPositioning(),
                entity.getFinishing(),
                entity.getShotPower(),
                entity.getLongShots(),
                entity.getVolleys(),
                entity.getPenalties()
        );
    }

    private static Passing createPassingAttributes(ChemistryEntity entity) {
        return new Passing(
                entity.getVision(),
                entity.getCrossing(),
                entity.getFkAccuracy(),
                entity.getShortPassing(),
                entity.getLongPassing(),
                entity.getCurve()
        );
    }

    private static Dribbling createDribblingAttributes(ChemistryEntity entity) {
        return new Dribbling(
                entity.getAgility(),
                entity.getBalance(),
                entity.getReactions(),
                entity.getBallControl(),
                entity.getDribbling(),
                entity.getComposure()
        );
    }

    private static Defending createDefendingAttributes(ChemistryEntity entity) {
        return new Defending(
                entity.getInterceptions(),
                entity.getHeadingAccuracy(),
                entity.getMarking(),
                entity.getStadingTackle(),
                entity.getSlidingTackle()
        );
    }

    private static Physicality createPhysicalityAttributes(ChemistryEntity entity) {
        return new Physicality(
                entity.getJumping(),
                entity.getStamina(),
                entity.getStrength(),
                entity.getAggression()
        );
    }
}
