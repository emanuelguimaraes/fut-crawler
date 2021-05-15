package br.com.fifa.futcrawler.infrastructure.card.custom;

import br.com.fifa.futcrawler.domain.card.dto.CardDTO;
import br.com.fifa.futcrawler.domain.position.Role;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.AttributesPlayerEntity_;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightEntity;
import br.com.fifa.futcrawler.infrastructure.attributes.weight.AttributesWeightEntity_;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity;
import br.com.fifa.futcrawler.infrastructure.card.CardEntity_;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardJpaRepositoryImpl implements CardJpaCustomRepository {

    private static final int NUMBER_ONE = 1;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CardDTO> findAllByAttributesType(Role position, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();

        Root<CardEntity> rootCard = query.from(CardEntity.class);
        Root<AttributesPlayerEntity> rootAttributes = query.from(AttributesPlayerEntity.class);
        Root<AttributesWeightEntity> rootWeight = query.from(AttributesWeightEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(rootCard.get(CardEntity_.id),
                rootAttributes.get(AttributesPlayerEntity_.card).get(CardEntity_.id)));
        predicates.add(builder.equal(rootWeight.get(AttributesWeightEntity_.position), position));

        Expression<BigDecimal> overall = generateOverall(builder, rootAttributes, rootWeight);

        query.select(builder.construct(
                CardDTO.class,
                rootCard.get(CardEntity_.id),
                rootCard.get(CardEntity_.name),
                rootCard.get(CardEntity_.club),
                rootCard.get(CardEntity_.league),
                rootCard.get(CardEntity_.position),
                rootCard.get(CardEntity_.revision),
                overall))
        .where(predicates.toArray(new Predicate[predicates.size()]))
        .orderBy(builder.desc(overall));

        return entityManager
                .createQuery(query)
                .setFirstResult(getFromIndex(pageable.getPageNumber(), pageable.getPageSize()))
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    private Expression<BigDecimal> generateOverall(CriteriaBuilder builder,
                                                   Root<AttributesPlayerEntity> rootAttributes,
                                                   Root<AttributesWeightEntity> rootWeight) {
        List<Expression<BigDecimal>> expressions = new ArrayList<>();
        Expression<BigDecimal> sum = builder.literal(BigDecimal.ZERO);

        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.ACCELERATION),
                rootWeight.get(AttributesWeightEntity_.acceleration)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.SPRINT_SPEED),
                rootWeight.get(AttributesWeightEntity_.sprintSpeed)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.POSITIONING),
                rootWeight.get(AttributesWeightEntity_.positioning)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.FINISHING),
                rootWeight.get(AttributesWeightEntity_.finishing)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.SHOT_POWER),
                rootWeight.get(AttributesWeightEntity_.shotPower)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.LONG_SHOTS),
                rootWeight.get(AttributesWeightEntity_.longShots)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.VOLLEYS),
                rootWeight.get(AttributesWeightEntity_.volleys)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.PENALTIES),
                rootWeight.get(AttributesWeightEntity_.penalties)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.VISION),
                rootWeight.get(AttributesWeightEntity_.vision)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.CROSSING),
                rootWeight.get(AttributesWeightEntity_.crossing)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.FK_ACCURACY),
                rootWeight.get(AttributesWeightEntity_.fkAccuracy)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.SHORT_PASSING),
                rootWeight.get(AttributesWeightEntity_.shortPassing)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.LONG_PASSING),
                rootWeight.get(AttributesWeightEntity_.longPassing)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.CURVE),
                rootWeight.get(AttributesWeightEntity_.curve)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.AGILITY),
                rootWeight.get(AttributesWeightEntity_.agility)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.BALANCE),
                rootWeight.get(AttributesWeightEntity_.balance)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.REACTIONS),
                rootWeight.get(AttributesWeightEntity_.reactions)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.BALL_CONTROL),
                rootWeight.get(AttributesWeightEntity_.ballControl)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.DRIBBLING),
                rootWeight.get(AttributesWeightEntity_.dribbling)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.COMPOSURE),
                rootWeight.get(AttributesWeightEntity_.composure)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.INTERCEPTIONS),
                rootWeight.get(AttributesWeightEntity_.interceptions)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.HEADING_ACCURACY),
                rootWeight.get(AttributesWeightEntity_.headingAccuracy)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.MARKING),
                rootWeight.get(AttributesWeightEntity_.marking)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.STADING_TACKLE),
                rootWeight.get(AttributesWeightEntity_.stadingTackle)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.SLIDING_TACKLE),
                rootWeight.get(AttributesWeightEntity_.slidingTackle)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.JUMPING),
                rootWeight.get(AttributesWeightEntity_.jumping)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.STAMINA),
                rootWeight.get(AttributesWeightEntity_.stamina)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.STRENGTH),
                rootWeight.get(AttributesWeightEntity_.strength)));
        expressions.add(builder.prod(
                rootAttributes.get(AttributesPlayerEntity_.AGGRESSION),
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
