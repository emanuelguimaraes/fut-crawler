package br.com.fifa.futcrawler.application.crawler.dto;

import br.com.fifa.futcrawler.application.crawler.util.CrawlerUtil;
import br.com.fifa.futcrawler.domain.attributes.*;
import br.com.fifa.futcrawler.domain.biography.Biography;
import br.com.fifa.futcrawler.domain.biography.Foot;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.club.Club;
import br.com.fifa.futcrawler.domain.club.League;
import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.domain.position.Goalkeeper;
import br.com.fifa.futcrawler.domain.position.Player;
import br.com.fifa.futcrawler.domain.position.Position;

import java.util.Map;

public class CardDetailsDTO {

    private String nome;
    private int altura;
    private int peso;
    private String nacionalidade;
    private String versao;
    private int finta;
    private Foot pernaBoa;
    private int pernaRuim;
    private Role posicao;
    private int aceleracao;
    private int sprint;
    private int posicionamento;
    private int finalizacao;
    private int potencia;
    private int chuteDeLonge;
    private int chutesAcrobaticos;
    private int penalti;
    private int visao;
    private int cruzamento;
    private int cobrancaFalta;
    private int passeCurto;
    private int passeLongo;
    private int efeito;
    private int agilidade;
    private int equilibrio;
    private int reflexos;
    private int controleDeBola;
    private int drible;
    private int compostura;
    private int interceptacao;
    private int cabeceamento;
    private int marcacao;
    private int carrinho;
    private int corteEmPe;
    private int salto;
    private int resistencia;
    private int forca;
    private int agressividade;
    private int mergulho;
    private int jogoDeMaos;
    private int pontape;
    private int posicionamentoGoleiro;
    private int reflexosGoleiro;
    private int velocidade;
    private String clube;
    private String liga;
    private Long idResource;

    public CardDetailsDTO(Map<String, String> fields) {
        nome = parseString(fields.get(CrawlerUtil.NOME));
        altura = parseHeight(fields.get(CrawlerUtil.ALTURA));
        peso = parseInt(fields.get(CrawlerUtil.PESO));
        nacionalidade = parseString(fields.get(CrawlerUtil.NACIONALIDADE));
        versao = parseString(fields.get(CrawlerUtil.VERSAO));
        finta = parseInt(fields.get(CrawlerUtil.FINTA));
        pernaBoa = parsePerna(fields.get(CrawlerUtil.PERNA_BOA));
        pernaRuim = parseInt(fields.get(CrawlerUtil.PERNA_RUIM));
        posicao = parseFuncao(fields.get(CrawlerUtil.POSICAO));
        clube = parseString(fields.get(CrawlerUtil.CLUBE));
        liga = parseString(fields.get(CrawlerUtil.LIGA));
        idResource = parseLong(fields.get(CrawlerUtil.ID_RESOURCE));

        if (posicao.equals(Role.GK)) {
            mergulho = parseInt(fields.get(CrawlerUtil.MERGULHO));
            jogoDeMaos = parseInt(fields.get(CrawlerUtil.JOGO_DE_MAOS));
            pontape = parseInt(fields.get(CrawlerUtil.PONTAPE));
            posicionamentoGoleiro = parseInt(fields.get(CrawlerUtil.POSICIONAMENTO_GOLEIRO));
            reflexosGoleiro = parseInt(fields.get(CrawlerUtil.REFLEXOS_GOLEIRO));
            velocidade = parseInt(fields.get(CrawlerUtil.VELOCIDADE));
        } else {
            aceleracao = parseInt(fields.get(CrawlerUtil.ACELERACAO));
            sprint = parseInt(fields.get(CrawlerUtil.SPRINT));
            posicionamento = parseInt(fields.get(CrawlerUtil.POSICIONAMENTO));
            finalizacao = parseInt(fields.get(CrawlerUtil.FINALIZACAO));
            potencia = parseInt(fields.get(CrawlerUtil.POTENCIA));
            chuteDeLonge = parseInt(fields.get(CrawlerUtil.CHUTE_DE_LONGE));
            chutesAcrobaticos = parseInt(fields.get(CrawlerUtil.CHUTES_ACROBATICOS));
            penalti = parseInt(fields.get(CrawlerUtil.PENALTI));
            visao = parseInt(fields.get(CrawlerUtil.VISAO));
            cruzamento = parseInt(fields.get(CrawlerUtil.CRUZAMENTO));
            cobrancaFalta = parseInt(fields.get(CrawlerUtil.COBRANCA_FALTA));
            passeCurto = parseInt(fields.get(CrawlerUtil.PASSE_CURTO));
            passeLongo = parseInt(fields.get(CrawlerUtil.PASSE_LONGO));
            efeito = parseInt(fields.get(CrawlerUtil.EFEITO));
            agilidade = parseInt(fields.get(CrawlerUtil.AGILIDADE));
            equilibrio = parseInt(fields.get(CrawlerUtil.EQUILIBRIO));
            reflexos = parseInt(fields.get(CrawlerUtil.REFLEXOS));
            controleDeBola = parseInt(fields.get(CrawlerUtil.CONTROLE_DE_BOLA));
            drible = parseInt(fields.get(CrawlerUtil.DRIBLE));
            compostura = parseInt(fields.get(CrawlerUtil.COMPOSTURA));
            interceptacao = parseInt(fields.get(CrawlerUtil.INTERCEPTACAO));
            cabeceamento = parseInt(fields.get(CrawlerUtil.CABECEAMENTO));
            marcacao = parseInt(fields.get(CrawlerUtil.MARCACAO));
            carrinho = parseInt(fields.get(CrawlerUtil.CARRINHO));
            corteEmPe = parseInt(fields.get(CrawlerUtil.CORTE_EM_PE));
            salto = parseInt(fields.get(CrawlerUtil.SALTO));
            resistencia = parseInt(fields.get(CrawlerUtil.RESISTENCIA));
            forca = parseInt(fields.get(CrawlerUtil.FORCA));
            agressividade = parseInt(fields.get(CrawlerUtil.AGRESSIVIDADE));
        }
    }

    public Card parseFromCartao() {
        Card card = new Card(this.nome,
                criarBiografia(),
                criarPosicao(),
                criarClube());

        card.getBiography().setIdResource(this.idResource);

        return card;
    }

    private Biography criarBiografia() {
        return new Biography(
                this.altura,
                this.peso,
                this.nacionalidade,
                this.versao,
                this.finta,
                this.pernaBoa,
                this.pernaRuim);
    }

    private Position criarPosicao() {
        if (Role.GK.equals(this.posicao)) {
            return new Goalkeeper(
                    this.mergulho,
                    this.jogoDeMaos,
                    this.pontape,
                    this.posicionamentoGoleiro,
                    this.reflexosGoleiro,
                    this.velocidade
            );
        } else {
            return new Player(
                    this.posicao,
                    criarAtributosVelocidade(),
                    criarAtributosFinalizacao(),
                    criarAtributosPasse(),
                    criarAtributosDrible(),
                    criarAtributosDefesa(),
                    criarAtributosFisico()
            );
        }
    }

    private Club criarClube() {
        return new Club(
                this.clube,
                new League(this.liga)
        );
    }

    private Pace criarAtributosVelocidade() {
        return new Pace(
                this.aceleracao,
                this.sprint
        );
    }

    private Shooting criarAtributosFinalizacao() {
        return new Shooting(
                this.posicionamento,
                this.finalizacao,
                this.potencia,
                this.chuteDeLonge,
                this.chutesAcrobaticos,
                this.penalti
        );
    }

    private Passing criarAtributosPasse() {
        return new Passing(
                this.visao,
                this.cruzamento,
                this.cobrancaFalta,
                this.passeCurto,
                this.passeLongo,
                this.efeito
        );
    }

    private Dribbling criarAtributosDrible() {
        return new Dribbling(
                this.agilidade,
                this.equilibrio,
                this.reflexos,
                this.controleDeBola,
                this.drible,
                this.compostura
        );
    }

    private Defending criarAtributosDefesa() {
        return new Defending(
                this.interceptacao,
                this.cabeceamento,
                this.marcacao,
                this.carrinho,
                this.corteEmPe
        );
    }

    private Physicality criarAtributosFisico() {
        return new Physicality(
                this.salto,
                this.resistencia,
                this.forca,
                this.agressividade
        );
    }

    private Integer parseHeight(String altura) {
        try {
            String[] arrayAltura = altura.split("cm");

            return Integer.parseInt(arrayAltura[0].trim());

        } catch (Exception e) {
            return Integer.valueOf(1);
        }
    }

    private Long parseLong(String value) {
        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return Long.valueOf(1L);
        }
    }

    private Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return Integer.valueOf(1);
        }
    }

    private Role parseFuncao(String value) {
        try {
            return Role.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Role.NAN;
        }
    }

    private Foot parsePerna(String value) {
        try {
            return Foot.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Foot.NAN;
        }
    }

    private String parseString(String value) {
        if (value.isBlank()) {
            return "NAN";
        }

        return value;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public int getFinta() {
        return finta;
    }

    public void setFinta(int finta) {
        this.finta = finta;
    }

    public Foot getPernaBoa() {
        return pernaBoa;
    }

    public void setPernaBoa(Foot pernaBoa) {
        this.pernaBoa = pernaBoa;
    }

    public int getPernaRuim() {
        return pernaRuim;
    }

    public void setPernaRuim(int pernaRuim) {
        this.pernaRuim = pernaRuim;
    }

    public Role getPosicao() {
        return posicao;
    }

    public void setPosicao(Role posicao) {
        this.posicao = posicao;
    }

    public int getAceleracao() {
        return aceleracao;
    }

    public void setAceleracao(int aceleracao) {
        this.aceleracao = aceleracao;
    }

    public int getSprint() {
        return sprint;
    }

    public void setSprint(int sprint) {
        this.sprint = sprint;
    }

    public int getPosicionamento() {
        return posicionamento;
    }

    public void setPosicionamento(int posicionamento) {
        this.posicionamento = posicionamento;
    }

    public int getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getChuteDeLonge() {
        return chuteDeLonge;
    }

    public void setChuteDeLonge(int chuteDeLonge) {
        this.chuteDeLonge = chuteDeLonge;
    }

    public int getChutesAcrobaticos() {
        return chutesAcrobaticos;
    }

    public void setChutesAcrobaticos(int chutesAcrobaticos) {
        this.chutesAcrobaticos = chutesAcrobaticos;
    }

    public int getPenalti() {
        return penalti;
    }

    public void setPenalti(int penalti) {
        this.penalti = penalti;
    }

    public int getVisao() {
        return visao;
    }

    public void setVisao(int visao) {
        this.visao = visao;
    }

    public int getCruzamento() {
        return cruzamento;
    }

    public void setCruzamento(int cruzamento) {
        this.cruzamento = cruzamento;
    }

    public int getCobrancaFalta() {
        return cobrancaFalta;
    }

    public void setCobrancaFalta(int cobrancaFalta) {
        this.cobrancaFalta = cobrancaFalta;
    }

    public int getPasseCurto() {
        return passeCurto;
    }

    public void setPasseCurto(int passeCurto) {
        this.passeCurto = passeCurto;
    }

    public int getPasseLongo() {
        return passeLongo;
    }

    public void setPasseLongo(int passeLongo) {
        this.passeLongo = passeLongo;
    }

    public int getEfeito() {
        return efeito;
    }

    public void setEfeito(int efeito) {
        this.efeito = efeito;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getEquilibrio() {
        return equilibrio;
    }

    public void setEquilibrio(int equilibrio) {
        this.equilibrio = equilibrio;
    }

    public int getReflexos() {
        return reflexos;
    }

    public void setReflexos(int reflexos) {
        this.reflexos = reflexos;
    }

    public int getControleDeBola() {
        return controleDeBola;
    }

    public void setControleDeBola(int controleDeBola) {
        this.controleDeBola = controleDeBola;
    }

    public int getDrible() {
        return drible;
    }

    public void setDrible(int drible) {
        this.drible = drible;
    }

    public int getCompostura() {
        return compostura;
    }

    public void setCompostura(int compostura) {
        this.compostura = compostura;
    }

    public int getInterceptacao() {
        return interceptacao;
    }

    public void setInterceptacao(int interceptacao) {
        this.interceptacao = interceptacao;
    }

    public int getCabeceamento() {
        return cabeceamento;
    }

    public void setCabeceamento(int cabeceamento) {
        this.cabeceamento = cabeceamento;
    }

    public int getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(int marcacao) {
        this.marcacao = marcacao;
    }

    public int getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(int carrinho) {
        this.carrinho = carrinho;
    }

    public int getCorteEmPe() {
        return corteEmPe;
    }

    public void setCorteEmPe(int corteEmPe) {
        this.corteEmPe = corteEmPe;
    }

    public int getSalto() {
        return salto;
    }

    public void setSalto(int salto) {
        this.salto = salto;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getAgressividade() {
        return agressividade;
    }

    public void setAgressividade(int agressividade) {
        this.agressividade = agressividade;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public int getMergulho() {
        return mergulho;
    }

    public void setMergulho(int mergulho) {
        this.mergulho = mergulho;
    }

    public int getJogoDeMaos() {
        return jogoDeMaos;
    }

    public void setJogoDeMaos(int jogoDeMaos) {
        this.jogoDeMaos = jogoDeMaos;
    }

    public int getPontape() {
        return pontape;
    }

    public void setPontape(int pontape) {
        this.pontape = pontape;
    }

    public int getPosicionamentoGoleiro() {
        return posicionamentoGoleiro;
    }

    public void setPosicionamentoGoleiro(int posicionamentoGoleiro) {
        this.posicionamentoGoleiro = posicionamentoGoleiro;
    }

    public int getReflexosGoleiro() {
        return reflexosGoleiro;
    }

    public void setReflexosGoleiro(int reflexosGoleiro) {
        this.reflexosGoleiro = reflexosGoleiro;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public Long getIdResource() {
        return idResource;
    }
}
