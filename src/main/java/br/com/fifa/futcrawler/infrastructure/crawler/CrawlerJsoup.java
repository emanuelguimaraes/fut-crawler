package br.com.fifa.futcrawler.infrastructure.crawler;

import br.com.fifa.futcrawler.application.crawler.Crawler;
import br.com.fifa.futcrawler.application.crawler.dto.CardDetailsDTO;
import br.com.fifa.futcrawler.application.crawler.dto.SimpleCardDTO;
import br.com.fifa.futcrawler.application.crawler.exception.CrawlerException;
import br.com.fifa.futcrawler.application.crawler.util.CrawlerUtil;
import br.com.fifa.futcrawler.domain.card.util.CardUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CrawlerJsoup implements Crawler {

    private final Logger logger = LogManager.getLogger(CrawlerJsoup.class);

    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    private static final int CLUB_INDEX = 1;
    private static final int NATION_INDEX = 2;
    private static final int LEAGUE_INDEX = 3;
    private static final int SKILLS_INDEX = 4;
    private static final int WEAK_FOOT_INDEX = 5;
    private static final int FOOT_INDEX = 7;
    private static final int HEIGHT_INDEX = 8;
    private static final int WEIGHT_INDEX = 9;
    private static final int REVISION_INDEX = 10;

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
    private static final String NAME_CLASS = "pcdisplay-name";
    private static final String POSITION_CLASS = "pcdisplay-pos";

    private static final int TWO_SECONDS = 2000;

    @Override
    public List<SimpleCardDTO> getListCards(String url) throws CrawlerException {
        try {
            Thread.sleep(TWO_SECONDS);
            Document doc = Jsoup.connect(url).userAgent(CrawlerUtil.USER_AGENT).get();

            Elements elements = doc.getElementsByClass(LIST_PLAYER_CLASS_ONE);
            elements.addAll(doc.getElementsByClass(LIST_PLAYER_CLASS_TWO));

            List<SimpleCardDTO> cardsDTO = elements
                    .stream()
                    .map(this::generateByElement)
                    .collect(Collectors.toList());

            logger.info(String.format("Carregado elementos da requisição %s", url));

            return cardsDTO;

        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            throw new CrawlerException(e.getMessage());
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
    public CardDetailsDTO getCardDetails(String url) throws CrawlerException {
        try {
            Thread.sleep(TWO_SECONDS);
            Document html = Jsoup.connect(url).userAgent(CrawlerUtil.USER_AGENT).get();

            Map<String, String> fields = new HashMap<>(getBiographyInHtml(html));
            Map<String, String> attributes = new HashMap<>();

            if (Role.GK.equals(Role.valueOf(fields.get(CardUtil.POSITION).trim().toUpperCase()))) {
                attributes.putAll(generateAttributesGoalkeeper());
            } else {
                attributes.putAll(generateAttributesPlayerMap());
            }

            fields.putAll(getAttributesInHtml(html, attributes));

            return new CardDetailsDTO(fields);

        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            throw new CrawlerException(e.getMessage());
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

        fields.put(CardUtil.ID_RESOURCE, html.getElementsByAttribute(ID_RESOURCE_ATTRIBUTE)
                .first().attributes().get(ID_RESOURCE_ATTRIBUTE));
        fields.put(CardUtil.NAME, getValueElement(html.getElementsByClass(NAME_CLASS),
                FIRST_ELEMENT, FIRST_ELEMENT));
        fields.put(CardUtil.POSITION, getValueElement(html.getElementsByClass(POSITION_CLASS),
                FIRST_ELEMENT, FIRST_ELEMENT));
        fields.put(CardUtil.HEIGHT, getValueElement(elements, getIndex(HEIGHT_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CardUtil.WEIGHT, getValueElement(elements, getIndex(WEIGHT_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CardUtil.NATION, getValueElement(elements.get(getIndex(NATION_INDEX, sizeElements))
                .children(), SECOND_ELEMENT, FIRST_ELEMENT));
        fields.put(CardUtil.REVISION, getValueElement(elements, getIndex(REVISION_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CardUtil.SKILLS, getValueElement(elements, getIndex(SKILLS_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CardUtil.FOOT, getValueElement(elements,
                getIndex(FOOT_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CardUtil.WEAK_FOOT, getValueElement(elements,
                getIndex(WEAK_FOOT_INDEX, sizeElements), FIRST_ELEMENT));
        fields.put(CardUtil.CLUB, getValueElement(elements.get(getIndex(CLUB_INDEX, sizeElements)).children(),
                SECOND_ELEMENT, FIRST_ELEMENT));
        fields.put(CardUtil.LEAGUE, getValueElement(elements.get(getIndex(LEAGUE_INDEX, sizeElements)).children(),
                SECOND_ELEMENT, FIRST_ELEMENT));

        return fields;
    }

    private int getIndex(int index, int size) {
        if ((size == 19) || (size == 17 && index < 7)) {
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
