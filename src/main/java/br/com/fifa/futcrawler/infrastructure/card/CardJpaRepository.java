package br.com.fifa.futcrawler.infrastructure.card;

import br.com.fifa.futcrawler.domain.card.dto.CardInfoUpdateDTO;
import br.com.fifa.futcrawler.domain.price.Price;
import br.com.fifa.futcrawler.infrastructure.card.custom.CardJpaCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, Long>, CardJpaCustomRepository {

    @Query("SELECT c FROM CardEntity c WHERE c.name = :name AND c.revision = :revision")
    Optional<CardEntity> findByNomeAndVersao(String name, String revision);

    @Query("SELECT c.idResource FROM CardEntity c WHERE c.idResource IN (:resourceIds)")
    List<Long> findByResourceIds(List<Long> resourceIds);

    @Query("SELECT new br.com.fifa.futcrawler.domain.card.dto.CardInfoUpdateDTO(c.id, c.idResource) " +
            "FROM CardEntity c WHERE c.id BETWEEN :initialId AND :finalId")
    List<CardInfoUpdateDTO> findAllOnlyIdsResource(Long initialId, Long finalId);

    @Query("SELECT c.idResource FROM CardEntity c WHERE c.id = :id")
    Long findOnlyResourceId(Long id);

    @Modifying
    @Query("UPDATE PriceEntity p SET p.currentValue = :currentValue, p.minValue = :minValue, p.maxValue = :maxValue, " +
            "p.updatedAt = :updatedAt WHERE p.card.id = :id")
    int updatePrice(Long id, BigDecimal currentValue, BigDecimal minValue,
                     BigDecimal maxValue, LocalDateTime updatedAt);
}
