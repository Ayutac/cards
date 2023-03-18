package org.abos.cards.core.rules;

import org.abos.cards.core.Board;
import org.abos.cards.core.Card;

public class ShuffleAction<T extends Card> extends StackAction<T> {

    public ShuffleAction(Board<T> board, String stackName) {
        super(board, stackName);
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public void run() {
        board.getStackByName(stackName).shuffle();
    }
}
