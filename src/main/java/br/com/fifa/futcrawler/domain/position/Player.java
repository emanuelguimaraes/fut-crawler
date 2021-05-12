package br.com.fifa.futcrawler.domain.position;

import br.com.fifa.futcrawler.domain.attributes.Defending;
import br.com.fifa.futcrawler.domain.attributes.Dribbling;
import br.com.fifa.futcrawler.domain.attributes.Shooting;
import br.com.fifa.futcrawler.domain.attributes.Physicality;
import br.com.fifa.futcrawler.domain.attributes.Passing;
import br.com.fifa.futcrawler.domain.attributes.Pace;

public class Player extends Position {

    private Pace pace;
    private Shooting shooting;
    private Passing passing;
    private Dribbling dribbling;
    private Defending defending;
    private Physicality physicality;

    public Player(Role nome, Pace pace, Shooting shooting, Passing passing, Dribbling dribbling, Defending defending, Physicality physicality) {
        super(nome);
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defending = defending;
        this.physicality = physicality;
    }

    public Pace getPace() {
        return pace;
    }

    public Shooting getShooting() {
        return shooting;
    }

    public Passing getPassing() {
        return passing;
    }

    public Dribbling getDribbling() {
        return dribbling;
    }

    public Defending getDefending() {
        return defending;
    }

    public Physicality getPhysicality() {
        return physicality;
    }
}
