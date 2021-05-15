package br.com.fifa.futcrawler.application.attributes;

import br.com.fifa.futcrawler.application.attributes.request.AttributesWeightRequest;
import br.com.fifa.futcrawler.application.attributes.response.AttributesWeightResponse;
import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;
import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeightRepository;

public class SaveAttributesWeight {

    private final AttributesWeightRepository repository;

    public SaveAttributesWeight(AttributesWeightRepository repository) {
        this.repository = repository;
    }

    public AttributesWeightResponse execute(AttributesWeightRequest request) {
        AttributesWeight attributesWeight = request.parseFromAttributesWeight();
        repository.save(attributesWeight);
        return new AttributesWeightResponse(String.format(
                        "Os pesos dos atributos para posição %s foram carregados com sucesso.",
                        request.getPosition().toString()));
    }
}
