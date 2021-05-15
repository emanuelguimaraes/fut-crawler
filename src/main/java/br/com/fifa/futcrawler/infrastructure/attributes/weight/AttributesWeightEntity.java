package br.com.fifa.futcrawler.infrastructure.attributes.weight;

import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;
import br.com.fifa.futcrawler.domain.attributes.weight.WeightGoalkeeper;
import br.com.fifa.futcrawler.domain.attributes.weight.WeightPlayer;
import br.com.fifa.futcrawler.domain.position.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PESO_ATRIBUTOS")
@SequenceGenerator(name = "PESO_ATRIB_GENERATOR", sequenceName = "SEQ_PESO_ATRIBUTOS", initialValue = 1, allocationSize = 1)
public class AttributesWeightEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESO_ATRIB_GENERATOR")
    private Long id;

    @Column(name = "POSICAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role position;

    @Column(name = "ACELERACAO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Aceleração")
    private BigDecimal acceleration = BigDecimal.ZERO;

    @Column(name = "SPRINT", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Sprint")
    private BigDecimal sprintSpeed = BigDecimal.ZERO;

    @Column(name = "POSICIONAMENTO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Posicionamento")
    private BigDecimal positioning = BigDecimal.ZERO;

    @Column(name = "FINALIZACAO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Finalização")
    private BigDecimal finishing = BigDecimal.ZERO;

    @Column(name = "POTENCIA", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Potência")
    private BigDecimal shotPower = BigDecimal.ZERO;

    @Column(name = "CHUTE_DE_LONGE", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Chute de Longe")
    private BigDecimal longShots = BigDecimal.ZERO;

    @Column(name = "CHUTES_ACROBATICOS", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Chutes Acrobáticos")
    private BigDecimal volleys = BigDecimal.ZERO;

    @Column(name = "PENALTI", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Pênalti")
    private BigDecimal penalties = BigDecimal.ZERO;

    @Column(name = "VISAO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Visão")
    private BigDecimal vision = BigDecimal.ZERO;

    @Column(name = "CRUZAMENTO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Cruzamento")
    private BigDecimal crossing = BigDecimal.ZERO;

    @Column(name = "COBRANCAO_FALTA", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Cobrança de Falta")
    private BigDecimal fkAccuracy = BigDecimal.ZERO;

    @Column(name = "PASSE_CURTO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Passe Curto")
    private BigDecimal shortPassing = BigDecimal.ZERO;

    @Column(name = "PASSE_LONGO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Passe Longo")
    private BigDecimal longPassing = BigDecimal.ZERO;

    @Column(name = "EFEITO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Efeito")
    private BigDecimal curve = BigDecimal.ZERO;

    @Column(name = "AGILIDADE", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Agilidade")
    private BigDecimal agility = BigDecimal.ZERO;

    @Column(name = "EQUILIBRIO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Equilíbrio")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "REFLEXOS", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Reflexos")
    private BigDecimal reactions = BigDecimal.ZERO;

    @Column(name = "CONTROLE_DE_BOLA", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Controle de Bola")
    private BigDecimal ballControl = BigDecimal.ZERO;

    @Column(name = "DRIBLE", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Drible")
    private BigDecimal dribbling = BigDecimal.ZERO;

    @Column(name = "COMPOSTURA", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Compostura")
    private BigDecimal composure = BigDecimal.ZERO;

    @Column(name = "INTERCEPTACAO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Interceptação")
    private BigDecimal interceptions = BigDecimal.ZERO;

    @Column(name = "CABECEAMENTO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Cabeceamento")
    private BigDecimal headingAccuracy = BigDecimal.ZERO;

    @Column(name = "MARCACAO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Marcação")
    private BigDecimal marking = BigDecimal.ZERO;

    @Column(name = "CARRINHO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Carrinho")
    private BigDecimal stadingTackle = BigDecimal.ZERO;

    @Column(name = "CORTE_EM_PE", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Corte em Pé")
    private BigDecimal slidingTackle = BigDecimal.ZERO;

    @Column(name = "SALTO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Salto")
    private BigDecimal jumping = BigDecimal.ZERO;

    @Column(name = "RESISTENCIA", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Resistência")
    private BigDecimal stamina = BigDecimal.ZERO;

    @Column(name = "FORCA", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Força")
    private BigDecimal strength = BigDecimal.ZERO;

    @Column(name = "AGRESSIVIDADE", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Agressividade")
    private BigDecimal aggression = BigDecimal.ZERO;

    @Column(name = "MERGULHO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Mergulho")
    private BigDecimal diving = BigDecimal.ZERO;

    @Column(name = "JOGO_DE_MAOS", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Jogo de Mãos")
    private BigDecimal handling = BigDecimal.ZERO;

    @Column(name = "PONTAPE", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Pontapé")
    private BigDecimal kicking = BigDecimal.ZERO;

    @Column(name = "POSICIONAMENTO_GOLEIRO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Posicionamento do Goleiro")
    private BigDecimal positionningGoalkeeper = BigDecimal.ZERO;

    @Column(name = "REFLEXOS_GOLEIRO", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Reflexos do Goleiro")
    private BigDecimal reflexesGoalkeeper = BigDecimal.ZERO;

    @Column(name = "VELOCIDADE", nullable = false)
    @Size(min = 0, max = 1, message = "Valor inválido para o atributo de Velocidade")
    private BigDecimal speed = BigDecimal.ZERO;

    public AttributesWeightEntity() { }

    public AttributesWeightEntity(AttributesWeight weight) {
        this.position = weight.getPosition();

        if (Role.GK.equals(weight.getPosition())) {
            this.diving = ((WeightGoalkeeper) weight).getDiving();
            this.handling = ((WeightGoalkeeper) weight).getHandling();
            this.kicking = ((WeightGoalkeeper) weight).getKicking();
            this.positionningGoalkeeper = ((WeightGoalkeeper) weight).getPositionning();
            this.reflexesGoalkeeper = ((WeightGoalkeeper) weight).getReflexes();
            this.speed = ((WeightGoalkeeper) weight).getSpeed();
        } else {
            this.acceleration = ((WeightPlayer) weight).getPace().getAcceleration();
            this.sprintSpeed = ((WeightPlayer) weight).getPace().getSprintSpeed();
            this.positioning = ((WeightPlayer) weight).getShooting().getPositioning();
            this.finishing = ((WeightPlayer) weight).getShooting().getFinishing();
            this.shotPower = ((WeightPlayer) weight).getShooting().getShotPower();
            this.longShots = ((WeightPlayer) weight).getShooting().getLongShots();
            this.volleys = ((WeightPlayer) weight).getShooting().getVolleys();
            this.penalties = ((WeightPlayer) weight).getShooting().getPenalties();
            this.vision = ((WeightPlayer) weight).getPassing().getVision();
            this.crossing = ((WeightPlayer) weight).getPassing().getCrossing();
            this.fkAccuracy = ((WeightPlayer) weight).getPassing().getFkAccuracy();
            this.shortPassing = ((WeightPlayer) weight).getPassing().getShortPassing();
            this.longPassing = ((WeightPlayer) weight).getPassing().getLongPassing();
            this.curve = ((WeightPlayer) weight).getPassing().getCurve();
            this.agility = ((WeightPlayer) weight).getDribbling().getAgility();
            this.balance = ((WeightPlayer) weight).getDribbling().getBalance();
            this.reactions = ((WeightPlayer) weight).getDribbling().getReactions();
            this.ballControl = ((WeightPlayer) weight).getDribbling().getBallControl();
            this.dribbling = ((WeightPlayer) weight).getDribbling().getDribbling();
            this.composure = ((WeightPlayer) weight).getDribbling().getComposure();
            this.interceptions = ((WeightPlayer) weight).getDefending().getInterceptions();
            this.headingAccuracy = ((WeightPlayer) weight).getDefending().getHeadingAccuracy();
            this.marking = ((WeightPlayer) weight).getDefending().getMarking();
            this.stadingTackle = ((WeightPlayer) weight).getDefending().getStadingTackle();
            this.slidingTackle = ((WeightPlayer) weight).getDefending().getSlidingTackle();
            this.jumping = ((WeightPlayer) weight).getPhysicality().getJumping();
            this.stamina = ((WeightPlayer) weight).getPhysicality().getStamina();
            this.strength = ((WeightPlayer) weight).getPhysicality().getStrength();
            this.aggression = ((WeightPlayer) weight).getPhysicality().getAggression();
        }
    }

    public Long getId() {
        return id;
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
