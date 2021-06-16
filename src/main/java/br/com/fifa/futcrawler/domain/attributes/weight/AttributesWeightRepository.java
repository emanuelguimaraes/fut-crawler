package br.com.fifa.futcrawler.domain.attributes.weight;

public interface AttributesWeightRepository {

    void save(AttributesWeight weight);

    void update(Long id, AttributesWeight weight);

}
