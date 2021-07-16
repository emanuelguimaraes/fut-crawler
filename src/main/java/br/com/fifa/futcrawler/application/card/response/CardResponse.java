package br.com.fifa.futcrawler.application.card.response;

import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;
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
    private String nation;
    private Role position;
    private String revision;
    private String overall;
    private ChemistryType chemistry;
    private BigDecimal price;

    public CardResponse(Long id, String name, String club, String league, String nation,
                        Role position, String revision, BigDecimal overall, ChemistryType chemistry,
                        BigDecimal price) {
        setFormat();
        this.id = id;
        this.name = name;
        this.club = club;
        this.league = league;
        this.nation = nation;
        this.position = position;
        this.revision = revision;
        this.overall = this.decimalFormat.format(overall);
        this.chemistry = chemistry;
        this.price = price;
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

    public String getNation() {
        return nation;
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

    public ChemistryType getChemistry() {
        return chemistry;
    }

    public String getPrice() {
        return this.decimalFormat.format(price);
    }

    public BigDecimal getPriceNumber() {
        return this.price;
    }

    public void addPrice(BigDecimal price) {
        this.price = price;
    }

    private void setFormat() {
        this.decimalFormatSymbols = new DecimalFormatSymbols();
        this.decimalFormatSymbols.setDecimalSeparator(',');
        this.decimalFormatSymbols.setGroupingSeparator('.');
        this.decimalFormat = new DecimalFormat("#,###.00", decimalFormatSymbols);
    }
}
