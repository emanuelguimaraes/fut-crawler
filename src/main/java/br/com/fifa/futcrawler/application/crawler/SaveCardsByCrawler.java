package br.com.fifa.futcrawler.application.crawler;

import br.com.fifa.futcrawler.application.card.SaveCard;
import br.com.fifa.futcrawler.application.crawler.dto.SimpleCardDTO;
import br.com.fifa.futcrawler.application.crawler.exception.CrawlerException;
import br.com.fifa.futcrawler.application.crawler.response.CrawlerResponse;
import br.com.fifa.futcrawler.domain.card.CardRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SaveCardsByCrawler {

    private static final int FIVE_MINUTES = 300000;
    private static final String SITE_URL = "https://www.futbin.com/21/players?page=";

    private final CardRepository repository;
    private final Crawler crawler;
    private final SaveCard saveCard;

    public SaveCardsByCrawler(CardRepository repository, Crawler crawler) {
        this.repository = repository;
        this.crawler = crawler;
        this.saveCard = new SaveCard(repository);
    }

    public CrawlerResponse execute(int initialIndex, int finalIndex) {
        try {
            BigDecimal totalSaved = BigDecimal.ZERO;

            for (int index = initialIndex; index <= finalIndex; index++) {
                try {
                    List<SimpleCardDTO> cards = crawler.getListCards(SITE_URL.concat(Integer.toString(index)))
                            .stream()
                            .filter(card -> repository.findByResourceId(card.getResourceId()).isEmpty())
                            .collect(Collectors.toList());

                    for (SimpleCardDTO card : cards) {
                        saveCard.execute(crawler.getCardDetails(card.getUrl()));
                        totalSaved = totalSaved.add(BigDecimal.ONE);
                    }

                } catch (CrawlerException e) {
                    Thread.sleep(FIVE_MINUTES);
                    index--;
                }
            }

            return new CrawlerResponse(String.format("Carregado %s card(s) com sucesso.", totalSaved.toString()));

        } catch (InterruptedException e) {
            throw new CrawlerException("Ocorreu um erro durante a execução do crawler");
        }
    }
}
