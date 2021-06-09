package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.application.price.FutExternalApi;
import br.com.fifa.futcrawler.application.price.GetCardPrice;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.price.Price;

import java.util.ArrayList;
import java.util.List;

public class UpdateCardsPrice {

    private final CardRepository repository;
    private final GetCardPrice getCardPriceService;

    public UpdateCardsPrice(CardRepository repository, FutExternalApi futApi) {
        this.repository = repository;
        this.getCardPriceService = new GetCardPrice(repository, futApi);
    }

    public List<Card> execute(Long initialId, Long finalId, String console) {
        List<Card> cardsResponse = new ArrayList<>();
        List<Long> idCards = repository.findAllOnlyIds(initialId, finalId);

        for (Long idCard : idCards) {
            Price price = getCardPriceService.execute(idCard, console);
            cardsResponse.add(repository.updatePrice(idCard, price));
        }

        return cardsResponse;
    }
}
