package br.com.fifa.futcrawler.application.price;

import br.com.fifa.futcrawler.domain.price.Price;

public interface FutExternalApi {

    Price getCardPrice(Long idCard, String console);
}
