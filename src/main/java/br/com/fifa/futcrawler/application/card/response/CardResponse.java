package br.com.fifa.futcrawler.application.card.response;

import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CardResponse {

    private DecimalFormat decimalFormat;
    private DecimalFormatSymbols decimalFormatSymbols;

    private Long id;
    private String name;
    private String club;
    private String league;
    private Role position;
    private String revision;
    private String overall;
    private String price;

    public CardResponse(Long id, String name, String club, String league, Role position,
                        String revision, BigDecimal overall) {
        setFormat();
        this.id = id;
        this.name = name;
        this.club = club;
        this.league = league;
        this.position = position;
        this.revision = revision;
        this.overall = this.decimalFormat.format(overall);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClub() {
        return club;
    }

    public String getLeague() {
        return league;
    }

    public Role getPosition() {
        return position;
    }

    public String getRevision() {
        return revision;
    }

    public String getOverall() {
        return overall;
    }

    public String getPrice() {
        return price;
    }

    public void addPrice(BigDecimal price) {
        this.price = this.decimalFormat.format(price);
    }

    private void setFormat() {
        this.decimalFormatSymbols = new DecimalFormatSymbols();
        this.decimalFormatSymbols.setDecimalSeparator(',');
        this.decimalFormatSymbols.setGroupingSeparator('.');
        this.decimalFormat = new DecimalFormat("#,###.00", decimalFormatSymbols);
    }
}
