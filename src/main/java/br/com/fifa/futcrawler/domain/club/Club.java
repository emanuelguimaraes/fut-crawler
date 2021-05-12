package br.com.fifa.futcrawler.domain.club;

public class Club {

    private String name;
    private League league;

    public Club(String name, League league) {
        this.name = name;
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public League getLeague() {
        return league;
    }
}
