package br.com.fifa.futcrawler.domain.position;

import br.com.fifa.futcrawler.domain.attributes.weight.AttributesWeight;

import java.math.BigDecimal;

public abstract class Position {

    private Role name;

    public Position(Role name) {
        this.name = name;
    }

    public Role getName() {
        return name;
    }

    public abstract BigDecimal getOverralByAttributes(AttributesWeight weight);

    protected BigDecimal multiplyAttribute(Integer attribute, BigDecimal level) {
        return new BigDecimal(attribute)
                .multiply(level);
    }
}
