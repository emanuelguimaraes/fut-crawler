package br.com.fifa.futcrawler.application.crawler;

import br.com.fifa.futcrawler.application.card.SaveCard;
import br.com.fifa.futcrawler.application.crawler.dto.CardDetailsDTO;
import br.com.fifa.futcrawler.application.crawler.dto.SimpleCardDTO;
import br.com.fifa.futcrawler.application.crawler.exception.CrawlerException;
import br.com.fifa.futcrawler.application.crawler.response.CrawlerResponse;
import br.com.fifa.futcrawler.application.price.FutExternalApi;
import br.com.fifa.futcrawler.application.price.GetCardPrice;
import br.com.fifa.futcrawler.domain.card.CardRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SaveCardsByCrawler {

    private static final int FIVE_MINUTES = 300000;
    private static final String SITE_URL = "https://www.futbin.com/21/players?page=";

    private final CardRepository repository;
    private final Crawler crawlerService;
    private final SaveCard saveCardService;
    private final FutExternalApi futApi;

    public SaveCardsByCrawler(CardRepository repository, Crawler crawlerService, FutExternalApi futApi) {
        this.repository = repository;
        this.crawlerService = crawlerService;
        this.saveCardService = new SaveCard(repository);
        this.futApi = futApi;
    }

    public CrawlerResponse execute(int initialIndex, int finalIndex, String console) {
        try {
            BigDecimal totalSaved = BigDecimal.ZERO;

            for (int index = initialIndex; index <= finalIndex; index++) {
                try {
                    List<SimpleCardDTO> cards = crawlerService.getListCards(SITE_URL.concat(Integer.toString(index)))
                            .stream()
                            .filter(card -> repository.findByResourceId(card.getResourceId()).isEmpty())
                            .collect(Collectors.toList());

                    for (SimpleCardDTO card : cards) {
                        CardDetailsDTO cardDetailsDTO = crawlerService.getCardDetails(card.getUrl());
                        cardDetailsDTO.addPrice(futApi.getCardPrice(cardDetailsDTO.getIdResource(), console));

                        saveCardService.execute(cardDetailsDTO);
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
