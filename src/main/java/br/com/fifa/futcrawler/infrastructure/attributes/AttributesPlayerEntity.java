package br.com.fifa.futcrawler.infrastructure.attributes;

import br.com.fifa.futcrawler.domain.position.Player;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "JOGADOR")
public class AttributesPlayerEntity extends AttributesEntity {

    @Column(name = "ACELERACAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Aceleração")
    private int acceleration = 1;

    @Column(name = "SPRINT")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Sprint")
    private int sprintSpeed = 1;

    @Column(name = "POSICIONAMENTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Posicionamento")
    private int positioning = 1;

    @Column(name = "FINALIZACAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Finalização")
    private int finishing = 1;

    @Column(name = "POTENCIA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Potência")
    private int shotPower = 1;

    @Column(name = "CHUTE_DE_LONGE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Chute de Longe")
    private int longShots = 1;

    @Column(name = "CHUTES_ACROBATICOS")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Chutes Acrobáticos")
    private int volleys = 1;

    @Column(name = "PENALTI")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Pênalti")
    private int penalties = 1;

    @Column(name = "VISAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Visão")
    private int vision = 1;

    @Column(name = "CRUZAMENTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Cruzamento")
    private int crossing = 1;

    @Column(name = "COBRANCAO_FALTA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Cobrança de Falta")
    private int fkAccuracy = 1;

    @Column(name = "PASSE_CURTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Passe Curto")
    private int shortPassing = 1;

    @Column(name = "PASSE_LONGO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Passe Longo")
    private int longPassing = 1;

    @Column(name = "EFEITO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Efeito")
    private int curve = 1;

    @Column(name = "AGILIDADE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Agilidade")
    private int agility = 1;

    @Column(name = "EQUILIBRIO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Equilíbrio")
    private int balance = 1;

    @Column(name = "REFLEXOS")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Reflexos")
    private int reactions = 1;

    @Column(name = "CONTROLE_DE_BOLA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Controle de Bola")
    private int ballControl = 1;

    @Column(name = "DRIBLE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Drible")
    private int dribbling = 1;

    @Column(name = "COMPOSTURA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Compostura")
    private int composure = 1;

    @Column(name = "INTERCEPTACAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Interceptação")
    private int interceptions = 1;

    @Column(name = "CABECEAMENTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Cabeceamento")
    private int headingAccuracy = 1;

    @Column(name = "MARCACAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Marcação")
    private int marking = 1;

    @Column(name = "CARRINHO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Carrinho")
    private int stadingTackle = 1;

    @Column(name = "CORTE_EM_PE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Corte em Pé")
    private int slidingTackle = 1;

    @Column(name = "SALTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Salto")
    private int jumping = 1;

    @Column(name = "RESISTENCIA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Resistência")
    private int stamina = 1;

    @Column(name = "FORCA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Força")
    private int strength = 1;

    @Column(name = "AGRESSIVIDADE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Agressividade")
    private int aggression = 1;

    public AttributesPlayerEntity() {

    }

    public AttributesPlayerEntity(CardEntity card, Player attributes) {
        super(card);
        this.acceleration = attributes.getPace().getAcceleration();
        this.sprintSpeed = attributes.getPace().getSprintSpeed();
        this.positioning = attributes.getShooting().getPositioning();
        this.finishing = attributes.getShooting().getFinishing();
        this.shotPower = attributes.getShooting().getShotPower();
        this.longShots = attributes.getShooting().getLongShots();
        this.volleys = attributes.getShooting().getVolleys();
        this.penalties = attributes.getShooting().getPenalties();
        this.vision = attributes.getPassing().getVision();
        this.crossing = attributes.getPassing().getCrossing();
        this.fkAccuracy = attributes.getPassing().getFkAccuracy();
        this.shortPassing = attributes.getPassing().getShortPassing();
        this.longPassing = attributes.getPassing().getLongPassing();
        this.curve = attributes.getPassing().getCurve();
        this.agility = attributes.getDribbling().getAgility();
        this.balance = attributes.getDribbling().getBalance();
        this.reactions = attributes.getDribbling().getReactions();
        this.ballControl = attributes.getDribbling().getBallControl();
        this.dribbling = attributes.getDribbling().getDribbling();
        this.composure = attributes.getDribbling().getComposure();
        this.interceptions = attributes.getDefending().getInterceptions();
        this.headingAccuracy = attributes.getDefending().getHeadingAccuracy();
        this.marking = attributes.getDefending().getMarking();
        this.stadingTackle = attributes.getDefending().getStadingTackle();
        this.slidingTackle = attributes.getDefending().getSlidingTackle();
        this.jumping = attributes.getPhysicality().getJumping();
        this.stamina = attributes.getPhysicality().getStamina();
        this.strength = attributes.getPhysicality().getStrength();
        this.aggression = attributes.getPhysicality().getAggression();
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
}
