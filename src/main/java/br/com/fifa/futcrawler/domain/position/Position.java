package br.com.fifa.futcrawler.domain.position;

import java.math.BigDecimal;
import java.util.Map;

public abstract class Position {

    private Role name;

    public Position(Role name) {
        this.name = name;
    }

    public Role getName() {
        return name;
    }

    public abstract BigDecimal getOverralByAttributes(Map<String, Integer> attributesLevel);

    protected BigDecimal multiplyAttribute(int attribute, Integer level) {
        return new BigDecimal(attribute)
                .multiply(new BigDecimal(level));
    }
}
