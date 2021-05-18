package br.com.fifa.futcrawler.application.attributes.chemistry.response;

import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;

public class ChemistryResponse {

    private Long id;
    private ChemistryType chemistry;

    public ChemistryResponse(Long id, ChemistryType chemistry) {
        this.id = id;
        this.chemistry = chemistry;
    }

    public Long getId() {
        return id;
    }

    public ChemistryType getChemistry() {
        return chemistry;
    }
}
