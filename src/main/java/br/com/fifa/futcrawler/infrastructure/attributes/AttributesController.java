package br.com.fifa.futcrawler.infrastructure.attributes;

import br.com.fifa.futcrawler.application.attributes.chemistry.SaveChemistry;
import br.com.fifa.futcrawler.application.attributes.chemistry.request.ChemistryRequest;
import br.com.fifa.futcrawler.application.attributes.chemistry.response.ChemistryResponse;
import br.com.fifa.futcrawler.application.attributes.weight.SaveAttributesWeight;
import br.com.fifa.futcrawler.application.attributes.weight.request.AttributesWeightRequest;
import br.com.fifa.futcrawler.application.attributes.weight.response.AttributesWeightResponse;
import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.ChemistryRepositoryImpl;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attributes")
public class AttributesController {

    private final SaveAttributesWeight saveAttributesWeightService;
    private final SaveChemistry saveChemistryService;

    @Autowired
    public AttributesController(AttributesWeightRepositoryImpl attributesRepository,
                                ChemistryRepositoryImpl chemistryRepository) {
        this.saveAttributesWeightService = new SaveAttributesWeight(attributesRepository);
        this.saveChemistryService = new SaveChemistry(chemistryRepository);
    }

    @PostMapping("/weight")
    public ResponseEntity<AttributesWeightResponse> saveAttributesWeight(
            @RequestBody AttributesWeightRequest request) {
        return ResponseEntity.ok(saveAttributesWeightService.execute(request));
    }

    @PostMapping("/chemistry")
    public ResponseEntity<ChemistryResponse> saveChemistry(@RequestBody ChemistryRequest request) {
        return ResponseEntity.ok(saveChemistryService.execute(request));
    }
}
