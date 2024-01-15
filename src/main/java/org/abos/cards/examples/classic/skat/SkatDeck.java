package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.core.simple.SimpleStack;
import org.abos.cards.examples.classic.FrenchRank;
import org.abos.cards.examples.classic.FrenchSuit;
import org.abos.cards.examples.classic.FrenchType;

public class SkatDeck extends SimpleStack<SimpleCard> {

    private static final FrenchRank[] validRanks = new FrenchRank[]
            {FrenchRank.SEVEN, FrenchRank.EIGHT, FrenchRank.NINE, FrenchRank.TEN,
             FrenchRank.JACK, FrenchRank.QUEEN, FrenchRank.KING, FrenchRank.AS};

    public SkatDeck() {
        super(SkatBoard.DECK_STACK);
        for (FrenchSuit suit : FrenchSuit.values()) {
            for (FrenchRank rank : validRanks) {
                add(new SimpleCard(FrenchType.of(suit, rank)));
            }
        }
    }
}
