package br.com.fifa.futcrawler.application.crawler.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CrawlerRequest {

    @NotNull(message = "O campo initialValue deve está preenchido")
    @Size(min = 1, max = 668, message = "O valor do campo initialValue deve está dentro do intervalo de 1 até 668")
    private int initialValue;

    @NotNull(message = "O campo finalValue deve está preenchido")
    @Size(min = 1, max = 668, message = "O valor do campo finalValue deve está dentro do intervalo de 1 até 668")
    private int finalValue;

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(int finalValue) {
        this.finalValue = finalValue;
    }
}
