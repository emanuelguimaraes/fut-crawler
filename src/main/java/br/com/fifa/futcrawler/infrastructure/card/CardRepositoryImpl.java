package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardRepositoryImpl implements CardRepository {

    private final CardJpaRepository repository;

    @Autowired
    public CardRepositoryImpl(CardJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Card card) {
        CardEntity cardEntity = new CardEntity(card);
        repository.save(cardEntity);
    }

    @Override
    public Optional<Card> findByNameAndRevision(String name, String revision) {
        Optional<CardEntity> cartaoEntity = repository.findByNomeAndVersao(name, revision);

        if (cartaoEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(CardFacade.criar(cartaoEntity.get()));
    }

    @Override
    public List<Card> findByResourceId(Long idResource) {
        List<CardEntity> cartoesEntity = repository.findByIdResource(idResource);

        return cartoesEntity.stream().map(CardFacade::criar).collect(Collectors.toList());
    }
}
