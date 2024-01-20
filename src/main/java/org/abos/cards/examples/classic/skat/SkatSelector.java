package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.Card;
import org.abos.cards.core.SubGame;
import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.core.simple.SimpleSubGame;
import org.abos.cards.examples.classic.FrenchType;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public final class SkatSelector implements BiPredicate<SubGame<SimpleCard>, SimpleCard> {

    private final SkatPlayer player;

    public SkatSelector(final SkatPlayer player) {
        this.player = Objects.requireNonNull(player);
    }

    @Override
    public boolean test(SubGame<SimpleCard> subGame, SimpleCard t) {
        if (subGame.getBoard() instanceof SkatBoard board) {
            final Optional<SimpleCard> pileCard = board.firstPlayed();
            if (pileCard.isEmpty()) {
                return true;
            }
            if (pileCard.get().getType() instanceof FrenchType pileType) {
                if (t.getType() instanceof FrenchType chosenType) {
                    if (pileType.getSuit() == chosenType.getSuit()) {
                        return true;
                    }
                    else {
                        return !subGame.getBoard().getStackByName(SkatBoard.HAND_STACKS.get(player)).stream()
                                .filter(card -> card.getType() instanceof FrenchType)
                                .map(card -> ((FrenchType)card.getType()).getSuit())
                                .anyMatch(suit -> suit == pileType.getSuit());
                    }
                }
            }
        }
        return false;
    }
}
