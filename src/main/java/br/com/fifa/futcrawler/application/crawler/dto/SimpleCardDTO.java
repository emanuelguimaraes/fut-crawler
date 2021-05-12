package br.com.fifa.futcrawler.application.crawler.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SimpleCardDTO {

    private Long resourceId;
    private String url;

    public SimpleCardDTO(@NotNull(message = "O valor do campo Resource Id está inválido") Long resourceId,
                         @NotBlank(message = "O valor do campo URL está inválido") String url) {
        this.resourceId = resourceId;
        this.url = url;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public String getUrl() {
        return url;
    }
}
