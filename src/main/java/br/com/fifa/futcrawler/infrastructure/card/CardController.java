package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.application.card.LargestOverallsByPosition;
import br.com.fifa.futcrawler.application.card.UpdateCardsPrice;
import br.com.fifa.futcrawler.application.card.request.OverallsRequest;
import br.com.fifa.futcrawler.application.card.response.CardResponse;
import br.com.fifa.futcrawler.application.crawler.SaveCardsByCrawler;
import br.com.fifa.futcrawler.application.crawler.request.CrawlerRequest;
import br.com.fifa.futcrawler.application.crawler.response.CrawlerResponse;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.infrastructure.crawler.CrawlerJsoup;
import br.com.fifa.futcrawler.infrastructure.price.FutExternalApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final SaveCardsByCrawler crawlerService;
    private final LargestOverallsByPosition overallsByPositionService;
    private final UpdateCardsPrice updateCardsPriceService;

    @Autowired
    public CardController(CardRepositoryImpl cardRepository,
                          CrawlerJsoup crawlerJsoup, FutExternalApiImpl futApi) {
        this.crawlerService = new SaveCardsByCrawler(cardRepository, crawlerJsoup, futApi);
        this.overallsByPositionService = new LargestOverallsByPosition(cardRepository, futApi);
        this.updateCardsPriceService = new UpdateCardsPrice(cardRepository, futApi);
    }

    @PostMapping("/crawler")
    public ResponseEntity<CrawlerResponse> saveCardsByCrawler(@Valid @RequestBody CrawlerRequest request,
                                                              @RequestParam String console) {
        return ResponseEntity.ok(crawlerService.execute(request.getInitialValue(), request.getFinalValue(), console));
    }

    @GetMapping("/overalls")
    public ResponseEntity<List<CardResponse>> getLargestOverallsByPosition(OverallsRequest request) {
        return ResponseEntity.ok(overallsByPositionService.execute(request));
    }

    @PutMapping("/prices")
    public ResponseEntity<List<Card>> updateCardsPrice(@RequestParam Long initialId, @RequestParam Long finalId,
                                                       @RequestParam String console) {
        return ResponseEntity.ok(updateCardsPriceService.execute(initialId, finalId, console));
    }
}
