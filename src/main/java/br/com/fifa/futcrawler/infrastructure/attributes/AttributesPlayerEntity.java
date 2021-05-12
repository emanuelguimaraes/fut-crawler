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
    private int aceleracao = 1;

    @Column(name = "SPRINT")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Sprint")
    private int sprint = 1;

    @Column(name = "POSICIONAMENTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Posicionamento")
    private int posicionamento = 1;

    @Column(name = "FINALIZACAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Finalização")
    private int finalizacao = 1;

    @Column(name = "POTENCIA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Potência")
    private int potencia = 1;

    @Column(name = "CHUTE_DE_LONGE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Chute de Longe")
    private int chuteDeLonge = 1;

    @Column(name = "CHUTES_ACROBATICOS")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Chutes Acrobáticos")
    private int chutesAcrobaticos = 1;

    @Column(name = "PENALTI")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Pênalti")
    private int penalti = 1;

    @Column(name = "VISAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Visão")
    private int visao = 1;

    @Column(name = "CRUZAMENTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Cruzamento")
    private int cruzamento = 1;

    @Column(name = "COBRANCAO_FALTA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Cobrança de Falta")
    private int cobrancaFalta = 1;

    @Column(name = "PASSE_CURTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Passe Curto")
    private int passeCurto = 1;

    @Column(name = "PASSE_LONGO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Passe Longo")
    private int passeLongo = 1;

    @Column(name = "EFEITO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Efeito")
    private int efeito = 1;

    @Column(name = "AGILIDADE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Agilidade")
    private int agilidade = 1;

    @Column(name = "EQUILIBRIO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Equilíbrio")
    private int equilibrio = 1;

    @Column(name = "REFLEXOS")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Reflexos")
    private int reflexos = 1;

    @Column(name = "CONTROLE_DE_BOLA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Controle de Bola")
    private int controleDeBola = 1;

    @Column(name = "DRIBLE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Drible")
    private int drible = 1;

    @Column(name = "COMPOSTURA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Compostura")
    private int compostura = 1;

    @Column(name = "INTERCEPTACAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Interceptação")
    private int interceptacao = 1;

    @Column(name = "CABECEAMENTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Cabeceamento")
    private int cabeceamento = 1;

    @Column(name = "MARCACAO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Marcação")
    private int marcacao = 1;

    @Column(name = "CARRINHO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Carrinho")
    private int carrinho = 1;

    @Column(name = "CORTE_EM_PE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Corte em Pé")
    private int corteEmPe = 1;

    @Column(name = "SALTO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Salto")
    private int salto = 1;

    @Column(name = "RESISTENCIA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Resistência")
    private int resistencia = 1;

    @Column(name = "FORCA")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Força")
    private int forca = 1;

    @Column(name = "AGRESSIVIDADE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Agressividade")
    private int agressividade = 1;

    public AttributesPlayerEntity() {

    }

    public AttributesPlayerEntity(CardEntity cartao, Player atributos) {
        super(cartao);
        this.aceleracao = atributos.getPace().getAcceleration();
        this.sprint = atributos.getPace().getSprintSpeed();
        this.posicionamento = atributos.getShooting().getPositioning();
        this.finalizacao = atributos.getShooting().getFinishing();
        this.potencia = atributos.getShooting().getShotPower();
        this.chuteDeLonge = atributos.getShooting().getLongShots();
        this.chutesAcrobaticos = atributos.getShooting().getVolleys();
        this.penalti = atributos.getShooting().getPenalties();
        this.visao = atributos.getPassing().getVision();
        this.cruzamento = atributos.getPassing().getCrossing();
        this.cobrancaFalta = atributos.getPassing().getFkAccuracy();
        this.passeCurto = atributos.getPassing().getShortPassing();
        this.passeLongo = atributos.getPassing().getLongPassing();
        this.efeito = atributos.getPassing().getCurve();
        this.agilidade = atributos.getDribbling().getAgility();
        this.equilibrio = atributos.getDribbling().getBalance();
        this.reflexos = atributos.getDribbling().getReactions();
        this.controleDeBola = atributos.getDribbling().getBallControl();
        this.drible = atributos.getDribbling().getDribbling();
        this.compostura = atributos.getDribbling().getComposure();
        this.interceptacao = atributos.getDefending().getInterceptions();
        this.cabeceamento = atributos.getDefending().getHeadingAccuracy();
        this.marcacao = atributos.getDefending().getMarking();
        this.carrinho = atributos.getDefending().getStadingTackle();
        this.corteEmPe = atributos.getDefending().getSlidingTackle();
        this.salto = atributos.getPhysicality().getJumping();
        this.resistencia = atributos.getPhysicality().getStamina();
        this.forca = atributos.getPhysicality().getStrength();
        this.agressividade = atributos.getPhysicality().getAggression();
    }

    public int getAceleracao() {
        return aceleracao;
    }

    public int getSprint() {
        return sprint;
    }

    public int getPosicionamento() {
        return posicionamento;
    }

    public int getFinalizacao() {
        return finalizacao;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getChuteDeLonge() {
        return chuteDeLonge;
    }

    public int getChutesAcrobaticos() {
        return chutesAcrobaticos;
    }

    public int getPenalti() {
        return penalti;
    }

    public int getVisao() {
        return visao;
    }

    public int getCruzamento() {
        return cruzamento;
    }

    public int getCobrancaFalta() {
        return cobrancaFalta;
    }

    public int getPasseCurto() {
        return passeCurto;
    }

    public int getPasseLongo() {
        return passeLongo;
    }

    public int getEfeito() {
        return efeito;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getEquilibrio() {
        return equilibrio;
    }

    public int getReflexos() {
        return reflexos;
    }

    public int getControleDeBola() {
        return controleDeBola;
    }

    public int getDrible() {
        return drible;
    }

    public int getCompostura() {
        return compostura;
    }

    public int getInterceptacao() {
        return interceptacao;
    }

    public int getCabeceamento() {
        return cabeceamento;
    }

    public int getMarcacao() {
        return marcacao;
    }

    public int getCarrinho() {
        return carrinho;
    }

    public int getCorteEmPe() {
        return corteEmPe;
    }

    public int getSalto() {
        return salto;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getForca() {
        return forca;
    }

    public int getAgressividade() {
        return agressividade;
    }
}
