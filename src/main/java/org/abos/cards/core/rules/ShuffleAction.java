package org.abos.cards.core.rules;

import org.abos.cards.core.Card;
import org.abos.cards.core.SubGame;

public class ShuffleAction<T extends Card> extends StackAction<T> {

    public ShuffleAction(SubGame<T> subGame, String stackName) {
        super(subGame, stackName);
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public void run() {
        subGame.getBoard().getStackByName(stackName).shuffle();
    }
}
