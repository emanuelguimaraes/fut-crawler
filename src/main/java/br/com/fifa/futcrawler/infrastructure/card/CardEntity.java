package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.domain.biography.Foot;
import br.com.fifa.futcrawler.domain.card.Card;
import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.domain.position.Goalkeeper;
import br.com.fifa.futcrawler.domain.position.Player;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesGoalkeeperEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_CARTAO")
@SequenceGenerator(name = "CARTAO_GENERATOR", sequenceName = "SEQ_CARTAO", initialValue = 1, allocationSize = 1)
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARTAO_GENERATOR")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "ALTURA", nullable = false)
    @Size(min = 1, max = 300, message = "Valor inválido para o campo Altura")
    private int altura;

    @Column(name = "PESO", nullable = false)
    @Size(min = 1, max = 300, message = "Valor inválido para o campo Peso")
    private int peso;

    @Column(name = "CLUBE", nullable = false)
    private String clube;

    @Column(name = "LIGA", nullable = false)
    private String liga;

    @Column(name = "NACIONALIDADE", nullable = false)
    private String nacionalidade;

    @Column(name = "VERSAO", nullable = false)
    private String versao;

    @Column(name = "FINTA", nullable = false)
    @Size(min = 1, max = 5, message = "Valor inválido para o atributo de Finta")
    private int finta;

    @Column(name = "PERNA_BOA", nullable = false)
    @Enumerated(EnumType.STRING)
    private Foot pernaBoa;

    @Column(name = "PERNA_RUIM", nullable = false)
    @Size(min = 1, max = 5, message = "Valor inválido para o atributo de Perna Ruim")
    private int pernaRuim;

    @Column(name = "POSICAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role posicao;

    @Column(name = "ID_RESOURCE", nullable = false)
    private Long idResource;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private AttributesEntity atributos;

    public CardEntity() {

    }

    public CardEntity(Card card) {
        this.nome = card.getName();
        this.altura = card.getBiography().getHeight();
        this.peso = card.getBiography().getWeight();
        this.clube = card.getClub().getName();
        this.liga = card.getClub().getLeague().getName();
        this.nacionalidade = card.getBiography().getNation();
        this.versao = card.getBiography().getRevision();
        this.finta = card.getBiography().getSkills();
        this.pernaBoa = card.getBiography().getFoot();
        this.pernaRuim = card.getBiography().getWeakFoot();
        this.posicao = card.getPosition().getName();
        this.idResource = card.getBiography().getIdResource();

        if (card.getPosition() instanceof Goalkeeper) {
            this.atributos = new AttributesGoalkeeperEntity(this, (Goalkeeper) card.getPosition());
        }

        if (card.getPosition() instanceof Player) {
            this.atributos = new AttributesPlayerEntity(this, (Player) card.getPosition());
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public String getClube() {
        return clube;
    }

    public String getLiga() {
        return liga;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getVersao() {
        return versao;
    }

    public int getFinta() {
        return finta;
    }

    public Foot getPernaBoa() {
        return pernaBoa;
    }

    public int getPernaRuim() {
        return pernaRuim;
    }

    public Role getPosicao() {
        return posicao;
    }

    public Long getIdResource() {
        return idResource;
    }

    public AttributesEntity getAtributos() {
        return atributos;
    }
}
