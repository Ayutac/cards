package org.abos.cards.examples.classic;

import org.abos.cards.core.Named;

public enum FrenchRank implements Named {

    AS,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    public static final String RANK_PROPERTY = "Rank";

    private final String name;

    FrenchRank() {
        name = Named.fromUpperCase(name());
    }

    @Override
    public String getName() {
        return name;
    }
}
