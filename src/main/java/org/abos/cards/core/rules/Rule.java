package org.abos.cards.core.rules;

import org.abos.cards.core.Card;
import org.abos.cards.core.Game;

import java.util.List;
import java.util.function.Predicate;

public interface Rule<T extends Card> extends Runnable {

    Game<T> getGame();

    Predicate<Game<T>> getCondition();

    /**
     * Returns an immutable view of the actions to take.
     * @return
     */
    List<Action<T>> getActions();

    @Override
    default void run() {
        if (getCondition().test(getGame())) {
            for (Action<T> action : getActions()) {
                if (action.isPossible()) {
                    action.run();
                }
            }
        }
    }

}
