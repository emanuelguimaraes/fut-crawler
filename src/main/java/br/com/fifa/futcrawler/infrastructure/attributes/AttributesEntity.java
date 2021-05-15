package br.com.fifa.futcrawler.infrastructure.attributes;

import br.com.fifa.futcrawler.infrastructure.card.CardEntity;

import javax.persistence.*;

@Entity
@Table(name = "TB_ATRIBUTOS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "ATRIBUTOS_GENERATOR", sequenceName = "SEQ_ATRIBUTOS", initialValue = 1, allocationSize = 1)
public abstract class AttributesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATRIBUTOS_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARTAO")
    private CardEntity card;

    @Column(name = "TIPO", nullable = false, insertable = false, updatable = false)
    private String type;

    public AttributesEntity() { }

    public AttributesEntity(CardEntity card) {
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public CardEntity getCard() {
        return card;
    }

    public String getType() {
        return type;
    }
}
