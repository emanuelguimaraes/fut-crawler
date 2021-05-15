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
    private String name;

    @Column(name = "ALTURA", nullable = false)
    @Size(min = 1, max = 300, message = "Valor inválido para o campo Altura")
    private int height;

    @Column(name = "PESO", nullable = false)
    @Size(min = 1, max = 300, message = "Valor inválido para o campo Peso")
    private int weight;

    @Column(name = "CLUBE", nullable = false)
    private String club;

    @Column(name = "LIGA", nullable = false)
    private String league;

    @Column(name = "NACIONALIDADE", nullable = false)
    private String nation;

    @Column(name = "VERSAO", nullable = false)
    private String revision;

    @Column(name = "FINTA", nullable = false)
    @Size(min = 1, max = 5, message = "Valor inválido para o atributo de Finta")
    private int skills;

    @Column(name = "PERNA_BOA", nullable = false)
    @Enumerated(EnumType.STRING)
    private Foot foot;

    @Column(name = "PERNA_RUIM", nullable = false)
    @Size(min = 1, max = 5, message = "Valor inválido para o atributo de Perna Ruim")
    private int weakFoot;

    @Column(name = "POSICAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role position;

    @Column(name = "ID_RESOURCE", nullable = false)
    private Long idResource;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private AttributesEntity attributes;

    public CardEntity() { }

    public CardEntity(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.height = card.getBiography().getHeight();
        this.weight = card.getBiography().getWeight();
        this.club = card.getClub().getName();
        this.league = card.getClub().getLeague().getName();
        this.nation = card.getBiography().getNation();
        this.revision = card.getBiography().getRevision();
        this.skills = card.getBiography().getSkills();
        this.foot = card.getBiography().getFoot();
        this.weakFoot = card.getBiography().getWeakFoot();
        this.position = card.getPosition().getName();
        this.idResource = card.getBiography().getIdResource();

        if (card.getPosition() instanceof Goalkeeper) {
            this.attributes = new AttributesGoalkeeperEntity(this, (Goalkeeper) card.getPosition());
        }

        if (card.getPosition() instanceof Player) {
            this.attributes = new AttributesPlayerEntity(this, (Player) card.getPosition());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getClub() {
        return club;
    }

    public String getLeague() {
        return league;
    }

    public String getNation() {
        return nation;
    }

    public String getRevision() {
        return revision;
    }

    public int getSkills() {
        return skills;
    }

    public Foot getFoot() {
        return foot;
    }

    public int getWeakFoot() {
        return weakFoot;
    }

    public Role getPosition() {
        return position;
    }

    public Long getIdResource() {
        return idResource;
    }

    public AttributesEntity getAttributes() {
        return attributes;
    }
}
