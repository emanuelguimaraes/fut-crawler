package br.com.fifa.futcrawler.infrastructure.attributes.weight;

import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;
import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributesWeightRepositoryImpl implements AttributesWeightRepository {

    private final AttributesWeightJpaRepository repository;

    @Autowired
    public AttributesWeightRepositoryImpl(AttributesWeightJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(AttributesWeight weight) {
        AttributesWeightEntity entity = new AttributesWeightEntity(weight);
        repository.save(entity);
    }
}
