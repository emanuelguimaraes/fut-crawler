package br.com.fifa.futcrawler.domain.attributes;

public class Dribbling<T> {

    private T agility;
    private T balance;
    private T reactions;
    private T ballControl;
    private T dribbling;
    private T composure;

    public Dribbling(T agility, T balance, T reactions, T ballControl, T dribbling, T composure) {
        this.agility = agility;
        this.balance = balance;
        this.reactions = reactions;
        this.ballControl = ballControl;
        this.dribbling = dribbling;
        this.composure = composure;
    }

    public T getAgility() {
        return agility;
    }

    public T getBalance() {
        return balance;
    }

    public T getReactions() {
        return reactions;
    }

    public T getBallControl() {
        return ballControl;
    }

    public T getDribbling() {
        return dribbling;
    }

    public T getComposure() {
        return composure;
    }
}
