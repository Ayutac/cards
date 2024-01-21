package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.examples.classic.FrenchRank;
import org.abos.cards.examples.classic.FrenchSuit;
import org.abos.cards.examples.classic.FrenchType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link SkatComparator}.
 */
public final class TestSkatComparator {

    /**
     * Tests the different constant comparators of {@link SkatComparator}.
     */
    @Test
    public void testConstantComparators() {
        final FrenchType cj = FrenchType.of(FrenchSuit.CLUBS, FrenchRank.JACK);
        final FrenchType dj = FrenchType.of(FrenchSuit.DIAMONDS, FrenchRank.JACK);
        final FrenchType h1 = FrenchType.of(FrenchSuit.HEARTS, FrenchRank.AS);
        final FrenchType s10 = FrenchType.of(FrenchSuit.SPADES, FrenchRank.TEN);
        Assertions.assertTrue(SkatComparator.BY_RANK.compare(cj,dj) == 0);
        Assertions.assertTrue(SkatComparator.BY_RANK.compare(cj,h1) < 0);
        Assertions.assertTrue(SkatComparator.BY_RANK.compare(cj,s10) > 0);
        Assertions.assertTrue(SkatComparator.BY_RANK.compare(dj,h1) < 0);
        Assertions.assertTrue(SkatComparator.BY_RANK.compare(dj,s10) > 0);
        Assertions.assertTrue(SkatComparator.BY_RANK.compare(h1,s10) > 0);
        Assertions.assertTrue(SkatComparator.BY_SUIT.compare(cj,dj) > 0);
        Assertions.assertTrue(SkatComparator.BY_SUIT.compare(cj,h1) > 0);
        Assertions.assertTrue(SkatComparator.BY_SUIT.compare(cj,s10) > 0);
        Assertions.assertTrue(SkatComparator.BY_SUIT.compare(dj,h1) < 0);
        Assertions.assertTrue(SkatComparator.BY_SUIT.compare(dj,s10) < 0);
        Assertions.assertTrue(SkatComparator.BY_SUIT.compare(h1,s10) < 0);
        Assertions.assertTrue(SkatComparator.BY_JACK.compare(cj,dj) > 0);
        Assertions.assertTrue(SkatComparator.BY_JACK.compare(cj,h1) > 0);
        Assertions.assertTrue(SkatComparator.BY_JACK.compare(cj,s10) > 0);
        Assertions.assertTrue(SkatComparator.BY_JACK.compare(dj,h1) > 0);
        Assertions.assertTrue(SkatComparator.BY_JACK.compare(dj,s10) > 0);
        Assertions.assertTrue(SkatComparator.BY_JACK.compare(h1,s10) == 0);
    }

}
