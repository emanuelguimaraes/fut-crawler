package br.com.fifa.futcrawler.infrastructure.attributes;

import br.com.fifa.futcrawler.application.attributes.chemistry.SaveChemistry;
import br.com.fifa.futcrawler.application.attributes.chemistry.request.ChemistryRequest;
import br.com.fifa.futcrawler.application.attributes.chemistry.response.ChemistryMessageResponse;
import br.com.fifa.futcrawler.application.attributes.weight.AttributesWeightService;
import br.com.fifa.futcrawler.application.attributes.weight.request.AttributesWeightRequest;
import br.com.fifa.futcrawler.application.attributes.weight.response.AttributesWeightResponse;
import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.ChemistryRepositoryImpl;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attributes")
public class AttributesController {

    private final AttributesWeightService attributesWeightServiceService;
    private final SaveChemistry saveChemistryService;

    @Autowired
    public AttributesController(AttributesWeightRepositoryImpl attributesRepository,
                                ChemistryRepositoryImpl chemistryRepository) {
        this.attributesWeightServiceService = new AttributesWeightService(attributesRepository);
        this.saveChemistryService = new SaveChemistry(chemistryRepository);
    }

    @PostMapping("/weight")
    public ResponseEntity<AttributesWeightResponse> saveAttributesWeight(
            @RequestBody AttributesWeightRequest request) {
        return ResponseEntity.ok(attributesWeightServiceService.save(request));
    }

    @PutMapping("/weight/{id}")
    public ResponseEntity<AttributesWeightResponse> updateAttributesWeight(
            @PathVariable Long id,
            @RequestBody AttributesWeightRequest request) {
        return ResponseEntity.ok(attributesWeightServiceService.update(id, request));
    }

    @PostMapping("/chemistry")
    public ResponseEntity<ChemistryMessageResponse> saveChemistry(@RequestBody ChemistryRequest request) {
        return ResponseEntity.ok(saveChemistryService.execute(request));
    }
}
