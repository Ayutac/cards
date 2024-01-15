package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.Option;
import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.core.SubGame;
import org.abos.cards.core.actions.Action;
import org.abos.cards.core.actions.MoveAction;
import org.abos.cards.core.actions.MoveType;
import org.abos.cards.core.actions.ShuffleAction;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class SkatDealingOption implements Option<SimpleCard> {

    private SkatSubGame subGame;

    public SkatDealingOption() {
        /* Nothing for now */
    }

    @Override
    public SubGame<SimpleCard> getSubGame() {
        return subGame;
    }

    @Override
    public void setSubGame(SubGame<SimpleCard> subGame) {
        if (subGame instanceof SkatSubGame skat) {
            this.subGame = skat;
        }
        else {
            throw new IllegalArgumentException("subGame must be of type SkatSubGame!");
        }
    }

    @Override
    public boolean resetIfActionFailed() {
        return false;
    }

    @Override
    public Predicate<SubGame<SimpleCard>> getCondition() {
        return subGame -> true;
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
