package br.com.fifa.futcrawler.domain.biography;

public class Biography {

    private int height;
    private int weight;
    private String nation;
    private String revision;
    private int skills;
    private Foot foot;
    private int weakFoot;
    private Long idResource;

    public Biography(int height, int weight, String nation, String revision, int skills,
                     Foot foot, int weakFoot) {
        this.height = height;
        this.weight = weight;
        this.nation = nation;
        this.revision = revision;
        this.skills = skills;
        this.foot = foot;
        this.weakFoot = weakFoot;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
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

    public void setIdResource(Long idResource) {
        this.idResource = idResource;
    }

    public Long getIdResource() {
        return idResource;
    }
}
