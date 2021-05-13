package br.com.fifa.futcrawler.application.price;

import java.math.BigDecimal;

public interface FutExternalApi {

    BigDecimal getCardPrice(Long idCard, String console);
}
