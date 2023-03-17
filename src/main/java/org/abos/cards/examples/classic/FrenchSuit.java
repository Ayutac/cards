package org.abos.cards.examples.classic;

import org.abos.cards.core.Named;

public enum FrenchSuit implements Named {

    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    public static final String SUIT_PROPERTY = "Suit";

    private final String name;

    FrenchSuit() {
        name = Named.fromUpperCase(name());
    }

    @Override
    public String getName() {
        return name;
    }
}
