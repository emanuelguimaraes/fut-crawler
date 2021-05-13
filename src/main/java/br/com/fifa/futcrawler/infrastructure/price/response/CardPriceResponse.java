package br.com.fifa.futcrawler.infrastructure.price.response;

import java.math.BigDecimal;

public class CardPriceResponse {

    private BigDecimal price;

    public CardPriceResponse(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
