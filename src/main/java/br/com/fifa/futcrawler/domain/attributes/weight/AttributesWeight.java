package br.com.fifa.futcrawler.domain.attributes.weight;

import br.com.fifa.futcrawler.domain.position.Role;

public abstract class AttributesWeight {

    private Role position;

    public AttributesWeight(Role position) {
        this.position = position;
    }

    public Role getPosition() {
        return position;
    }
}
