package br.com.fifa.futcrawler.infrastructure.card.custom;

import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.position.Role;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CardJpaCustomRepository {

    List<CardDTO> findAllByAttributesType(Role position, Long idCard, String nation, String league,
                                          BigDecimal price, BigDecimal thrustValue, Pageable pageable);

    ChemistryType findBestChemistryByCard(Long idCard, Role position, BigDecimal thrustValue);
}
