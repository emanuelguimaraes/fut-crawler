package br.com.fifa.futcrawler.domain.card.util;

import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;

public class CardUtil {

    public static final String NAME = "name";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String NATION = "nation";
    public static final String REVISION = "revision";
    public static final String SKILLS = "skills";
    public static final String FOOT = "foot";
    public static final String WEAK_FOOT = "weakFoot";
    public static final String POSITION = "position";
    public static final String ACCELERATION = "acceleration";
    public static final String SPRINT_SPEED = "sprintSpeed";
    public static final String POSITIONING = "positioning";
    public static final String FINISHING = "finishing";
    public static final String SHOT_POWER = "shotPower";
    public static final String LONG_SHOTS = "longShots";
    public static final String VOLLEYS = "volleys";
    public static final String PENALTIES = "penalties";
    public static final String VISION = "vision";
    public static final String CROSSING = "crossing";
    public static final String FK_ACCURACY = "fkAccuracy";
    public static final String SHORT_PASSING = "shortPassing";
    public static final String LONG_PASSING = "longPassing";
    public static final String CURVE = "curve";
    public static final String AGILITY = "agility";
    public static final String BALANCE = "balance";
    public static final String REACTIONS = "reactions";
    public static final String BALL_CONTROL = "ballControl";
    public static final String DRIBBLING = "dribbling";
    public static final String COMPOSURE = "composure";
    public static final String INTERCEPTIONS = "interceptions";
    public static final String HEADING_ACCURACY = "headingAccuracy";
    public static final String MARKING = "marking";
    public static final String STADING_TACKLE = "stadingTackle";
    public static final String SLIDING_TACKLE = "slidingTackle";
    public static final String JUMPING = "jumping";
    public static final String STAMINA = "stamina";
    public static final String STRENGTH = "strength";
    public static final String AGGRESSION = "aggression";
    public static final String DIVING = "diving";
    public static final String HANDLING = "handling";
    public static final String KICKING = "kicking";
    public static final String POSITIONNING_GOALKEEPER = "positionningGoalkeeper";
    public static final String REFLEXES_GOALKEEPER = "reflexesGoalkeeper";
    public static final String SPEED = "speed";
    public static final String CLUB = "club";
    public static final String LEAGUE = "league";
    public static final String ID_RESOURCE = "idResource";

    public static final String GOALKEEPER = "GOLEIRO";
    public static final String PLAYER = "JOGADOR";

    public static final BigDecimal TOTAL_ATTRIBUTES_PLAYER = new BigDecimal(29);
    public static final BigDecimal TOTAL_ATTRIBUTES_GOALKEEPER = new BigDecimal(6);

    public static String getAttributesType(Role role) {
        if (Role.GK.equals(role)) {
            return GOALKEEPER;
        } else {
            return PLAYER;
        }
    }
}
