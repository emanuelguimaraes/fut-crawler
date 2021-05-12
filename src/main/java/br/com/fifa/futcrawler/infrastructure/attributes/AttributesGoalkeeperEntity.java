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
    private int diving = 1;

    @Column(name = "JOGO_DE_MAOS")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Jogo de Mãos")
    private int handling = 1;

    @Column(name = "PONTAPE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Pontapé")
    private int kicking = 1;

    @Column(name = "POSICIONAMENTO_GOLEIRO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Posicionamento")
    private int positionning = 1;

    @Column(name = "REFLEXOS_GOLEIRO")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Reflexos")
    private int reflexes = 1;

    @Column(name = "VELOCIDADE")
    @Size(min = 1, max = 99, message = "Valor inválido para o atributo de Velocidade")
    private int speed = 1;

    public AttributesGoalkeeperEntity() {

    }

    public AttributesGoalkeeperEntity(CardEntity card, Goalkeeper attributes) {
        super(card);
        this.diving = attributes.getDiving();
        this.handling = attributes.getHandling();
        this.kicking = attributes.getKicking();
        this.positionning = attributes.getPositionning();
        this.reflexes = attributes.getReflexes();
        this.speed = attributes.getSpeed();
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

    public int getPositionning() {
        return positionning;
    }

    public int getReflexes() {
        return reflexes;
    }

    public int getSpeed() {
        return speed;
    }
}
