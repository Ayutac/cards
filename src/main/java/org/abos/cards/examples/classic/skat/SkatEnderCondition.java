package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.SubGame;
import org.abos.cards.core.simple.SimpleCard;

import java.util.function.Predicate;

public final class SkatEnderCondition implements Predicate<SubGame<SimpleCard>> {

    public static final SkatEnderCondition INSTANCE = new SkatEnderCondition();

    private SkatEnderCondition() {
        /* Singleton. */
    }

    @Override
    public boolean test(final SubGame<SimpleCard> subGame) {
        for (SkatPlayer player : SkatPlayer.values()) {
            if (!subGame.getBoard().getStackByName(SkatBoard.HAND_STACKS.get(player)).isEmpty()) {
                return false;
            }
        }
        return !subGame.getBoard().getStackByName(SkatBoard.PILE_STACK).isEmpty();
    }
}
