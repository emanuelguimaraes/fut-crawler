package br.com.fifa.futcrawler.infrastructure.crawler;

import br.com.fifa.futcrawler.application.crawler.Crawler;
import br.com.fifa.futcrawler.application.crawler.dto.CardDetailsDTO;
import br.com.fifa.futcrawler.application.crawler.dto.SimpleCardDTO;
import br.com.fifa.futcrawler.application.crawler.util.CrawlerUtil;
import br.com.fifa.futcrawler.domain.position.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CrawlerJsoup implements Crawler {

    private Logger logger = LogManager.getLogger(CrawlerJsoup.class);

    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    private static final int CLUBE_INDEX = 1;
    private static final int NACIONALIDADE_INDEX = 2;
    private static final int LIGA_INDEX = 3;
    private static final int FINTA_INDEX = 4;
    private static final int PERNA_RUIM_INDEX = 5;
    private static final int PERNA_BOA_INDEX = 7;
    private static final int ALTURA_INDEX = 8;
    private static final int PESO_INDEX = 9;
    private static final int VERSAO_INDEX = 10;

    private static final String LIST_PLAYER_CLASS_ONE = "player_tr_1";
    private static final String LIST_PLAYER_CLASS_TWO = "player_tr_2";
    private static final String RESOURCE_ID_CLASS = "player_img";
    private static final String RESOURCE_ID_ATTRIBUTE = "data-original";
    private static final String URL_CLASS = "player_name_players_table";
    private static final String URL_ATTRIBUTE = "href";
    private static final String ATTRIBUTES_CLASS = "stat_val";
    private static final String BIOGRAPHY_ID_ELEMENT = "info_content";
    private static final String BIOGRAPHY_TAG = "td";
    private static final String ID_RESOURCE_ATTRIBUTE = "data-player-resource";
    private static final String NOME_CLASS = "pcdisplay-name";
    private static final String POSICAO_CLASS = "pcdisplay-pos";

    private static final int TWO_SECONDS = 2000;

    @Override
    public List<SimpleCardDTO> getListCards(String url) {
        try {
            List<SimpleCardDTO> cardsDTO = new ArrayList<>();

            Thread.sleep(2000);
            Document doc = Jsoup.connect(url).userAgent(CrawlerUtil.USER_AGENT).get();

            Elements elements = doc.getElementsByClass(LIST_PLAYER_CLASS_ONE);
            elements.addAll(doc.getElementsByClass(LIST_PLAYER_CLASS_TWO));

            cardsDTO.addAll(elements
                    .stream()
                    .map(element -> generateByElement(element))
                    .collect(Collectors.toList()));

            return cardsDTO;

        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private SimpleCardDTO generateByElement(Element element) {
        Long resourceId = CrawlerUtil.formatterResourceId(element
                .getElementsByClass(RESOURCE_ID_CLASS)
                .first().attributes().get(RESOURCE_ID_ATTRIBUTE));

        String url = element
                .getElementsByClass(URL_CLASS)
                .first()
                .attr(URL_ATTRIBUTE);

        return new SimpleCardDTO(resourceId, CrawlerUtil.generateCompleteURL(url));
    }

    @Override
    public CardDetailsDTO getCardDetails(String url) {
        try {
            Map<String, String> fields = new HashMap<>();
            Map<String, String> attributes = new HashMap<>();

            Thread.sleep(TWO_SECONDS);
            Document html = Jsoup.connect(url).userAgent(CrawlerUtil.USER_AGENT).get();

            fields.putAll(getBiographyInHtml(html));

            if (Role.GK.equals(Role.valueOf(fields.get(CrawlerUtil.POSICAO).trim().toUpperCase()))) {
                attributes.putAll(generateAttributesGoalkeeper());
            } else {
                attributes.putAll(generateAttributesPlayerMap());
            }

            fields.putAll(getAttributesInHtml(html, attributes));

            return new CardDetailsDTO(fields);

        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private Map<String, String> getAttributesInHtml(Document html, Map<String, String> attributes) {
        Map<String, String> fields = new HashMap<>();

        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            Element elementAttribute = html.getElementsByClass(attribute.getValue()).first();

            if (elementAttribute == null) {
                throw new RuntimeException(
                        String.format("Erro durante a captura do valor do atributo %s",
                                attribute.getKey()));
            }

            String value = getValueElement(
                    elementAttribute.getElementsByClass(ATTRIBUTES_CLASS), SECOND_ELEMENT, FIRST_ELEMENT);

            if (value == null || value.isBlank()) {
                throw new RuntimeException(
                        String.format("Erro durante a captura do valor do atributo %s",
                                attribute.getKey()));
            }

            fields.put(attribute.getKey(), value);
        }

        return fields;
    }

    private Map<String, String> getBiographyInHtml(Document html) {
        Map<String, String> fields = new HashMap<>();
        Elements elements = html.getElementById(BIOGRAPHY_ID_ELEMENT).getElementsByTag(BIOGRAPHY_TAG);
        int sizeElements = elements.size();

        fields.put(CrawlerUtil.ID_RESOURCE, html.getElementsByAttribute(ID_RESOURCE_ATTRIBUTE)
                .first().attributes().get(ID_RESOURCE_ATTRIBUTE));
        fields.put(CrawlerUtil.NOME, getValueElement(html.getElementsByClass(NOME_CLASS),
                FIRST_ELEMENT, FIRST_ELEMENT));
        fields.put(CrawlerUtil.POSICAO, getValueElement(html.getElementsByClass(POSICAO_CLASS),
                FIRST_ELEMENT, FIRST_ELEMENT));
        fields.put(CrawlerUtil.ALTURA, getValueElement(elements, getIndex(ALTURA_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CrawlerUtil.PESO, getValueElement(elements, getIndex(PESO_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CrawlerUtil.NACIONALIDADE, getValueElement(elements.get(getIndex(NACIONALIDADE_INDEX, sizeElements))
                .children(), SECOND_ELEMENT, FIRST_ELEMENT));
        fields.put(CrawlerUtil.VERSAO, getValueElement(elements, getIndex(VERSAO_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CrawlerUtil.FINTA, getValueElement(elements, getIndex(FINTA_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CrawlerUtil.PERNA_BOA, getValueElement(elements,
                getIndex(PERNA_BOA_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CrawlerUtil.PERNA_RUIM, getValueElement(elements,
                getIndex(PERNA_RUIM_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CrawlerUtil.CLUBE, getValueElement(elements.get(getIndex(CLUBE_INDEX, sizeElements)).children(),
                SECOND_ELEMENT, FIRST_ELEMENT));
        fields.put(CrawlerUtil.LIGA, getValueElement(elements.get(getIndex(LIGA_INDEX, sizeElements)).children(),
                SECOND_ELEMENT, FIRST_ELEMENT));

        return fields;
    }

    private int getIndex(int index, int size) {
        if (size == 17 && index < 7) {
            return (index + 1);
        }

        if (size == 19) {
            return (index + 1);
        }

        return index;
    }

    private String getValueElement(Elements element, int indexElement, int indexChildNode) {
        try {
            return ((TextNode) element.get(indexElement)
                    .childNode(indexChildNode))
                    .text();
        } catch (Exception e) {
            return null;
        }
    }
}
