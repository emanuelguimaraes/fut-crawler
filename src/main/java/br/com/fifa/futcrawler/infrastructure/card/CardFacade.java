package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.biography.Biography;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.club.Club;
import br.com.fifa.futcrawler.domain.club.League;
import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.domain.position.Goalkeeper;
import br.com.fifa.futcrawler.domain.position.Player;
import br.com.fifa.futcrawler.domain.position.Position;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesGoalkeeperEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity;

public class CardFacade {

    public static Card criar(CardEntity cardEntity) {
        return new Card(cardEntity.getNome(),
                criarBiografia(cardEntity),
                criarPosicao(cardEntity),
                criarClube(cardEntity)
        );
    }

    private static Biography criarBiografia(CardEntity cardEntity) {
        return new Biography(
                cardEntity.getAltura(),
                cardEntity.getPeso(),
                cardEntity.getNacionalidade(),
                cardEntity.getVersao(),
                cardEntity.getFinta(),
                cardEntity.getPernaBoa(),
                cardEntity.getPernaRuim());
    }

    private static Position criarPosicao(CardEntity cardEntity) {
        if (Role.GK.equals(cardEntity.getPosicao())) {
            AttributesGoalkeeperEntity atributosGoleiro = (AttributesGoalkeeperEntity) cardEntity.getAtributos();
            return new Goalkeeper(
                    atributosGoleiro.getMergulho(),
                    atributosGoleiro.getJogoDeMaos(),
                    atributosGoleiro.getPontape(),
                    atributosGoleiro.getPosicionamento(),
                    atributosGoleiro.getReflexos(),
                    atributosGoleiro.getVelocidade()
            );
        } else {
            AttributesPlayerEntity atributosJogador = (AttributesPlayerEntity) cardEntity.getAtributos();
            return new Player(
                    cardEntity.getPosicao(),
                    criarAtributosVelocidade(atributosJogador),
                    criarAtributosFinalizacao(atributosJogador),
                    criarAtributosPasse(atributosJogador),
                    criarAtributosDrible(atributosJogador),
                    criarAtributosDefesa(atributosJogador),
                    criarAtributosFisico(atributosJogador)
            );
        }
    }

    private static Club criarClube(CardEntity cardEntity) {
        return new Club(
                cardEntity.getClube(),
                new League(cardEntity.getLiga())
        );
    }

    private static Pace criarAtributosVelocidade(AttributesPlayerEntity atributos) {
        return new Pace(
                atributos.getAceleracao(),
                atributos.getSprint()
        );
    }

    private static Shooting criarAtributosFinalizacao(AttributesPlayerEntity atributos) {
        return new Shooting(
                atributos.getPosicionamento(),
                atributos.getFinalizacao(),
                atributos.getPotencia(),
                atributos.getChuteDeLonge(),
                atributos.getChutesAcrobaticos(),
                atributos.getPenalti()
        );
    }

    private static Passing criarAtributosPasse(AttributesPlayerEntity atributos) {
        return new Passing(
                atributos.getVisao(),
                atributos.getCruzamento(),
                atributos.getCobrancaFalta(),
                atributos.getPasseCurto(),
                atributos.getPasseLongo(),
                atributos.getEfeito()
        );
    }

    private static Dribbling criarAtributosDrible(AttributesPlayerEntity atributos) {
        return new Dribbling(
                atributos.getAgilidade(),
                atributos.getEquilibrio(),
                atributos.getReflexos(),
                atributos.getControleDeBola(),
                atributos.getDrible(),
                atributos.getCompostura()
        );
    }

    private static Defending criarAtributosDefesa(AttributesPlayerEntity atributos) {
        return new Defending(
                atributos.getInterceptacao(),
                atributos.getCabeceamento(),
                atributos.getMarcacao(),
                atributos.getCarrinho(),
                atributos.getCorteEmPe()
        );
    }

    private static Physicality criarAtributosFisico(AttributesPlayerEntity atributos) {
        return new Physicality(
                atributos.getSalto(),
                atributos.getResistencia(),
                atributos.getForca(),
                atributos.getAgressividade()
        );
    }
}
