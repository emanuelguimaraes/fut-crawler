package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.application.card.response.CardResponse;
import br.com.fifa.futcrawler.application.price.FutExternalApi;
import br.com.fifa.futcrawler.application.price.GetCardPrice;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.card.util.CardUtil;
import br.com.fifa.futcrawler.domain.position.Role;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LargestOverallsByPosition {

    private static final int ITENS_PER_PAGE = 10;
    private static final int NUMBER_ONE = 1;

    private final CardRepository repository;
    private final GetCardPrice getCardPriceService;

    public LargestOverallsByPosition(CardRepository repository, FutExternalApi futApi) {
        this.repository = repository;
        this.getCardPriceService = new GetCardPrice(repository, futApi);
    }

    public List<CardResponse> execute(Role position, String console, int page) {
        List<CardResponse> cardsResponse = repository
                .findAllByAttributesType(CardUtil.getAttributesType(position), position, page)
                .stream()
                .map(card -> new CardResponse(
                        card.getId(),
                        card.getName(),
                        card.getClub(),
                        card.getLeague(),
                        card.getPosition(),
                        card.getRevision(),
                        card.getOverall()
                ))
                .collect(Collectors.toList());

        cardsResponse.forEach(cardResponse -> cardResponse.addPrice(
                getCardPriceService.execute(cardResponse.getId(), console)));

        return cardsResponse;
    }

    private int getFromIndex(int page) {
        return (page - NUMBER_ONE) * ITENS_PER_PAGE;
    }

    private int getToIndex(int page) {
        return (ITENS_PER_PAGE * page);
    }
}
