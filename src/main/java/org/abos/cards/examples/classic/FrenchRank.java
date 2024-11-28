package org.abos.cards.examples.classic;

import org.abos.common.Named;
import org.abos.common.StringUtil;

public enum FrenchRank implements Named {

    ACE,
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
        name = StringUtil.toCapitalized(name());
    }

    @Override
    public String getName() {
        return name;
    }
}
