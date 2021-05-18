package br.com.fifa.futcrawler.infrastructure.attributes.chemistry;

import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.custom.ChemistryJpaCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemistryJpaRepository extends JpaRepository<ChemistryEntity, Long>, ChemistryJpaCustomRepository {

}
