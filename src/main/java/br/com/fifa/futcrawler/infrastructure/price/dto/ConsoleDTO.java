package br.com.fifa.futcrawler.infrastructure.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ConsoleDTO {

    private static final String VIRGULA = ",";
    private static final String VAZIO = "";

    @JsonProperty("LCPrice")
    private String lCPrice;

    @JsonProperty("LCPrice2")
    private String lCPrice2;

    @JsonProperty("LCPrice3")
    private String lCPrice3;

    @JsonProperty("LCPrice4")
    private String lCPrice4;

    @JsonProperty("LCPrice5")
    private String lCPrice5;

    @JsonProperty("updated")
    private String updated;

    @JsonProperty("MinPrice")
    private String minPrice;

    @JsonProperty("MaxPrice")
    private String maxPrice;

    @JsonProperty("PRP")
    private String pRP;

    public BigDecimal getlCPrice() {
        return new BigDecimal(lCPrice.replaceAll(VIRGULA, VAZIO));
    }

    public void setlCPrice(String lCPrice) {
        this.lCPrice = lCPrice;
    }

    public BigDecimal getlCPrice2() {
        return new BigDecimal(lCPrice2.replaceAll(VIRGULA, VAZIO));
    }

    public void setlCPrice2(String lCPrice2) {
        this.lCPrice2 = lCPrice2;
    }

    public BigDecimal getlCPrice3() {
        return new BigDecimal(lCPrice3.replaceAll(VIRGULA, VAZIO));
    }

    public void setlCPrice3(String lCPrice3) {
        this.lCPrice3 = lCPrice3;
    }

    public BigDecimal getlCPrice4() {
        return new BigDecimal(lCPrice4.replaceAll(VIRGULA, VAZIO));
    }

    public void setlCPrice4(String lCPrice4) {
        this.lCPrice4 = lCPrice4;
    }

    public BigDecimal getlCPrice5() {
        return new BigDecimal(lCPrice5.replaceAll(VIRGULA, VAZIO));
    }

    public void setlCPrice5(String lCPrice5) {
        this.lCPrice5 = lCPrice5;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public BigDecimal getMinPrice() {
        return new BigDecimal(minPrice.replaceAll(VIRGULA, VAZIO));
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return new BigDecimal(maxPrice.replaceAll(VIRGULA, VAZIO));
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getpRP() {
        return Integer.parseInt(pRP);
    }

    public void setpRP(String pRP) {
        this.pRP = pRP;
    }
}
