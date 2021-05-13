package br.com.fifa.futcrawler.infrastructure.price;

import br.com.fifa.futcrawler.application.price.GetCardPrice;
import br.com.fifa.futcrawler.infrastructure.card.CardRepositoryImpl;
import br.com.fifa.futcrawler.infrastructure.price.response.CardPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    private final GetCardPrice getCardPriceService;

    @Autowired
    public PriceController(CardRepositoryImpl repository, FutExternalApiImpl futApi) {
        this.getCardPriceService = new GetCardPrice(repository, futApi);
    }

    @GetMapping("/card/{idCard}/price")
    public ResponseEntity<CardPriceResponse> getCardPrice(@PathVariable Long idCard, @RequestParam String console) {
        return ResponseEntity.ok(new CardPriceResponse(getCardPriceService.execute(idCard, console)));
    }
}
