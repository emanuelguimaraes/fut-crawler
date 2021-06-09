package br.com.fifa.futcrawler.domain.card;

import br.com.fifa.futcrawler.domain.biography.Biography;
import br.com.fifa.futcrawler.domain.club.Club;
import br.com.fifa.futcrawler.domain.position.Position;
import br.com.fifa.futcrawler.domain.price.Price;

public class Card {

    private String name;
    private Biography biography;
    private Position position;
    private Club club;
    private Price price;

    public Card(String name, Biography biography, Position position, Club club, Price price) {
        this.name = name;
        this.biography = biography;
        this.position = position;
        this.club = club;
        this.price = price;
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

    public Price getPrice() {
        return price;
    }

    public void updatePrice(Price price) {
        this.price = price;
    }
}
