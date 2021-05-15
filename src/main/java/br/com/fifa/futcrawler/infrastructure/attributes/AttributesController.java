package br.com.fifa.futcrawler.infrastructure.attributes;

import br.com.fifa.futcrawler.application.attributes.SaveAttributesWeight;
import br.com.fifa.futcrawler.application.attributes.request.AttributesWeightRequest;
import br.com.fifa.futcrawler.application.attributes.response.AttributesWeightResponse;
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

    private SaveAttributesWeight saveAttributesWeightService;

    @Autowired
    public AttributesController(AttributesWeightRepositoryImpl repository) {
        this.saveAttributesWeightService = new SaveAttributesWeight(repository);
    }

    @PostMapping("/weight")
    public ResponseEntity<AttributesWeightResponse> saveAttributesWeight(
            @RequestBody AttributesWeightRequest request) {
        return ResponseEntity.ok(this.saveAttributesWeightService.execute(request));
    }
}
