package br.com.fifa.futcrawler.domain.attributes.chemistry;

public abstract class Chemistry {

    private ChemistryType name;

    public Chemistry(ChemistryType name) {
        this.name = name;
    }

    public ChemistryType getName() {
        return name;
    }
}
