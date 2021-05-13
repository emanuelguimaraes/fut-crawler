package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.application.crawler.SaveCardsByCrawler;
import br.com.fifa.futcrawler.application.crawler.request.CrawlerRequest;
import br.com.fifa.futcrawler.application.crawler.response.CrawlerResponse;
import br.com.fifa.futcrawler.infrastructure.crawler.CrawlerJsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/card")
public class CardController {

    private final SaveCardsByCrawler crawlerService;

    @Autowired
    public CardController(CardRepositoryImpl repository, CrawlerJsoup crawlerJsoup) {
        this.crawlerService = new SaveCardsByCrawler(repository, crawlerJsoup);
    }

    @PostMapping("/crawler")
    public ResponseEntity<CrawlerResponse> saveCardsByCrawler(@Valid @RequestBody CrawlerRequest request) {
        return ResponseEntity.ok(crawlerService.execute(request.getInitialValue(), request.getFinalValue()));
    }
}
