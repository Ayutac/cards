package org.abos.cards.core;

import java.util.SequencedSet;

public interface Phase<T extends Card> {

    SubGame<T> getSubGame();

    void setSubGame(SubGame<T> subGame);

    boolean isSubGameInitialization();

    boolean isSubGameEnder();

    SequencedSet<Option<T>> getOptions();

    default boolean hasAvailableOptions() {
        return getOptions().stream().anyMatch(option -> option.getCondition().test(getSubGame()));
    }

    Option<T> selectRule();

    void done();

    boolean isDone();

}
