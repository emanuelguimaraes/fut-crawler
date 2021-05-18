package br.com.fifa.futcrawler.infrastructure.attributes.chemistry.custom;

import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity_;
import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.ChemistryEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.ChemistryEntity_;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightEntity_;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChemistryJpaRepositoryImpl implements ChemistryJpaCustomRepository {

    private static final Integer MAX_VALUE_ATTRIBUTE = Integer.valueOf(99);
    private static final String LEAST_SQL_FUNCTION = "LEAST";
    private static final int FIRST_ITEM = 1;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ChemistryEntity findBestChemistryByCard(Long idCard, Role position) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChemistryEntity> query = builder.createQuery(ChemistryEntity.class);

        Root<ChemistryEntity> rootChemistry = query.from(ChemistryEntity.class);
        Root<CardEntity> rootCard = query.from(CardEntity.class);
        Root<AttributesPlayerEntity> rootAttributes = query.from(AttributesPlayerEntity.class);
        Root<AttributesWeightEntity> rootWeight = query.from(AttributesWeightEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(rootCard.get(CardEntity_.id),
                rootAttributes.get(AttributesPlayerEntity_.card).get(CardEntity_.id)));
        predicates.add(builder.equal(rootCard.get(CardEntity_.id), idCard));
        predicates.add(builder.equal(rootWeight.get(AttributesWeightEntity_.position), position));

        Expression<BigDecimal> overall = generateOverall(builder, rootAttributes, rootWeight, rootChemistry);

        query.select(rootChemistry)
                .where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(builder.desc(overall));

        return entityManager
                .createQuery(query)
                .setMaxResults(FIRST_ITEM)
                .getSingleResult();
    }

    private Expression<BigDecimal> generateOverall(CriteriaBuilder builder,
                                                   Root<AttributesPlayerEntity> rootAttributes,
                                                   Root<AttributesWeightEntity> rootWeight,
                                                   Root<ChemistryEntity> rootChemistry) {
        List<Expression<BigDecimal>> expressions = new ArrayList<>();
        Expression<BigDecimal> sum = builder.literal(BigDecimal.ZERO);

        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.ACCELERATION),
                                rootChemistry.get(ChemistryEntity_.acceleration)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.acceleration)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SPRINT_SPEED),
                                rootChemistry.get(ChemistryEntity_.sprintSpeed)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.sprintSpeed)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.POSITIONING),
                                rootChemistry.get(ChemistryEntity_.positioning)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.positioning)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.FINISHING),
                                rootChemistry.get(ChemistryEntity_.finishing)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.finishing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SHOT_POWER),
                                rootChemistry.get(ChemistryEntity_.shotPower)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.shotPower)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.LONG_SHOTS),
                                rootChemistry.get(ChemistryEntity_.longShots)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.longShots)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.VOLLEYS),
                                rootChemistry.get(ChemistryEntity_.volleys)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.volleys)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.PENALTIES),
                                rootChemistry.get(ChemistryEntity_.penalties)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.penalties)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.VISION),
                                rootChemistry.get(ChemistryEntity_.vision)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.vision)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.CROSSING),
                                rootChemistry.get(ChemistryEntity_.crossing)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.crossing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.FK_ACCURACY),
                                rootChemistry.get(ChemistryEntity_.fkAccuracy)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.fkAccuracy)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SHORT_PASSING),
                                rootChemistry.get(ChemistryEntity_.shortPassing)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.shortPassing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.LONG_PASSING),
                                rootChemistry.get(ChemistryEntity_.longPassing)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.longPassing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.CURVE),
                                rootChemistry.get(ChemistryEntity_.curve)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.curve)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.AGILITY),
                                rootChemistry.get(ChemistryEntity_.agility)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.agility)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.BALANCE),
                                rootChemistry.get(ChemistryEntity_.balance)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.balance)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.REACTIONS),
                                rootChemistry.get(ChemistryEntity_.reactions)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.reactions)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.BALL_CONTROL),
                                rootChemistry.get(ChemistryEntity_.ballControl)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.ballControl)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.DRIBBLING),
                                rootChemistry.get(ChemistryEntity_.dribbling)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.dribbling)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.COMPOSURE),
                                rootChemistry.get(ChemistryEntity_.composure)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.composure)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.INTERCEPTIONS),
                                rootChemistry.get(ChemistryEntity_.interceptions)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.interceptions)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.HEADING_ACCURACY),
                                rootChemistry.get(ChemistryEntity_.headingAccuracy)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.headingAccuracy)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.MARKING),
                                rootChemistry.get(ChemistryEntity_.marking)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.marking)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.STADING_TACKLE),
                                rootChemistry.get(ChemistryEntity_.stadingTackle)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.stadingTackle)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SLIDING_TACKLE),
                                rootChemistry.get(ChemistryEntity_.slidingTackle)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.slidingTackle)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.JUMPING),
                                rootChemistry.get(ChemistryEntity_.jumping)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.jumping)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.STAMINA),
                                rootChemistry.get(ChemistryEntity_.stamina)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.stamina)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.STRENGTH),
                                rootChemistry.get(ChemistryEntity_.strength)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.strength)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.AGGRESSION),
                                rootChemistry.get(ChemistryEntity_.aggression)),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.aggression)));

        for (Expression<BigDecimal> expression : expressions) {
            sum = builder.sum(sum, expression);
        }

        return sum;
    }
}
