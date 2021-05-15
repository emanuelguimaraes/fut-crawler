package br.com.fifa.futcrawler.domain.card;

import br.com.fifa.futcrawler.domain.biography.Biography;
import br.com.fifa.futcrawler.domain.club.Club;
import br.com.fifa.futcrawler.domain.position.Position;

public class Card {

    private String name;
    private Biography biography;
    private Position position;
    private Club club;

    public Card(String name, Biography biography, Position position, Club club) {
        this.name = name;
        this.biography = biography;
        this.position = position;
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public Biography getBiography() {
        return biography;
    }

    public Position getPosition() {
        return position;
    }

    public Club getClub() {
        return club;
    }
}
