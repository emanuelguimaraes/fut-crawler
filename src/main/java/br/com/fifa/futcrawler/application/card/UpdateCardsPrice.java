package br.com.fifa.futcrawler.application.card;

import br.com.fifa.futcrawler.application.price.FutExternalApi;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.card.dto.CardInfoUpdateDTO;
import br.com.fifa.futcrawler.domain.price.Price;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UpdateCardsPrice {

    private final Logger logger = LogManager.getLogger(UpdateCardsPrice.class);

    private final CardRepository repository;
    private final FutExternalApi futApi;

    public UpdateCardsPrice(CardRepository repository, FutExternalApi futApi) {
        this.repository = repository;
        this.futApi = futApi;
    }

    public String execute(Long initialId, Long finalId, String console) {
        List<CardInfoUpdateDTO> idCards = repository.findAllOnlyIdsResource(initialId, finalId);

        idCards.parallelStream().forEach(card -> {
            Price price = futApi.getCardPrice(card.getIdResource(), console);
            repository.updatePrice(card.getId(), price);
            logger.info(String.format("Preço do Card %s atualizado com sucesso", card.getId()));
        });

        return "Preços dos Cards atualizados com sucesso";
    }
}
