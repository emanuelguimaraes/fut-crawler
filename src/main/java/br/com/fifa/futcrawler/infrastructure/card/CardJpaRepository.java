package br.com.fifa.futcrawler.infrastructure.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, Long> {

    @Query("SELECT c FROM CardEntity c WHERE c.nome = :nome AND c.versao = :versao")
    Optional<CardEntity> findByNomeAndVersao(String nome, String versao);

    @Query("SELECT c FROM CardEntity c WHERE c.idResource = :idResource")
    List<CardEntity> findByIdResource(Long idResource);

}
