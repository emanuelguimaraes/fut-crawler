package br.com.fifa.futcrawler.infrastructure.price.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseApiPriceDTO {

    private Map<String, Object> details = new LinkedHashMap<>();

    @JsonAnySetter
    public void setDetails(String key, Object value) {
        details.put(key, value);
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
