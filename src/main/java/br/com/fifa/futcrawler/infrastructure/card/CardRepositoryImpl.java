package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardRepositoryImpl implements CardRepository {

    private static final int TOTAL_ITENS_PER_PAGE = 10;

    private final CardJpaRepository repository;

    @Autowired
    public CardRepositoryImpl(CardJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Card> findById(Long id) {
        Optional<CardEntity> cardEntity = repository.findById(id);

        if (cardEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(CardFacade.create(cardEntity.get()));
    }

    @Override
    public List<CardDTO> findAllByAttributesType(OverallsRequest request) {
        return repository.findAllByAttributesType(request.getPosition(), request.getIdCard(), request.getNation(),
                request.getLeague(), request.getPrice(), PageRequest.of(request.getPage(), TOTAL_ITENS_PER_PAGE));
    }

    @Override
    public void save(Card card) {
        CardEntity cardEntity = new CardEntity(card);
        repository.save(cardEntity);
    }

    @Override
    public Optional<Card> findByNameAndRevision(String name, String revision) {
        Optional<CardEntity> cardEntity = repository.findByNomeAndVersao(name, revision);

        if (cardEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(CardFacade.create(cardEntity.get()));
    }

    @Override
    public List<Long> findByResourceIds(List<Long> resourceIds) {
        return repository.findByResourceIds(resourceIds);
    }

    @Override
    public List<Long> findAllOnlyIds(Long initialId, Long finalId) {
        return repository.findAllOnlyIds(initialId, finalId);
    }

    @Override
    public Long findOnlyResourceId(Long id) {
        return repository.findOnlyResourceId(id);
    }

    @Override
    public Card updatePrice(Long id, Price price) {
        CardEntity cardEntity = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Card informado não foi encontrado"));

        cardEntity.updatePrice(price);
        repository.save(cardEntity);

        return CardFacade.create(cardEntity);
    }
}
