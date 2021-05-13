package br.com.fifa.futcrawler.application.crawler;

import br.com.fifa.futcrawler.application.crawler.dto.CardDetailsDTO;
import br.com.fifa.futcrawler.application.crawler.dto.SimpleCardDTO;
import br.com.fifa.futcrawler.application.crawler.exception.CrawlerException;
import br.com.fifa.futcrawler.application.crawler.util.CrawlerUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Crawler {

    List<SimpleCardDTO> getListCards(String url) throws CrawlerException;
    CardDetailsDTO getCardDetails(String url) throws CrawlerException;

    default Map<String, String> generateAttributesGoalkeeper() {
        Map<String, String> attributes = new HashMap<>();

        attributes.put(CrawlerUtil.MERGULHO, "gkdiving-val-0");
        attributes.put(CrawlerUtil.JOGO_DE_MAOS, "gkhandling-val-0");
        attributes.put(CrawlerUtil.PONTAPE, "gkkicking-val-0");
        attributes.put(CrawlerUtil.POSICIONAMENTO_GOLEIRO, "gkreflexes-val-0");
        attributes.put(CrawlerUtil.REFLEXOS_GOLEIRO, "speed-val-0");
        attributes.put(CrawlerUtil.VELOCIDADE, "gkpositioning-val-0");

        return attributes;
    }

    default Map<String, String> generateAttributesPlayerMap() {
        Map<String, String> attributes = new HashMap<>();

        attributes.put(CrawlerUtil.ACELERACAO, "acceleration-val-0");
        attributes.put(CrawlerUtil.SPRINT, "sprintspeed-val-0");
        attributes.put(CrawlerUtil.POSICIONAMENTO, "positioning-val-0");
        attributes.put(CrawlerUtil.FINALIZACAO, "finishing-val-0");
        attributes.put(CrawlerUtil.POTENCIA, "shotpower-val-0");
        attributes.put(CrawlerUtil.CHUTE_DE_LONGE, "longshotsaccuracy-val-0");
        attributes.put(CrawlerUtil.CHUTES_ACROBATICOS, "volleys-val-0");
        attributes.put(CrawlerUtil.PENALTI, "penalties-val-0");
        attributes.put(CrawlerUtil.VISAO, "vision-val-0");
        attributes.put(CrawlerUtil.CRUZAMENTO, "crossing-val-0");
        attributes.put(CrawlerUtil.COBRANCA_FALTA, "freekickaccuracy-val-0");
        attributes.put(CrawlerUtil.PASSE_CURTO, "shortpassing-val-0");
        attributes.put(CrawlerUtil.PASSE_LONGO, "longpassing-val-0");
        attributes.put(CrawlerUtil.EFEITO, "curve-val-0");
        attributes.put(CrawlerUtil.AGILIDADE, "agility-val-0");
        attributes.put(CrawlerUtil.EQUILIBRIO, "balance-val-0");
        attributes.put(CrawlerUtil.REFLEXOS, "reactions-val-0");
        attributes.put(CrawlerUtil.CONTROLE_DE_BOLA, "ballcontrol-val-0");
        attributes.put(CrawlerUtil.DRIBLE, "dribbling-val-0");
        attributes.put(CrawlerUtil.COMPOSTURA, "composure-val-0");
        attributes.put(CrawlerUtil.INTERCEPTACAO, "interceptions-val-0");
        attributes.put(CrawlerUtil.CABECEAMENTO, "headingaccuracy-val-0");
        attributes.put(CrawlerUtil.MARCACAO, "marking-val-0");
        attributes.put(CrawlerUtil.CARRINHO, "standingtackle-val-0");
        attributes.put(CrawlerUtil.CORTE_EM_PE, "slidingtackle-val-0");
        attributes.put(CrawlerUtil.SALTO, "jumping-val-0");
        attributes.put(CrawlerUtil.RESISTENCIA, "stamina-val-0");
        attributes.put(CrawlerUtil.FORCA, "strength-val-0");
        attributes.put(CrawlerUtil.AGRESSIVIDADE, "aggression-val-0");

        return attributes;
    }
}
