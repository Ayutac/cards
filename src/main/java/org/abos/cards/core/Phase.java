package org.abos.cards.core;

import java.util.SequencedSet;

public interface Phase<T extends Card> {

    SubGame<T> getSubGame();

    boolean isSubGameInitialization();

    boolean isSubGameEnder();

    SequencedSet<Rule<T>> getRules();

}
