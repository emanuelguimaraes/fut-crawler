package br.com.fifa.futcrawler.application.attributes.chemistry;

import br.com.fifa.futcrawler.domain.attributes.chemistry.Chemistry;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryRepository;
import br.com.fifa.futcrawler.domain.position.Role;

public class BestChemistryByCard {

    private final ChemistryRepository repository;

    public BestChemistryByCard(ChemistryRepository repository) {
        this.repository = repository;
    }

    public Chemistry execute(Long idCard, Role position) {
        return repository.findBestChemistryByCard(idCard, position);
    }
}
