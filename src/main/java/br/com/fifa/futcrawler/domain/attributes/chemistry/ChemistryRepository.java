package br.com.fifa.futcrawler.domain.attributes.chemistry;

import br.com.fifa.futcrawler.domain.position.Role;

public interface ChemistryRepository {

    void save(Chemistry chemistry);

    Chemistry findBestChemistryByCard(Long idCard, Role position);
}
