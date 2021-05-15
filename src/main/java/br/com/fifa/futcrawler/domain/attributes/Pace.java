package br.com.fifa.futcrawler.domain.attributes;

public class Pace<T> {

    private T acceleration;
    private T sprintSpeed;

    public Pace(T acceleration, T sprintSpeed) {
        this.acceleration = acceleration;
        this.sprintSpeed = sprintSpeed;
    }

    public T getAcceleration() {
        return acceleration;
    }

    public T getSprintSpeed() {
        return sprintSpeed;
    }
}
