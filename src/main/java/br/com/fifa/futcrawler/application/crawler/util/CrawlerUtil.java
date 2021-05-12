package br.com.fifa.futcrawler.application.crawler.util;

public class CrawlerUtil {

    public static final String NOME = "nome";
    public static final String ALTURA = "altura";
    public static final String PESO = "peso";
    public static final String NACIONALIDADE = "nacionalidade";
    public static final String VERSAO = "versao";
    public static final String FINTA = "finta";
    public static final String PERNA_BOA = "pernaBoa";
    public static final String PERNA_RUIM = "pernaRuim";
    public static final String POSICAO = "posicao";
    public static final String ACELERACAO = "aceleracao";
    public static final String SPRINT = "sprint";
    public static final String POSICIONAMENTO = "posicionamento";
    public static final String FINALIZACAO = "finalizacao";
    public static final String POTENCIA = "potencia";
    public static final String CHUTE_DE_LONGE = "chuteDeLonge";
    public static final String CHUTES_ACROBATICOS = "chutesAcrobaticos";
    public static final String PENALTI = "penalti";
    public static final String VISAO = "visao";
    public static final String CRUZAMENTO = "cruzamento";
    public static final String COBRANCA_FALTA = "cobrancaFalta";
    public static final String PASSE_CURTO = "passeCurto";
    public static final String PASSE_LONGO = "passeLongo";
    public static final String EFEITO = "efeito";
    public static final String AGILIDADE = "agilidade";
    public static final String EQUILIBRIO = "equilibrio";
    public static final String REFLEXOS = "reflexos";
    public static final String CONTROLE_DE_BOLA = "controleDeBola";
    public static final String DRIBLE = "drible";
    public static final String COMPOSTURA = "compostura";
    public static final String INTERCEPTACAO = "interceptacao";
    public static final String CABECEAMENTO = "cabeceamento";
    public static final String MARCACAO = "marcacao";
    public static final String CARRINHO = "carrinho";
    public static final String CORTE_EM_PE = "corteEmPe";
    public static final String SALTO = "salto";
    public static final String RESISTENCIA = "resistencia";
    public static final String FORCA = "forca";
    public static final String AGRESSIVIDADE = "agressividade";
    public static final String MERGULHO = "mergulho";
    public static final String JOGO_DE_MAOS = "jogoDeMaos";
    public static final String PONTAPE = "pontape";
    public static final String POSICIONAMENTO_GOLEIRO = "posicionamentoGoleiro";
    public static final String REFLEXOS_GOLEIRO = "reflexosGoleiro";
    public static final String VELOCIDADE = "velocidade";
    public static final String CLUBE = "clube";
    public static final String LIGA = "liga";
    public static final String ID_RESOURCE = "idResource";
    public static final String HOST = "https://www.futbin.com";
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36";

    private static final String VAZIO = "";

    public static Long formatterResourceId(String resourceId) {
        return Long.valueOf(resourceId
                .replace("https://cdn.futbin.com/content/fifa21/img/players/", VAZIO)
                .replace(".png?v=22", VAZIO)
                .replace("p", VAZIO));
    }

    public static String generateCompleteURL(String url) {
        return HOST.concat(url);
    }
}
