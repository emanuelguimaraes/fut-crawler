package br.com.futstats.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUseCase {

    public List<Player> listAllPlayers() {
        List<Player> players = new ArrayList<>();

        players.add(new Player("Jogador 1"));
        players.add(new Player("Jogador 2"));
        players.add(new Player("Jogador 3"));

        return players;
    }
}
