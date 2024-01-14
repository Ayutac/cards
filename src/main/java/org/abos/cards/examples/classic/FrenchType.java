package org.abos.cards.examples.classic;

import org.abos.cards.core.CardType;

import java.util.Objects;

public final class FrenchType implements CardType {

    private static final FrenchType[][] cards = new FrenchType[FrenchSuit.values().length][FrenchRank.values().length];

    static {
        for (FrenchSuit suit : FrenchSuit.values()) {
            for (FrenchRank rank : FrenchRank.values()) {
                cards[suit.ordinal()][rank.ordinal()] = new FrenchType(suit, rank);
            }
        }
    }

    private final String name;
    private final FrenchSuit suit;
    private final FrenchRank rank;

    private FrenchType(final FrenchSuit suit, final FrenchRank rank) {
        this.suit = Objects.requireNonNull(suit);
        this.rank = Objects.requireNonNull(rank);
        this.name = suit.getName() + " " + rank.getName();
    }

    public static FrenchType of(final FrenchSuit suit, final FrenchRank rank) {
        return cards[suit.ordinal()][rank.ordinal()];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getProperty(final String property) throws IllegalArgumentException {
        return switch (property) {
            case CardType.NAME_PROPERTY -> getName();
            case FrenchSuit.SUIT_PROPERTY -> suit;
            case FrenchRank.RANK_PROPERTY -> rank;
            default -> throw new IllegalArgumentException("Unknown Property!");
        };
    }
}
