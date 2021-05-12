package br.com.fifa.futcrawler.domain.position;

public abstract class Position {

    private Role name;

    public Position(Role name) {
        this.name = name;
    }

    public Role getName() {
        return name;
    }
}
