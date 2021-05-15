package br.com.fifa.futcrawler.domain.card.dto;

import br.com.fifa.futcrawler.domain.position.Role;

import java.math.BigDecimal;

public class CardDTO {

    private Long id;
    private String name;
    private String club;
    private String league;
    private Role position;
    private String revision;
    private BigDecimal overall;

    public CardDTO(Long id, String name, String club, String league, Role position,
                   String revision, BigDecimal overall) {
        this.id = id;
        this.name = name;
        this.club = club;
        this.league = league;
        this.position = position;
        this.revision = revision;
        this.overall = overall;
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

    public BigDecimal getOverall() {
        return overall;
    }
}
