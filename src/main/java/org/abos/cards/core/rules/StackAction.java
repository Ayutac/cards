package org.abos.cards.core.rules;

import org.abos.cards.core.Card;
import org.abos.cards.core.SubGame;

import java.util.Objects;

public abstract class StackAction<T extends Card> implements Action<T> {

    protected final SubGame<T> subGame;

    protected final String stackName;

    public StackAction(SubGame<T> subGame, String stackName) {
        this.subGame = Objects.requireNonNull(subGame);
        this.stackName = Objects.requireNonNull(stackName);
        if (!subGame.getBoard().hasStack(stackName)) {
            throw new IllegalArgumentException(String.format("Given board doesn't contain %s!", stackName));
        }
    }

    @Override
    public SubGame<T> getSubGame() {
        return subGame;
    }

    public String getStackName() {
        return stackName;
    }
}
