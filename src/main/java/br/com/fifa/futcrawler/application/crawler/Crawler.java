package br.com.fifa.futcrawler.application.crawler;

import br.com.fifa.futcrawler.application.crawler.dto.CardDetailsDTO;
import br.com.fifa.futcrawler.application.crawler.dto.SimpleCardDTO;
import br.com.fifa.futcrawler.application.crawler.exception.CrawlerException;
import br.com.fifa.futcrawler.domain.card.util.CardUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Crawler {

    List<SimpleCardDTO> getListCards(String url) throws CrawlerException;
    CardDetailsDTO getCardDetails(String url) throws CrawlerException;

    default Map<String, String> generateAttributesGoalkeeper() {
        Map<String, String> attributes = new HashMap<>();

        attributes.put(CardUtil.DIVING, "gkdiving-val-0");
        attributes.put(CardUtil.HANDLING, "gkhandling-val-0");
        attributes.put(CardUtil.KICKING, "gkkicking-val-0");
        attributes.put(CardUtil.POSITIONNING_GOALKEEPER, "gkreflexes-val-0");
        attributes.put(CardUtil.REFLEXES_GOALKEEPER, "speed-val-0");
        attributes.put(CardUtil.SPEED, "gkpositioning-val-0");

        return attributes;
    }

    default Map<String, String> generateAttributesPlayerMap() {
        Map<String, String> attributes = new HashMap<>();

        attributes.put(CardUtil.ACCELERATION, "acceleration-val-0");
        attributes.put(CardUtil.SPRINT_SPEED, "sprintspeed-val-0");
        attributes.put(CardUtil.POSITIONING, "positioning-val-0");
        attributes.put(CardUtil.FINISHING, "finishing-val-0");
        attributes.put(CardUtil.SHOT_POWER, "shotpower-val-0");
        attributes.put(CardUtil.LONG_SHOTS, "longshotsaccuracy-val-0");
        attributes.put(CardUtil.VOLLEYS, "volleys-val-0");
        attributes.put(CardUtil.PENALTIES, "penalties-val-0");
        attributes.put(CardUtil.VISION, "vision-val-0");
        attributes.put(CardUtil.CROSSING, "crossing-val-0");
        attributes.put(CardUtil.FK_ACCURACY, "freekickaccuracy-val-0");
        attributes.put(CardUtil.SHORT_PASSING, "shortpassing-val-0");
        attributes.put(CardUtil.LONG_PASSING, "longpassing-val-0");
        attributes.put(CardUtil.CURVE, "curve-val-0");
        attributes.put(CardUtil.AGILITY, "agility-val-0");
        attributes.put(CardUtil.BALANCE, "balance-val-0");
        attributes.put(CardUtil.REACTIONS, "reactions-val-0");
        attributes.put(CardUtil.BALL_CONTROL, "ballcontrol-val-0");
        attributes.put(CardUtil.DRIBBLING, "dribbling-val-0");
        attributes.put(CardUtil.COMPOSURE, "composure-val-0");
        attributes.put(CardUtil.INTERCEPTIONS, "interceptions-val-0");
        attributes.put(CardUtil.HEADING_ACCURACY, "headingaccuracy-val-0");
        attributes.put(CardUtil.MARKING, "marking-val-0");
        attributes.put(CardUtil.STADING_TACKLE, "standingtackle-val-0");
        attributes.put(CardUtil.SLIDING_TACKLE, "slidingtackle-val-0");
        attributes.put(CardUtil.JUMPING, "jumping-val-0");
        attributes.put(CardUtil.STAMINA, "stamina-val-0");
        attributes.put(CardUtil.STRENGTH, "strength-val-0");
        attributes.put(CardUtil.AGGRESSION, "aggression-val-0");

        return attributes;
    }
}
