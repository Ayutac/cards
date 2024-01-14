package org.abos.cards.examples.classic;

import org.abos.common.Named;
import org.abos.common.StringUtil;

public enum FrenchSuit implements Named {

    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    public static final String SUIT_PROPERTY = "Suit";

    private final String name;

    FrenchSuit() {
        name = StringUtil.toCapitalized(name());
    }

    @Override
    public String getName() {
        return name;
    }
}
