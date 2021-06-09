package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.biography.Biography;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.club.Club;
import br.com.fifa.futcrawler.domain.club.League;
import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.domain.position.Goalkeeper;
import br.com.fifa.futcrawler.domain.position.Player;
import br.com.fifa.futcrawler.domain.position.Position;
import br.com.fifa.futcrawler.domain.price.Price;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesGoalkeeperEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity;
import br.com.fifa.futcrawler.infrastructure.price.PriceEntity;

public class CardFacade {

    public static Card create(CardEntity cardEntity) {
        return new Card(cardEntity.getName(),
                createBiography(cardEntity),
                createPosition(cardEntity),
                createClub(cardEntity),
                createPrice(cardEntity.getPrice())
        );
    }

    private static Biography createBiography(CardEntity cardEntity) {
        return new Biography(
                cardEntity.getHeight(),
                cardEntity.getWeight(),
                cardEntity.getNation(),
                cardEntity.getRevision(),
                cardEntity.getSkills(),
                cardEntity.getFoot(),
                cardEntity.getWeakFoot(),
                cardEntity.getIdResource());
    }

    private static Position createPosition(CardEntity cardEntity) {
        if (Role.GK.equals(cardEntity.getPosition())) {
            AttributesGoalkeeperEntity attributesGoalkeeperEntity = (AttributesGoalkeeperEntity) cardEntity.getAttributes();
            return new Goalkeeper(
                    attributesGoalkeeperEntity.getDiving(),
                    attributesGoalkeeperEntity.getHandling(),
                    attributesGoalkeeperEntity.getKicking(),
                    attributesGoalkeeperEntity.getPositionning(),
                    attributesGoalkeeperEntity.getReflexes(),
                    attributesGoalkeeperEntity.getSpeed()
            );
        } else {
            AttributesPlayerEntity attributesPlayerEntity = (AttributesPlayerEntity) cardEntity.getAttributes();
            return new Player(
                    cardEntity.getPosition(),
                    createPaceAttributes(attributesPlayerEntity),
                    createShootingAttributes(attributesPlayerEntity),
                    createPassingAttributes(attributesPlayerEntity),
                    createDribblingAttributes(attributesPlayerEntity),
                    createDefendingAttributes(attributesPlayerEntity),
                    createPhysicalityAttributes(attributesPlayerEntity)
            );
        }
    }

    private static Club createClub(CardEntity cardEntity) {
        return new Club(
                cardEntity.getClub(),
                new League(cardEntity.getLeague())
        );
    }

    private static Price createPrice(PriceEntity priceEntity) {
        if (priceEntity != null) {
            return new Price(priceEntity.getCurrentValue(),
                    priceEntity.getMinValue(),
                    priceEntity.getMaxValue());
        } else {
            return null;
        }
    }

    private static Pace createPaceAttributes(AttributesPlayerEntity atributos) {
        return new Pace(
                atributos.getAcceleration(),
                atributos.getSprintSpeed()
        );
    }

    private static Shooting createShootingAttributes(AttributesPlayerEntity atributos) {
        return new Shooting(
                atributos.getPositioning(),
                atributos.getFinishing(),
                atributos.getShotPower(),
                atributos.getLongShots(),
                atributos.getVolleys(),
                atributos.getPenalties()
        );
    }

    private static Passing createPassingAttributes(AttributesPlayerEntity atributos) {
        return new Passing(
                atributos.getVision(),
                atributos.getCrossing(),
                atributos.getFkAccuracy(),
                atributos.getShortPassing(),
                atributos.getLongPassing(),
                atributos.getCurve()
        );
    }

    private static Dribbling createDribblingAttributes(AttributesPlayerEntity atributos) {
        return new Dribbling(
                atributos.getAgility(),
                atributos.getBalance(),
                atributos.getReactions(),
                atributos.getBallControl(),
                atributos.getDribbling(),
                atributos.getComposure()
        );
    }

    private static Defending createDefendingAttributes(AttributesPlayerEntity atributos) {
        return new Defending(
                atributos.getInterceptions(),
                atributos.getHeadingAccuracy(),
                atributos.getMarking(),
                atributos.getStadingTackle(),
                atributos.getSlidingTackle()
        );
    }

    private static Physicality createPhysicalityAttributes(AttributesPlayerEntity atributos) {
        return new Physicality(
                atributos.getJumping(),
                atributos.getStamina(),
                atributos.getStrength(),
                atributos.getAggression()
        );
    }
}
