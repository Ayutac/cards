package org.abos.cards.examples.classic;

import org.abos.cards.core.CardType;
import org.abos.cards.core.Named;
import org.abos.cards.core.SimpleCard;

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

    private FrenchType(FrenchSuit suit, FrenchRank rank) {
        this.suit = Objects.requireNonNull(suit);
        this.rank = Objects.requireNonNull(rank);
        this.name = suit.getName() + " " + rank.getName();
    }

    public static FrenchType of(FrenchSuit suit, FrenchRank rank) {
        return cards[suit.ordinal()][rank.ordinal()];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getProperty(String property) {
        if (Named.NAME_PROPERTY.equals(property)) {
            return getName();
        }
        if (FrenchSuit.SUIT_PROPERTY.equals(property)) {
            return suit;
        }
        if (FrenchRank.RANK_PROPERTY.equals(property)) {
            return rank;
        }
        return null; // TODO really?
    }

    @Override
    public SimpleCard create() {
        return new SimpleCard(this);
    }
}
