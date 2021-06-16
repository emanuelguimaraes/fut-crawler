package br.com.fifa.futcrawler.application.attributes.weight;

import br.com.fifa.futcrawler.application.attributes.weight.request.AttributesWeightRequest;
import br.com.fifa.futcrawler.application.attributes.weight.response.AttributesWeightResponse;
import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;
import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeightRepository;

import java.math.BigDecimal;

public class AttributesWeightService {

    public static final BigDecimal TOTAL_ATTRIBUTES = BigDecimal.ONE;

    private final AttributesWeightRepository repository;

    public AttributesWeightService(AttributesWeightRepository repository) {
        this.repository = repository;
    }

    public AttributesWeightResponse save(AttributesWeightRequest request) {
        AttributesWeight attributesWeight = request.parseFromAttributesWeight();

        if (TOTAL_ATTRIBUTES.compareTo(attributesWeight.sumAttributes()) == 0) {
            repository.save(attributesWeight);
        } else {
            throw new RuntimeException(String.format(
                    "A soma dos atributos informados é diferente de %s. A soma dos valores informados foi [%s]",
                    TOTAL_ATTRIBUTES.toString(),
                    attributesWeight.sumAttributes()));
        }

        return new AttributesWeightResponse(String.format(
                        "Os pesos dos atributos para posição %s foram carregados com sucesso.",
                        request.getPosition().toString()));
    }

    public AttributesWeightResponse update(Long id, AttributesWeightRequest request) {
        AttributesWeight attributesWeight = request.parseFromAttributesWeight();

        if (TOTAL_ATTRIBUTES.compareTo(attributesWeight.sumAttributes()) == 0) {
            repository.update(id, attributesWeight);
        } else {
            throw new RuntimeException(String.format(
                    "A soma dos atributos informados é diferente de %s. A soma dos valores informados foi [%s]",
                    TOTAL_ATTRIBUTES.toString(),
                    attributesWeight.sumAttributes()));
        }

        return new AttributesWeightResponse(String.format(
                "Os pesos dos atributos para posição %s foram atualizados com sucesso.",
                request.getPosition().toString()));
    }
}
