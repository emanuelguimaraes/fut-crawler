package br.com.fifa.futcrawler.application.price;

import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.price.Price;

public class GetCardPrice {

    private final CardRepository repository;
    private final FutExternalApi futApi;

    public GetCardPrice(CardRepository repository, FutExternalApi futApi) {
        this.repository = repository;
        this.futApi = futApi;
    }

    public Price execute(Long idCard, String console) {
        Long resourceId = repository.findOnlyResourceId(idCard);
        return futApi.getCardPrice(resourceId, console);
    }
}
