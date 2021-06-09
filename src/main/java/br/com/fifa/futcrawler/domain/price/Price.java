package br.com.fifa.futcrawler.domain.price;

import java.math.BigDecimal;

public class Price {

    private BigDecimal currentValue;
    private BigDecimal minValue;
    private BigDecimal maxValue;

    public Price(BigDecimal currentValue, BigDecimal minValue, BigDecimal maxValue) {
        this.currentValue = currentValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Price() {
        this.currentValue = BigDecimal.ZERO;
        this.minValue = BigDecimal.ZERO;
        this.maxValue = BigDecimal.ZERO;
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
}
