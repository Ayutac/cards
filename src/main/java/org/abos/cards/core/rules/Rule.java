package org.abos.cards.core.rules;

import org.abos.cards.core.Card;
import org.abos.cards.core.SubGame;

import java.util.List;
import java.util.function.Predicate;

public interface Rule<T extends Card> extends Runnable {

    SubGame<T> getSubGame();

    Predicate<SubGame<T>> getCondition();

    /**
     * Returns an immutable view of the actions to take.
     * @return
     */
    List<Action<T>> getActions();

    @Override
    default void run() {
        if (getCondition().test(getSubGame())) {
            for (Action<T> action : getActions()) {
                if (action.isPossible()) {
                    action.run();
                }
            }
        }
    }

}
