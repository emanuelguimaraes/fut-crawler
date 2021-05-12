package br.com.fifa.futcrawler.infrastructure.attributes;

import br.com.fifa.futcrawler.domain.position.Goalkeeper;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "GOLEIRO")
public class AttributesGoalkeeperEntity extends AttributesEntity {

    @Column(name = "MERGULHO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Mergulho")
    private int mergulho = 1;

    @Column(name = "JOGO_DE_MAOS")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Jogo de Mãos")
    private int jogoDeMaos = 1;

    @Column(name = "PONTAPE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Pontapé")
    private int pontape = 1;

    @Column(name = "POSICIONAMENTO_GOLEIRO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Posicionamento")
    private int posicionamento = 1;

    @Column(name = "REFLEXOS_GOLEIRO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Reflexos")
    private int reflexos = 1;

    @Column(name = "VELOCIDADE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Velocidade")
    private int velocidade = 1;

    public AttributesGoalkeeperEntity() {

    }

    public AttributesGoalkeeperEntity(CardEntity cartao, Goalkeeper atributos) {
        super(cartao);
        this.mergulho = atributos.getDiving();
        this.jogoDeMaos = atributos.getHandling();
        this.pontape = atributos.getKicking();
        this.posicionamento = atributos.getPositionning();
        this.reflexos = atributos.getReflexes();
        this.velocidade = atributos.getSpeed();
    }

    public int getMergulho() {
        return mergulho;
    }

    public int getJogoDeMaos() {
        return jogoDeMaos;
    }

    public int getPontape() {
        return pontape;
    }

    public int getPosicionamento() {
        return posicionamento;
    }

    public int getReflexos() {
        return reflexos;
    }

    public int getVelocidade() {
        return velocidade;
    }
}
