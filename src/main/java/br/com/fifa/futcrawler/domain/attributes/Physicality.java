package br.com.fifa.futcrawler.domain.attributes;

public class Physicality {

    private int jumping;
    private int stamina;
    private int strength;
    private int aggression;

    public Physicality(int jumping, int stamina, int strength, int aggression) {
        this.jumping = jumping;
        this.stamina = stamina;
        this.strength = strength;
        this.aggression = aggression;
    }

    public int getJumping() {
        return jumping;
    }

    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public int getAggression() {
        return aggression;
    }
}
