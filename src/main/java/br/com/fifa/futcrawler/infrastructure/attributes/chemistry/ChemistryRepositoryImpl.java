package br.com.fifa.futcrawler.infrastructure.attributes.chemistry;

import br.com.fifa.futcrawler.domain.attributes.chemistry.Chemistry;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChemistryRepositoryImpl implements ChemistryRepository {

    private final ChemistryJpaRepository repository;

    @Autowired
    public ChemistryRepositoryImpl(ChemistryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Chemistry chemistry) {
        ChemistryEntity entity = new ChemistryEntity(chemistry);
        repository.save(entity);
    }
}
