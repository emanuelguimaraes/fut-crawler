package br.com.fifa.futcrawler.application.card.request;

import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;

public class OverallsRequest {

    private Role position;
    private String console;
    private int page;
    private BigDecimal price;
    private Long idCard;
    private String nation;
    private String league;

    public Role getPosition() {
        return position;
    }

    public void setPosition(Role position) {
        this.position = position;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }
}
