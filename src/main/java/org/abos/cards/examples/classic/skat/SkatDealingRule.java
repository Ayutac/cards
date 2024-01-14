package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.SimpleCard;
import org.abos.cards.core.SubGame;
import org.abos.cards.core.rules.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class SkatDealingRule implements Rule<SimpleCard> {

    private final SkatSubGame subGame;

    public SkatDealingRule(SkatSubGame subGame) {
        this.subGame = Objects.requireNonNull(subGame);
    }

    @Override
    public SubGame<SimpleCard> getSubGame() {
        return subGame;
    }

    @Override
    public Predicate<SubGame<SimpleCard>> getCondition() {
        return SubGame::isInitialized;
    }

    @Override
    public List<Action<SimpleCard>> getActions() {
        final List<Action<SimpleCard>> actions = new LinkedList<>();
        actions.add(new ShuffleAction<>(subGame, SkatBoard.DECK_STACK));
        final SkatPlayer[] dealingOrder = subGame.getDealingPlayer().dealingOrder();
        for (SkatPlayer player : dealingOrder) {
            actions.add(new MoveAction<>(subGame, SkatBoard.DECK_STACK, SkatBoard.HAND_STACKS.get(player), MoveType.TOP, 3));
        }
        actions.add(new MoveAction<>(subGame, SkatBoard.DECK_STACK, SkatBoard.SKAT_STACK, MoveType.TOP, 2));
        for (SkatPlayer player : dealingOrder) {
            actions.add(new MoveAction<>(subGame, SkatBoard.DECK_STACK, SkatBoard.HAND_STACKS.get(player), MoveType.TOP, 4));
        }
        for (SkatPlayer player : dealingOrder) {
            actions.add(new MoveAction<>(subGame, SkatBoard.DECK_STACK, SkatBoard.HAND_STACKS.get(player), MoveType.TOP, 3));
        }
        return actions;
    }
}
