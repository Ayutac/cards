package org.abos.cards.core.simple;

import org.abos.cards.core.Card;
import org.abos.cards.core.CardType;

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
