package br.com.fifa.futcrawler.infrastructure.attributes.weight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributesWeightJpaRepository extends JpaRepository<AttributesWeightEntity, Long> {

}
