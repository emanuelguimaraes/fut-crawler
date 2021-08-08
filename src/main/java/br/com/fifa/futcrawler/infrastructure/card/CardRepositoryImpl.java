package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.card.dto.CardFilterDTO;
import br.com.fifa.futcrawler.domain.card.dto.CardInfoUpdateDTO;
import br.com.fifa.futcrawler.domain.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardRepositoryImpl implements CardRepository {

    private static final int TOTAL_ITENS_PER_PAGE = 10;

    private final CardJpaRepository repository;

    @Autowired
    public CardRepositoryImpl(CardJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CardDTO> findAllByAttributesType(OverallsRequest request, BigDecimal thrustValue) {
        List<CardDTO> cards =  repository.findAllByAttributesType(request.getPosition(), request.getIdCard(),
                request.getNation(), request.getLeague(), request.getPrice(), thrustValue,
                PageRequest.of(request.getPage(), TOTAL_ITENS_PER_PAGE));

        cards.forEach(card -> card.addChemistry(repository.findBestChemistryByCard(
                card.getId(),
                request.getPosition(),
                thrustValue)));

        return cards;
    }

    @Override
    public void save(Card card) {
        CardEntity cardEntity = new CardEntity(card);
        repository.save(cardEntity);
    }

    @Override
    public List<CardDTO> find(CardFilterDTO filterDTO) {
        return repository.findByFilters(filterDTO);
    }

    @Override
    public List<Long> findByResourceIds(List<Long> resourceIds) {
        return repository.findByResourceIds(resourceIds);
    }

    @Override
    public List<CardInfoUpdateDTO> findAllOnlyIdsResource(Long initialId, Long finalId) {
        return repository.findAllOnlyIdsResource(initialId, finalId);
    }

    @Override
    public Long findOnlyResourceId(Long id) {
        return repository.findOnlyResourceId(id);
    }

    @Transactional
    @Override
    public void updatePrice(Long id, Price price) {
        int result = repository.updatePrice(id, price.getCurrentValue(), price.getMinValue(),
                price.getMaxValue(), LocalDateTime.now());

        if (result != 1) {
            throw new RuntimeException(
                    String.format("Ocorreu um erro ao realizar a atualização do preço do Card com Id %s", id));
        }
    }
}
