package br.com.fifa.futcrawler.domain.attributes;

public class Dribbling {

    private int agility;
    private int balance;
    private int reactions;
    private int ballControl;
    private int dribbling;
    private int composure;

    public Dribbling(int agility, int balance, int reactions, int ballControl, int dribbling, int composure) {
        this.agility = agility;
        this.balance = balance;
        this.reactions = reactions;
        this.ballControl = ballControl;
        this.dribbling = dribbling;
        this.composure = composure;
    }

    public int getAgility() {
        return agility;
    }

    public int getBalance() {
        return balance;
    }

    public int getReactions() {
        return reactions;
    }

    public int getBallControl() {
        return ballControl;
    }

    public int getDribbling() {
        return dribbling;
    }

    public int getComposure() {
        return composure;
    }
}
