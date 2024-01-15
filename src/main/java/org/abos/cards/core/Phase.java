package org.abos.cards.core;

import java.util.SequencedSet;

public interface Phase<T extends Card> {

    SubGame<T> getSubGame();

    void setSubGame(SubGame<T> subGame);

    boolean isSubGameInitialization();

    boolean isSubGameEnder();

    SequencedSet<Rule<T>> getRules();

    default boolean hasAvailableRules() {
        return getRules().stream().anyMatch(rule -> rule.getCondition().test(getSubGame()));
    }

    Rule<T> selectRule();

    void done();

    boolean isDone();

}
