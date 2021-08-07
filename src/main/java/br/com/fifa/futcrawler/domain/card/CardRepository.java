package br.com.fifa.futcrawler.domain.card;

import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.price.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CardRepository {

    void save(Card card);

    Optional<Card> findById(Long id);

    List<CardDTO> findAllByAttributesType(OverallsRequest request, BigDecimal thrustValue);

    Optional<Card> findByNameAndRevision(String name, String revision);

    List<Long> findByResourceIds(List<Long> resourceIds);

    List<Long> findAllOnlyIds(Long initialId, Long finalId);

    Long findOnlyResourceId(Long id);

    Card updatePrice(Long id, Price price);
}
