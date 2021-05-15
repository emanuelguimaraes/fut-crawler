package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.application.card.LargestOverallsByPosition;
import br.com.fifa.futcrawler.application.card.response.CardResponse;
import br.com.fifa.futcrawler.application.crawler.SaveCardsByCrawler;
import br.com.fifa.futcrawler.application.crawler.request.CrawlerRequest;
import br.com.fifa.futcrawler.application.crawler.response.CrawlerResponse;
import br.com.fifa.futcrawler.domain.position.Role;
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

    @Autowired
    public CardController(CardRepositoryImpl repository, CrawlerJsoup crawlerJsoup, FutExternalApiImpl futApi) {
        this.crawlerService = new SaveCardsByCrawler(repository, crawlerJsoup);
        this.overallsByPositionService = new LargestOverallsByPosition(repository, futApi);
    }

    @PostMapping("/crawler")
    public ResponseEntity<CrawlerResponse> saveCardsByCrawler(@Valid @RequestBody CrawlerRequest request) {
        return ResponseEntity.ok(crawlerService.execute(request.getInitialValue(), request.getFinalValue()));
    }

    @GetMapping("/overalls")
    public ResponseEntity<List<CardResponse>> getLargestOverallsByPosition(
            @RequestParam Role position, @RequestParam String console, @RequestParam int page) {
        return ResponseEntity.ok(overallsByPositionService.execute(position, console, page));
    }
}
