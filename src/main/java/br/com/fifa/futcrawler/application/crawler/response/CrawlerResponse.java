package br.com.fifa.futcrawler.application.crawler.response;

public class CrawlerResponse {

    private String message;

    public CrawlerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
