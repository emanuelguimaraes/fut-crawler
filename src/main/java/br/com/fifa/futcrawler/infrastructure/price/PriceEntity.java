package br.com.fifa.futcrawler.infrastructure.price;

import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.price.Price;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRECO")
@SequenceGenerator(name = "PRECO_GENERATOR", sequenceName = "SEQ_PRECO", initialValue = 1, allocationSize = 1)
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRECO_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "VALOR_ATUAL", nullable = false)
    private BigDecimal currentValue;

    @Column(name = "VALOR_MINIMO", nullable = false)
    private BigDecimal minValue;

    @Column(name = "VALOR_MAXIMO", nullable = false)
    private BigDecimal maxValue;

    @Column(name = "CRIADO_EM", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "ATUALIZADO_EM", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARTAO")
    private CardEntity card;

    public PriceEntity() { }

    public PriceEntity(CardEntity card, Price price) {
        this.card = card;
        this.currentValue = price.getCurrentValue();
        this.minValue = price.getMinValue();
        this.maxValue = price.getMaxValue();
    }

    public void updateValues(Price price) {
        this.currentValue = price.getCurrentValue();
        this.minValue = price.getMinValue();
        this.maxValue = price.getMaxValue();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public CardEntity getCard() {
        return card;
    }
}
