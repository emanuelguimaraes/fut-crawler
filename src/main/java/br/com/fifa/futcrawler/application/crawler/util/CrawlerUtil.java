package br.com.fifa.futcrawler.application.crawler.util;

public class CrawlerUtil {

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
