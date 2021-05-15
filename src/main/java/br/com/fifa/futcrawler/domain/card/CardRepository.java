package br.com.fifa.futcrawler.domain.card;

import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.position.Role;

import java.util.List;
import java.util.Optional;

public interface CardRepository {

    Optional<Card> findById(Long id);

    List<CardDTO> findAllByAttributesType(String type, Role position, int page);

    void save(Card card);

    Optional<Card> findByNameAndRevision(String name, String revision);

    List<Card> findByResourceId(Long idResource);
}
