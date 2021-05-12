package br.com.fifa.futcrawler.application;

public abstract class Command<T> {

    public abstract void execute(T dto);
}
