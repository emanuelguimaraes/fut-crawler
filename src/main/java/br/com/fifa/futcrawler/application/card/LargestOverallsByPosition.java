package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.application.attributes.chemistry.BestChemistryByCard;
import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.application.card.response.CardResponse;
import br.com.fifa.futcrawler.application.price.FutExternalApi;
import br.com.fifa.futcrawler.application.price.GetCardPrice;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryRepository;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.card.util.CardUtil;
import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class LargestOverallsByPosition {

    private final CardRepository repository;
    private final GetCardPrice priceService;
    private final BestChemistryByCard chemistryService;

    public LargestOverallsByPosition(CardRepository repository, FutExternalApi futApi,
                                     ChemistryRepository chemistryRepository) {
        this.repository = repository;
        this.priceService = new GetCardPrice(repository, futApi);
        this.chemistryService = new BestChemistryByCard(chemistryRepository);
    }

    public List<CardResponse> execute(OverallsRequest request) {
        List<CardResponse> cardsResponse = repository
                .findAllByAttributesType(request)
                .stream()
                .map(card -> new CardResponse(
                        card.getId(),
                        card.getName(),
                        card.getClub(),
                        card.getLeague(),
                        card.getNation(),
                        card.getPosition(),
                        card.getRevision(),
                        card.getOverall()
                ))
                .collect(Collectors.toList());

        for (CardResponse card : cardsResponse) {
            card.addChemistry(chemistryService.execute(card.getId(), request.getPosition()).getName());
            card.addPrice(priceService.execute(card.getId(), request.getConsole()));
        }

        return cardsResponse
                .stream()
                .filter(card -> {
                    if (request.getPrice() == null) {
                        return true;
                    } else if ((card.getPriceNumber().compareTo(request.getPrice()) <= 0)
                            && (card.getPriceNumber().compareTo(BigDecimal.ZERO) > 0)) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
