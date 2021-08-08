package br.com.fifa.futcrawler.infrastructure.card.custom;

import br.com.fifa.futcrawler.domain.attributes.chemistry.ChemistryType;
import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.card.dto.CardFilterDTO;
import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity_;
import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.ChemistryEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.chemistry.ChemistryEntity_;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightEntity_;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity_;
import br.com.fifa.futcrawler.infrastructure.price.PriceEntity;
import br.com.fifa.futcrawler.infrastructure.price.PriceEntity_;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardJpaRepositoryImpl implements CardJpaCustomRepository {

    private static final int NUMBER_ONE = 1;
    private static final Integer MAX_VALUE_ATTRIBUTE = Integer.valueOf(99);
    private static final String LEAST_SQL_FUNCTION = "LEAST";
    private static final String WILDCARD_PERCENT = "%";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CardDTO> findAllByAttributesType(Role position, Long idCard, String nation, String league,
                                                 BigDecimal price, BigDecimal thrustValue, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();

        Root<CardEntity> rootCard = query.from(CardEntity.class);
        Root<AttributesPlayerEntity> rootAttributes = query.from(AttributesPlayerEntity.class);
        Root<AttributesWeightEntity> rootWeight = query.from(AttributesWeightEntity.class);
        Root<ChemistryEntity> rootChemistry = query.from(ChemistryEntity.class);
        Root<PriceEntity> rootPrice = query.from(PriceEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(rootCard.get(CardEntity_.id),
                rootAttributes.get(AttributesPlayerEntity_.card).get(CardEntity_.id)));
        predicates.add(builder.equal(rootWeight.get(AttributesWeightEntity_.position), position));
        predicates.add(builder.equal(rootCard.get(CardEntity_.id),
                rootPrice.get(PriceEntity_.card).get(CardEntity_.id)));

        if (idCard != null) {
            predicates.add(builder.equal(rootCard.get(CardEntity_.id), idCard));
        } else {
            predicates.add(builder.gt(rootPrice.get(PriceEntity_.currentValue), BigDecimal.ZERO));
        }

        if (nation != null) {
            predicates.add(builder.equal(rootCard.get(CardEntity_.nation), nation));
        }

        if (league != null) {
            predicates.add(builder.equal(rootCard.get(CardEntity_.league), league));
        }

        if (price != null) {
            predicates.add(builder.le(rootPrice.get(PriceEntity_.currentValue), price));
        }

        Expression<BigDecimal> overall = generateOverall(thrustValue, builder, rootAttributes, rootWeight, rootChemistry);

        query.select(builder.construct(
                CardDTO.class,
                rootCard.get(CardEntity_.id),
                rootCard.get(CardEntity_.name),
                rootCard.get(CardEntity_.club),
                rootCard.get(CardEntity_.league),
                rootCard.get(CardEntity_.nation),
                rootCard.get(CardEntity_.position),
                rootCard.get(CardEntity_.revision),
                builder.max(overall),
                rootPrice.get(PriceEntity_.currentValue)))
        .where(predicates.toArray(new Predicate[predicates.size()]))
        .groupBy(
                rootCard.get(CardEntity_.id),
                rootCard.get(CardEntity_.name),
                rootCard.get(CardEntity_.club),
                rootCard.get(CardEntity_.league),
                rootCard.get(CardEntity_.nation),
                rootCard.get(CardEntity_.position),
                rootCard.get(CardEntity_.revision),
                rootPrice.get(PriceEntity_.currentValue))
        .orderBy(builder.desc(builder.max(overall)));

        return entityManager
                .createQuery(query)
                .setFirstResult(getFromIndex(pageable.getPageNumber(), pageable.getPageSize()))
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    @Override
    public ChemistryType findBestChemistryByCard(Long idCard, Role position, BigDecimal thrustValue) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChemistryType> query = builder.createQuery(ChemistryType.class);

        Root<ChemistryEntity> rootChemistry = query.from(ChemistryEntity.class);
        Root<CardEntity> rootCard = query.from(CardEntity.class);
        Root<AttributesPlayerEntity> rootAttributes = query.from(AttributesPlayerEntity.class);
        Root<AttributesWeightEntity> rootWeight = query.from(AttributesWeightEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(rootCard.get(CardEntity_.id),
                rootAttributes.get(AttributesPlayerEntity_.card).get(CardEntity_.id)));
        predicates.add(builder.equal(rootCard.get(CardEntity_.id), idCard));
        predicates.add(builder.equal(rootWeight.get(AttributesWeightEntity_.position), position));

        Expression<BigDecimal> overall = generateOverall(thrustValue, builder, rootAttributes,
                rootWeight, rootChemistry);

        Path<ChemistryType> name = rootChemistry.get(ChemistryEntity_.name);

        query.select(name)
                .where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(builder.desc(overall));

        return entityManager
                .createQuery(query)
                .setMaxResults(NUMBER_ONE)
                .getSingleResult();
    }

    @Override
    public List<CardDTO> findByFilters(CardFilterDTO filterDTO) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();

        Root<CardEntity> rootCard = query.from(CardEntity.class);
        Root<PriceEntity> rootPrice = query.from(PriceEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(rootCard.get(CardEntity_.id),
                rootPrice.get(PriceEntity_.card).get(CardEntity_.id)));

        if (filterDTO.getId() != null) {
            predicates.add(builder.equal(rootCard.get(CardEntity_.id), filterDTO.getId()));
        }

        if (filterDTO.getName() != null) {
            predicates.add(builder.like(rootCard.get(CardEntity_.name),
                    WILDCARD_PERCENT + filterDTO.getName() + WILDCARD_PERCENT));
        }

        query.select(builder.construct(
                CardDTO.class,
                rootCard.get(CardEntity_.id),
                rootCard.get(CardEntity_.name),
                rootCard.get(CardEntity_.club),
                rootCard.get(CardEntity_.league),
                rootCard.get(CardEntity_.nation),
                rootCard.get(CardEntity_.position),
                rootCard.get(CardEntity_.revision),
                rootPrice.get(PriceEntity_.currentValue)))
                .where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager
                .createQuery(query)
                .getResultList();
    }

    private Expression<BigDecimal> generateOverall(BigDecimal thrustValue, CriteriaBuilder builder,
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
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.acceleration))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.acceleration)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SPRINT_SPEED),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.sprintSpeed))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.sprintSpeed)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.POSITIONING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.positioning))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.positioning)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.FINISHING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.finishing))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.finishing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SHOT_POWER),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.shotPower))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.shotPower)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.LONG_SHOTS),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.longShots))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.longShots)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.VOLLEYS),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.volleys))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.volleys)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.PENALTIES),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.penalties))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.penalties)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.VISION),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.vision))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.vision)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.CROSSING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.crossing))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.crossing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.FK_ACCURACY),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.fkAccuracy))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.fkAccuracy)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SHORT_PASSING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.shortPassing))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.shortPassing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.LONG_PASSING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.longPassing))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.longPassing)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.CURVE),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.curve))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.curve)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.AGILITY),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.agility))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.agility)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.BALANCE),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.balance))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.balance)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.REACTIONS),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.reactions))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.reactions)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.BALL_CONTROL),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.ballControl))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.ballControl)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.DRIBBLING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.dribbling))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.dribbling)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.COMPOSURE),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.composure))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.composure)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.INTERCEPTIONS),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.interceptions))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.interceptions)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.HEADING_ACCURACY),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.headingAccuracy))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.headingAccuracy)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.MARKING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.marking))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.marking)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.STADING_TACKLE),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.stadingTackle))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.stadingTackle)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.SLIDING_TACKLE),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.slidingTackle))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.slidingTackle)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.JUMPING),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.jumping))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.jumping)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.STAMINA),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.stamina))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.stamina)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.STRENGTH),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.strength))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.strength)));
        expressions.add(builder.prod(
                builder.function(
                        LEAST_SQL_FUNCTION,
                        BigDecimal.class,
                        builder.sum(
                                rootAttributes.get(AttributesPlayerEntity_.AGGRESSION),
                                builder.prod(
                                        builder.literal(thrustValue),
                                        rootChemistry.get(ChemistryEntity_.aggression))),
                        builder.literal(MAX_VALUE_ATTRIBUTE)),
                rootWeight.get(AttributesWeightEntity_.aggression)));

        for (Expression<BigDecimal> expression : expressions) {
            sum = builder.sum(sum, expression);
        }

        return sum;
    }

    private int getFromIndex(int page, int size) {
        return (page - NUMBER_ONE) * size;
    }
}
