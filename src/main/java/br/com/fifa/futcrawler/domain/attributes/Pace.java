package br.com.fifa.futcrawler.domain.attributes;

public class Pace {

    private int acceleration;
    private int sprintSpeed;

    public Pace(int acceleration, int sprintSpeed) {
        this.acceleration = acceleration;
        this.sprintSpeed = sprintSpeed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getSprintSpeed() {
        return sprintSpeed;
    }
}
