package br.com.fifa.futcrawler.domain.card;

import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.card.dto.CardFilterDTO;
import br.com.fifa.futcrawler.domain.card.dto.CardInfoUpdateDTO;
import br.com.fifa.futcrawler.domain.price.Price;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository {

    void save(Card card);

    List<CardDTO> find(CardFilterDTO filterDTO);

    List<CardDTO> findAllByAttributesType(OverallsRequest request, BigDecimal thrustValue);

    List<Long> findByResourceIds(List<Long> resourceIds);

    List<CardInfoUpdateDTO> findAllOnlyIdsResource(Long initialId, Long finalId);

    Long findOnlyResourceId(Long id);

    void updatePrice(Long id, Price price);
}
