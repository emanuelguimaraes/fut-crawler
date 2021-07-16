package br.com.fifa.futcrawler.domain.card.dto;

import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;
import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;

public class CardDTO {

    private Long id;
    private String name;
    private String club;
    private String league;
    private String nation;
    private Role position;
    private String revision;
    private BigDecimal overall;
    private ChemistryType chemistry;
    private BigDecimal price;

    public CardDTO(Long id, String name, String club, String league, String nation,
                   Role position, String revision, BigDecimal overall, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.club = club;
        this.league = league;
        this.nation = nation;
        this.position = position;
        this.revision = revision;
        this.overall = overall;
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

    public BigDecimal getOverall() {
        return overall;
    }

    public ChemistryType getChemistry() {
        return chemistry;
    }

    public void addChemistry(ChemistryType chemistry) {
        this.chemistry = chemistry;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
