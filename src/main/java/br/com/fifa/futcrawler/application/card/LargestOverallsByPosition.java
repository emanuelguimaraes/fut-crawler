package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.application.card.response.CardResponse;
import br.com.fifa.futcrawler.application.price.FutExternalApi;
import br.com.fifa.futcrawler.application.price.GetCardPrice;
import br.com.fifa.futcrawler.domain.card.CardRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class LargestOverallsByPosition {

    private static final double INDEX_CHEMISTRY_TEAM = 0.25;
    private static final double INDEX_CHEMISTRY_PLAYER = 7.5;
    private static final long INDEX_THRUST = 50l;

    private final CardRepository repository;
    private final GetCardPrice priceService;

    public LargestOverallsByPosition(CardRepository repository, FutExternalApi futApi) {
        this.repository = repository;
        this.priceService = new GetCardPrice(repository, futApi);
    }

    public List<CardResponse> execute(OverallsRequest request) {
        return repository
                .findAllByAttributesType(
                        request,
                        maximumThrustValue(request.getChemistryTeam(), request.getChemistryPlayer()))
                .stream()
                .map(card -> new CardResponse(
                        card.getId(),
                        card.getName(),
                        card.getClub(),
                        card.getLeague(),
                        card.getNation(),
                        card.getPosition(),
                        card.getRevision(),
                        card.getOverall(),
                        card.getChemistry(),
                        card.getPrice()
                ))
                .collect(Collectors.toList());
    }

    private BigDecimal maximumThrustValue(BigDecimal chemistryTeam, BigDecimal chemistryPlayer) {
        chemistryTeam = chemistryTeam.multiply(BigDecimal.valueOf(INDEX_CHEMISTRY_TEAM));
        chemistryPlayer = chemistryPlayer.multiply(BigDecimal.valueOf(INDEX_CHEMISTRY_PLAYER));

        BigDecimal chemistry = chemistryTeam.add(chemistryPlayer);

        return (chemistry.subtract(BigDecimal.valueOf(INDEX_THRUST))).divide(BigDecimal.valueOf(INDEX_THRUST));
    }
}
