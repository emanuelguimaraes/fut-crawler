package br.com.fifa.futcrawler.application.attributes.chemistry;

import br.com.fifa.futcrawler.application.attributes.chemistry.request.ChemistryRequest;
import br.com.fifa.futcrawler.application.attributes.chemistry.response.ChemistryResponse;
import br.com.fifa.futcrawler.domain.attributes.chemistry.Chemistry;
import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryRepository;

public class SaveChemistry {

    private final ChemistryRepository repository;

    public SaveChemistry(ChemistryRepository repository) {
        this.repository = repository;
    }

    public ChemistryResponse execute(ChemistryRequest request) {
        Chemistry chemistry = request.parseFromChemistry();
        repository.save(chemistry);

        return new ChemistryResponse(String.format(
                "O estilo de química %s foi carregado com sucesso,",
                request.getName().toString()));
    }
}
