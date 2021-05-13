package br.com.fifa.futcrawler.application.price;

import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;

import java.math.BigDecimal;

public class GetCardPrice {

    private final CardRepository repository;
    private final FutExternalApi futApi;

    public GetCardPrice(CardRepository repository, FutExternalApi futApi) {
        this.repository = repository;
        this.futApi = futApi;
    }

    public BigDecimal execute(Long idCard, String console) {
        Card card = repository
                .findById(idCard)
                .orElseThrow(() -> new RuntimeException("Cartão informado não foi encontrado"));

        return futApi.getCardPrice(card.getBiography().getIdResource(), console);
    }
}
