package br.com.fifa.futcrawler.infrastructure.attributes.chemistry;

import br.com.fifa.futcrawler.domain.attributes.chemistry.Chemistry;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryGoalkeeper;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryPlayer;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_ESTILO_QUIMICA")
@SequenceGenerator(name = "QUIMICA_GENERATOR", sequenceName = "SEQ_QUIMICA", initialValue = 1, allocationSize = 1)
public class ChemistryEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUIMICA_GENERATOR")
    private Long id;

    @Column(name = "NOME", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChemistryType name;

    @Column(name = "ACELERACAO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Aceleração")
    private int acceleration;

    @Column(name = "SPRINT", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Sprint")
    private int sprintSpeed;

    @Column(name = "POSICIONAMENTO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Posicionamento")
    private int positioning;

    @Column(name = "FINALIZACAO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Finalização")
    private int finishing;

    @Column(name = "POTENCIA", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Potência")
    private int shotPower;

    @Column(name = "CHUTE_DE_LONGE", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Chute de Longe")
    private int longShots;

    @Column(name = "CHUTES_ACROBATICOS", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Chutes Acrobáticos")
    private int volleys;

    @Column(name = "PENALTI", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Pênalti")
    private int penalties;

    @Column(name = "VISAO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Visão")
    private int vision;

    @Column(name = "CRUZAMENTO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Cruzamento")
    private int crossing;

    @Column(name = "COBRANCAO_FALTA", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Cobrança de Falta")
    private int fkAccuracy;

    @Column(name = "PASSE_CURTO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Passe Curto")
    private int shortPassing;

    @Column(name = "PASSE_LONGO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Passe Longo")
    private int longPassing;

    @Column(name = "EFEITO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Efeito")
    private int curve;

    @Column(name = "AGILIDADE", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Agilidade")
    private int agility;

    @Column(name = "EQUILIBRIO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Equilíbrio")
    private int balance;

    @Column(name = "REFLEXOS", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Reflexos")
    private int reactions;

    @Column(name = "CONTROLE_DE_BOLA", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Controle de Bola")
    private int ballControl;

    @Column(name = "DRIBLE", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Drible")
    private int dribbling;

    @Column(name = "COMPOSTURA", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Compostura")
    private int composure;

    @Column(name = "INTERCEPTACAO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Interceptação")
    private int interceptions;

    @Column(name = "CABECEAMENTO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Cabeceamento")
    private int headingAccuracy;

    @Column(name = "MARCACAO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Marcação")
    private int marking;

    @Column(name = "CARRINHO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Carrinho")
    private int stadingTackle;

    @Column(name = "CORTE_EM_PE", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Corte em Pé")
    private int slidingTackle;

    @Column(name = "SALTO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Salto")
    private int jumping;

    @Column(name = "RESISTENCIA", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Resistência")
    private int stamina;

    @Column(name = "FORCA", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Força")
    private int strength;

    @Column(name = "AGRESSIVIDADE", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Agressividade")
    private int aggression;

    @Column(name = "MERGULHO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Mergulho")
    private int diving;

    @Column(name = "JOGO_DE_MAOS", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Jogo de Mãos")
    private int handling;

    @Column(name = "PONTAPE", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Pontapé")
    private int kicking;

    @Column(name = "POSICIONAMENTO_GOLEIRO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Posicionamento do Goleiro")
    private int positioningGoalkeeper;

    @Column(name = "REFLEXOS_GOLEIRO", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Reflexos do Goleiro")
    private int reflexesGoalkeeper;

    @Column(name = "VELOCIDADE", nullable = false)
    @Size(min = 0, max = 15, message = "Valor inválido para o atributo de Velocidade")
    private int speed;

    public ChemistryEntity() { }

    public ChemistryEntity(Chemistry chemistry) {
        this.name = chemistry.getName();
        if (chemistry instanceof ChemistryPlayer) {
            this.acceleration = ((ChemistryPlayer) chemistry).getPace().getAcceleration();
            this.sprintSpeed = ((ChemistryPlayer) chemistry).getPace().getSprintSpeed();
            this.positioning = ((ChemistryPlayer) chemistry).getShooting().getPositioning();
            this.finishing = ((ChemistryPlayer) chemistry).getShooting().getFinishing();
            this.shotPower = ((ChemistryPlayer) chemistry).getShooting().getShotPower();
            this.longShots = ((ChemistryPlayer) chemistry).getShooting().getLongShots();
            this.volleys = ((ChemistryPlayer) chemistry).getShooting().getVolleys();
            this.penalties = ((ChemistryPlayer) chemistry).getShooting().getPenalties();
            this.vision = ((ChemistryPlayer) chemistry).getPassing().getVision();
            this.crossing = ((ChemistryPlayer) chemistry).getPassing().getCrossing();
            this.fkAccuracy = ((ChemistryPlayer) chemistry).getPassing().getFkAccuracy();
            this.shortPassing = ((ChemistryPlayer) chemistry).getPassing().getShortPassing();
            this.longPassing = ((ChemistryPlayer) chemistry).getPassing().getLongPassing();
            this.curve = ((ChemistryPlayer) chemistry).getPassing().getCurve();
            this.agility = ((ChemistryPlayer) chemistry).getDribbling().getAgility();
            this.balance = ((ChemistryPlayer) chemistry).getDribbling().getBalance();
            this.reactions = ((ChemistryPlayer) chemistry).getDribbling().getReactions();
            this.ballControl = ((ChemistryPlayer) chemistry).getDribbling().getBallControl();
            this.dribbling = ((ChemistryPlayer) chemistry).getDribbling().getDribbling();
            this.composure = ((ChemistryPlayer) chemistry).getDribbling().getComposure();
            this.interceptions = ((ChemistryPlayer) chemistry).getDefending().getInterceptions();
            this.headingAccuracy = ((ChemistryPlayer) chemistry).getDefending().getHeadingAccuracy();
            this.marking = ((ChemistryPlayer) chemistry).getDefending().getMarking();
            this.stadingTackle = ((ChemistryPlayer) chemistry).getDefending().getStadingTackle();
            this.slidingTackle = ((ChemistryPlayer) chemistry).getDefending().getSlidingTackle();
            this.jumping = ((ChemistryPlayer) chemistry).getPhysicality().getJumping();
            this.stamina = ((ChemistryPlayer) chemistry).getPhysicality().getStamina();
            this.strength = ((ChemistryPlayer) chemistry).getPhysicality().getStrength();
            this.aggression = ((ChemistryPlayer) chemistry).getPhysicality().getAggression();
        } else {
            this.diving = ((ChemistryGoalkeeper) chemistry).getDiving();
            this.handling = ((ChemistryGoalkeeper) chemistry).getHandling();
            this.kicking = ((ChemistryGoalkeeper) chemistry).getKicking();
            this.positioningGoalkeeper = ((ChemistryGoalkeeper) chemistry).getPositioning();
            this.reflexesGoalkeeper = ((ChemistryGoalkeeper) chemistry).getReflexes();
            this.speed = ((ChemistryGoalkeeper) chemistry).getSpeed();
        }
    }

    public Long getId() {
        return id;
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

    public int getPositioningGoalkeeper() {
        return positioningGoalkeeper;
    }

    public int getReflexesGoalkeeper() {
        return reflexesGoalkeeper;
    }

    public int getSpeed() {
        return speed;
    }
}
