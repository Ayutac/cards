package org.abos.cards.examples.classic.skat;

import org.abos.cards.examples.classic.FrenchRank;
import org.abos.cards.examples.classic.FrenchSuit;
import org.abos.cards.examples.classic.FrenchType;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class SkatComparator implements Comparator<FrenchType> {

    private static final Map<SkatTrump, SkatComparator> CMPS = new EnumMap<>(SkatTrump.class);

    private final SkatTrump trump;

    public static final Comparator<FrenchType> BY_RANK = (t1, t2) -> {
        if (t1.getRank() == t2.getRank()) {
            return 0;
        }
        if (t1.getRank() == FrenchRank.AS) {
            return 1;
        }
        if (t2.getRank() == FrenchRank.AS) {
            return -1;
        }
        return t1.getRank().ordinal() - t2.getRank().ordinal();
    };

    public static final Comparator<FrenchType> BY_SUIT = (t1, t2) -> {
        if (t1.getSuit() == t2.getSuit()) {
            return 0;
        }
        return switch (t1.getSuit()) {
            case CLUBS -> 1;
            case SPADES -> t2.getSuit() == FrenchSuit.CLUBS ? -1 : 1;
            case HEARTS -> t2.getSuit() == FrenchSuit.DIAMONDS ? 1 : -1;
            case DIAMONDS -> -1;
        };
    };

    public static final Comparator<FrenchType> BY_JACK = (t1, t2) -> {
        if (t1.equals(t2)) {
            return 0;
        }
        if (t1.getRank() == FrenchRank.JACK) {
            if (t2.getRank() == FrenchRank.JACK) {
                return BY_SUIT.compare(t1, t2);
            }
            return 1;
        }
        if (t2.getRank() == FrenchRank.JACK) {
            return -1;
        }
        return 0;
    };

    static {
        for (SkatTrump trump : SkatTrump.values()) {
            CMPS.put(trump, new SkatComparator(trump));
        }
    }

    public static Comparator<FrenchType> byTrump(final SkatTrump trump) {
        return CMPS.get(Objects.requireNonNull(trump));
    }

    private SkatComparator(final SkatTrump trump) {
        this.trump = Objects.requireNonNull(trump);
    }

    private int compareTrumpSuit(FrenchType t1, FrenchType t2, FrenchSuit suit) {
        final int jackCmp = BY_JACK.compare(t1, t2);
        if (jackCmp != 0) {
            return jackCmp;
        }
        if (t1.getSuit() == suit) {
            if (t2.getSuit() == suit) {
                return BY_RANK.compare(t1, t2);
            }
            return 1;
        }
        if (t2.getSuit() == suit) {
            return -1;
        }
        return BY_SUIT.thenComparing(BY_RANK).compare(t1, t2);
    }

    @Override
    public int compare(FrenchType t1, FrenchType t2) {
        if (t1.equals(t2)) {
            return 0;
        }
        return switch(trump) {
            case NULL -> BY_SUIT.thenComparing(BY_RANK).compare(t1, t2);
            case JACK -> BY_JACK.thenComparing(BY_SUIT).thenComparing(BY_RANK).compare(t1, t2);
            case CLUBS -> compareTrumpSuit(t1, t2, FrenchSuit.CLUBS);
            case DIAMONDS -> compareTrumpSuit(t1, t2, FrenchSuit.DIAMONDS);
            case HEARTS -> compareTrumpSuit(t1, t2, FrenchSuit.HEARTS);
            case SPADES -> compareTrumpSuit(t1, t2, FrenchSuit.SPADES);
        };
    }
}
