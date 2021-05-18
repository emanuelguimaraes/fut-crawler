package br.com.fifa.futcrawler.infrastructure.attributes.chemistry.custom;

import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.ChemistryEntity;

public interface ChemistryJpaCustomRepository {

    ChemistryEntity findBestChemistryByCard(Long idCard, Role position);
}
