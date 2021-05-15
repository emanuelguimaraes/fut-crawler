package br.com.fifa.futcrawler.application.crawler.dto;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.biography.Biography;
import br.com.fifa.futcrawler.domain.biography.Foot;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.util.CardUtil;
import br.com.fifa.futcrawler.domain.club.Club;
import br.com.fifa.futcrawler.domain.club.League;
import br.com.fifa.futcrawler.domain.position.Goalkeeper;
import br.com.fifa.futcrawler.domain.position.Player;
import br.com.fifa.futcrawler.domain.position.Position;
import br.com.fifa.futcrawler.domain.position.Role;

import java.util.Map;

public class CardDetailsDTO {

    private String name;
    private int height;
    private int weight;
    private String nation;
    private String revision;
    private int skills;
    private Foot foot;
    private int weakFoot;
    private Role position;
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
    private String club;
    private String league;
    private Long idResource;

    public CardDetailsDTO(Map<String, String> fields) {
        name = parseString(fields.get(CardUtil.NAME));
        height = parseHeight(fields.get(CardUtil.HEIGHT));
        weight = parseInt(fields.get(CardUtil.WEIGHT));
        nation = parseString(fields.get(CardUtil.NATION));
        revision = parseString(fields.get(CardUtil.REVISION));
        skills = parseInt(fields.get(CardUtil.SKILLS));
        foot = parsePerna(fields.get(CardUtil.FOOT));
        weakFoot = parseInt(fields.get(CardUtil.WEAK_FOOT));
        position = parseFuncao(fields.get(CardUtil.POSITION));
        club = parseString(fields.get(CardUtil.CLUB));
        league = parseString(fields.get(CardUtil.LEAGUE));
        idResource = parseLong(fields.get(CardUtil.ID_RESOURCE));

        if (position.equals(Role.GK)) {
            diving = parseInt(fields.get(CardUtil.DIVING));
            handling = parseInt(fields.get(CardUtil.HANDLING));
            kicking = parseInt(fields.get(CardUtil.KICKING));
            positionningGoalkeeper = parseInt(fields.get(CardUtil.POSITIONNING_GOALKEEPER));
            reflexesGoalkeeper = parseInt(fields.get(CardUtil.REFLEXES_GOALKEEPER));
            speed = parseInt(fields.get(CardUtil.SPEED));
        } else {
            acceleration = parseInt(fields.get(CardUtil.ACCELERATION));
            sprintSpeed = parseInt(fields.get(CardUtil.SPRINT_SPEED));
            positioning = parseInt(fields.get(CardUtil.POSITIONING));
            finishing = parseInt(fields.get(CardUtil.FINISHING));
            shotPower = parseInt(fields.get(CardUtil.SHOT_POWER));
            longShots = parseInt(fields.get(CardUtil.LONG_SHOTS));
            volleys = parseInt(fields.get(CardUtil.VOLLEYS));
            penalties = parseInt(fields.get(CardUtil.PENALTIES));
            vision = parseInt(fields.get(CardUtil.VISION));
            crossing = parseInt(fields.get(CardUtil.CROSSING));
            fkAccuracy = parseInt(fields.get(CardUtil.FK_ACCURACY));
            shortPassing = parseInt(fields.get(CardUtil.SHORT_PASSING));
            longPassing = parseInt(fields.get(CardUtil.LONG_PASSING));
            curve = parseInt(fields.get(CardUtil.CURVE));
            agility = parseInt(fields.get(CardUtil.AGILITY));
            balance = parseInt(fields.get(CardUtil.BALANCE));
            reactions = parseInt(fields.get(CardUtil.REACTIONS));
            ballControl = parseInt(fields.get(CardUtil.BALL_CONTROL));
            dribbling = parseInt(fields.get(CardUtil.DRIBBLING));
            composure = parseInt(fields.get(CardUtil.COMPOSURE));
            interceptions = parseInt(fields.get(CardUtil.INTERCEPTIONS));
            headingAccuracy = parseInt(fields.get(CardUtil.HEADING_ACCURACY));
            marking = parseInt(fields.get(CardUtil.MARKING));
            stadingTackle = parseInt(fields.get(CardUtil.STADING_TACKLE));
            slidingTackle = parseInt(fields.get(CardUtil.SLIDING_TACKLE));
            jumping = parseInt(fields.get(CardUtil.JUMPING));
            stamina = parseInt(fields.get(CardUtil.STAMINA));
            strength = parseInt(fields.get(CardUtil.STRENGTH));
            aggression = parseInt(fields.get(CardUtil.AGGRESSION));
        }
    }

    public Card parseFromCartao() {
        Card card = new Card(null,
                this.name,
                createBiography(),
                createPosition(),
                createClub());

        card.getBiography().setIdResource(this.idResource);

        return card;
    }

    private Biography createBiography() {
        return new Biography(
                this.height,
                this.weight,
                this.nation,
                this.revision,
                this.skills,
                this.foot,
                this.weakFoot);
    }

    private Position createPosition() {
        if (Role.GK.equals(this.position)) {
            return new Goalkeeper(
                    this.diving,
                    this.handling,
                    this.kicking,
                    this.positionningGoalkeeper,
                    this.reflexesGoalkeeper,
                    this.speed
            );
        } else {
            return new Player(
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

    private Club createClub() {
        return new Club(
                this.club,
                new League(this.league)
        );
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

    private Integer parseHeight(String altura) {
        try {
            String[] arrayAltura = altura.split("cm");

            return Integer.parseInt(arrayAltura[0].trim());

        } catch (Exception e) {
            return Integer.valueOf(1);
        }
    }

    private Long parseLong(String value) {
        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return Long.valueOf(1L);
        }
    }

    private Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return Integer.valueOf(1);
        }
    }

    private Role parseFuncao(String value) {
        try {
            return Role.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Role.NAN;
        }
    }

    private Foot parsePerna(String value) {
        try {
            return Foot.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Foot.NAN;
        }
    }

    private String parseString(String value) {
        if (value.isBlank()) {
            return "NAN";
        }

        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public int getSkills() {
        return skills;
    }

    public void setSkills(int skills) {
        this.skills = skills;
    }

    public Foot getFoot() {
        return foot;
    }

    public void setFoot(Foot foot) {
        this.foot = foot;
    }

    public int getWeakFoot() {
        return weakFoot;
    }

    public void setWeakFoot(int weakFoot) {
        this.weakFoot = weakFoot;
    }

    public Role getPosition() {
        return position;
    }

    public void setPosition(Role position) {
        this.position = position;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getSprintSpeed() {
        return sprintSpeed;
    }

    public void setSprintSpeed(int sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }

    public int getPositioning() {
        return positioning;
    }

    public void setPositioning(int positioning) {
        this.positioning = positioning;
    }

    public int getFinishing() {
        return finishing;
    }

    public void setFinishing(int finishing) {
        this.finishing = finishing;
    }

    public int getShotPower() {
        return shotPower;
    }

    public void setShotPower(int shotPower) {
        this.shotPower = shotPower;
    }

    public int getLongShots() {
        return longShots;
    }

    public void setLongShots(int longShots) {
        this.longShots = longShots;
    }

    public int getVolleys() {
        return volleys;
    }

    public void setVolleys(int volleys) {
        this.volleys = volleys;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getCrossing() {
        return crossing;
    }

    public void setCrossing(int crossing) {
        this.crossing = crossing;
    }

    public int getFkAccuracy() {
        return fkAccuracy;
    }

    public void setFkAccuracy(int fkAccuracy) {
        this.fkAccuracy = fkAccuracy;
    }

    public int getShortPassing() {
        return shortPassing;
    }

    public void setShortPassing(int shortPassing) {
        this.shortPassing = shortPassing;
    }

    public int getLongPassing() {
        return longPassing;
    }

    public void setLongPassing(int longPassing) {
        this.longPassing = longPassing;
    }

    public int getCurve() {
        return curve;
    }

    public void setCurve(int curve) {
        this.curve = curve;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getReactions() {
        return reactions;
    }

    public void setReactions(int reactions) {
        this.reactions = reactions;
    }

    public int getBallControl() {
        return ballControl;
    }

    public void setBallControl(int ballControl) {
        this.ballControl = ballControl;
    }

    public int getDribbling() {
        return dribbling;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public int getComposure() {
        return composure;
    }

    public void setComposure(int composure) {
        this.composure = composure;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public int getHeadingAccuracy() {
        return headingAccuracy;
    }

    public void setHeadingAccuracy(int headingAccuracy) {
        this.headingAccuracy = headingAccuracy;
    }

    public int getMarking() {
        return marking;
    }

    public void setMarking(int marking) {
        this.marking = marking;
    }

    public int getStadingTackle() {
        return stadingTackle;
    }

    public void setStadingTackle(int stadingTackle) {
        this.stadingTackle = stadingTackle;
    }

    public int getSlidingTackle() {
        return slidingTackle;
    }

    public void setSlidingTackle(int slidingTackle) {
        this.slidingTackle = slidingTackle;
    }

    public int getJumping() {
        return jumping;
    }

    public void setJumping(int jumping) {
        this.jumping = jumping;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAggression() {
        return aggression;
    }

    public void setAggression(int aggression) {
        this.aggression = aggression;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getDiving() {
        return diving;
    }

    public void setDiving(int diving) {
        this.diving = diving;
    }

    public int getHandling() {
        return handling;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }

    public int getKicking() {
        return kicking;
    }

    public void setKicking(int kicking) {
        this.kicking = kicking;
    }

    public int getPositionningGoalkeeper() {
        return positionningGoalkeeper;
    }

    public void setPositionningGoalkeeper(int positionningGoalkeeper) {
        this.positionningGoalkeeper = positionningGoalkeeper;
    }

    public int getReflexesGoalkeeper() {
        return reflexesGoalkeeper;
    }

    public void setReflexesGoalkeeper(int reflexesGoalkeeper) {
        this.reflexesGoalkeeper = reflexesGoalkeeper;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Long getIdResource() {
        return idResource;
    }
}
