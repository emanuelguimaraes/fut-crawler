package br.com.fifa.futcrawler.domain.card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {

    Optional<Card> findById(Long id);

    void save(Card card);

    Optional<Card> findByNameAndRevision(String name, String revision);

    List<Card> findByResourceId(Long idResource);
}
