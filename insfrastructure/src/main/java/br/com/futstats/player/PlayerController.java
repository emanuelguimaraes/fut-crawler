package br.com.futstats.player;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/player")
public class PlayerController {

    private final PlayerUseCase useCase;

    public PlayerController() {
        this.useCase = new PlayerUseCase();
    }

    @GetMapping
    public List<Player> listPlayers() {
        return useCase.listAllPlayers();
    }
}
