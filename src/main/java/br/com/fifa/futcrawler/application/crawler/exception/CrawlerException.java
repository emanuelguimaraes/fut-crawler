package br.com.fifa.futcrawler.application.crawler.exception;

public class CrawlerException extends RuntimeException {

    public CrawlerException(String mensagem) {
        super(mensagem);
    }
}
