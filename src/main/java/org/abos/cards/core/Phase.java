package org.abos.cards.core;

import java.util.SequencedSet;

public interface Phase<T extends Card> {

    SubGame<T> getSubGame();

    void setSubGame(SubGame<T> subGame);

    boolean isSubGameInitialization();

    boolean isSubGameEnder();

    SequencedSet<Option<T>> getRules();

    default boolean hasAvailableRules() {
        return getRules().stream().anyMatch(option -> option.getCondition().test(getSubGame()));
    }

    Option<T> selectRule();

    void done();

    boolean isDone();

}
