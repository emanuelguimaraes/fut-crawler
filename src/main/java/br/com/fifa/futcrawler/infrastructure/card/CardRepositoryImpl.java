package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.position.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardRepositoryImpl implements CardRepository {

    private static final int TOTAL_ITENS_PER_PAGE = 100;

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
    public List<CardDTO> findAllByAttributesType(String type, Role position, int page) {
        return repository.findAllByAttributesType(position, PageRequest.of(page, TOTAL_ITENS_PER_PAGE));
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
    public List<Card> findByResourceId(Long idResource) {
        List<CardEntity> cardsEntity = repository.findByIdResource(idResource);

        return cardsEntity
                .stream()
                .map(CardFacade::create)
                .collect(Collectors.toList());
    }
}
