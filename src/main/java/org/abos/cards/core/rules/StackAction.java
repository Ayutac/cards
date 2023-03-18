package org.abos.cards.core.rules;

import org.abos.cards.core.Board;
import org.abos.cards.core.Card;

import java.util.Objects;

public abstract class StackAction<T extends Card> implements Action<T> {

    protected final Board<T> board;

    protected final String stackName;

    public StackAction(Board<T> board, String stackName) {
        this.board = Objects.requireNonNull(board);
        this.stackName = Objects.requireNonNull(stackName);
        if (!board.hasStack(stackName)) {
            throw new IllegalArgumentException(String.format("Given board doesn't contain %s!", stackName));
        }
    }

    @Override
    public Board<T> getBoard() {
        return board;
    }

    public String getStackName() {
        return stackName;
    }
}
