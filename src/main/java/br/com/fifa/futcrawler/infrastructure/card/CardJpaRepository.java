package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.infrastructure.card.custom.CardJpaCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, Long>, CardJpaCustomRepository {

    @Query("SELECT c FROM CardEntity c WHERE c.name = :name AND c.revision = :revision")
    Optional<CardEntity> findByNomeAndVersao(String name, String revision);

    @Query("SELECT c FROM CardEntity c WHERE c.idResource = :idResource")
    List<CardEntity> findByIdResource(Long idResource);
}
