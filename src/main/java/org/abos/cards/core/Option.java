package org.abos.cards.core;

import org.abos.cards.core.actions.Action;

import java.util.List;
import java.util.function.Predicate;

public interface Option<T extends Card> extends Runnable {

    SubGame<T> getSubGame();

    void setSubGame(SubGame<T> subGame);

    boolean resetIfActionFailed();

    Predicate<SubGame<T>> getCondition();

    /**
     * Returns an immutable view of the actions to take.
     * @return
     */
    List<Action<T>> getActions();

    @Override
    default void run() {
        boolean allActionsDone = true;
        if (getCondition().test(getSubGame())) {
            for (Action<T> action : getActions()) {
                if (action.isPossible()) {
                    action.run();
                }
                else {
                    allActionsDone = false;
                }
            }
        }
        if (!allActionsDone && resetIfActionFailed()) {
            getSubGame().resetToLastBoard();
        }
    }

}
