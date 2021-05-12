package br.com.fifa.futcrawler.application.crawler;

import br.com.fifa.futcrawler.application.Command;
import br.com.fifa.futcrawler.application.card.SaveCard;
import br.com.fifa.futcrawler.application.crawler.dto.SimpleCardDTO;
import br.com.fifa.futcrawler.domain.card.CardRepository;

import java.util.List;

public class SaveCardsByCrawler extends Command<String> {

    private final CardRepository repositorio;
    private final Crawler crawler;
    private SaveCard saveCard;

    public SaveCardsByCrawler(CardRepository repositorio, Crawler crawler) {
        this.repositorio = repositorio;
        this.crawler = crawler;
        this.saveCard = new SaveCard(repositorio);
    }

    @Override
    public void execute(String url) {
        List<SimpleCardDTO> cards = crawler.getListCards(url);

        for (SimpleCardDTO card : cards) {
            if (repositorio.findByResourceId(card.getResourceId()).isEmpty()) {
                saveCard.execute(crawler.getCardDetails(card.getUrl()));
            }
        }
    }
}
