package org.abos.cards.core;

import java.util.Objects;

public class SimpleCard implements Card {

    private final CardType type;

    public SimpleCard(final CardType type) {
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public CardType getType() {
        return type;
    }
}
