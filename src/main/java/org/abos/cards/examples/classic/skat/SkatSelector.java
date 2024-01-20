package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.SubGame;
import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.examples.classic.FrenchType;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class SkatSelector implements BiPredicate<SubGame<SimpleCard>, SimpleCard> {

    private final SkatPlayer player;

    public SkatSelector(final SkatPlayer player) {
        this.player = Objects.requireNonNull(player);
    }

    @Override
    public boolean test(SubGame<SimpleCard> subGame, SimpleCard t) {
        if (subGame instanceof SkatSubGame game) {
            final Optional<SimpleCard> pileCard = game.getBoard().firstPlayed();
            if (pileCard.isEmpty()) {
                return true;
            }
            if (pileCard.get().getType() instanceof FrenchType pileType) {
                if (t.getType() instanceof FrenchType chosenType) {
                    final Predicate<FrenchType> pileMatcher = game.getPileMatcher(pileType);
                    if (pileMatcher.test(chosenType)) {
                        return true;
                    }
                    return subGame.getBoard().getStackByName(SkatBoard.HAND_STACKS.get(player)).stream()
                            .filter(card -> card.getType() instanceof FrenchType)
                            .map(card -> (FrenchType)card.getType())
                            .noneMatch(pileMatcher);
                }
            }
        }
        return false;
    }

}
