package br.com.fifa.futcrawler.domain.attributes;

public class Physicality<T> {

    private T jumping;
    private T stamina;
    private T strength;
    private T aggression;

    public Physicality(T jumping, T stamina, T strength, T aggression) {
        this.jumping = jumping;
        this.stamina = stamina;
        this.strength = strength;
        this.aggression = aggression;
    }

    public T getJumping() {
        return jumping;
    }

    public T getStamina() {
        return stamina;
    }

    public T getStrength() {
        return strength;
    }

    public T getAggression() {
        return aggression;
    }
}
