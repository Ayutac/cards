package org.abos.cards.core;

import java.util.SequencedSet;

public abstract class SimplePhase<T extends Card> implements Phase<T> {



    @Override
    public SubGame<T> getSubGame() {
        return null;
    }

    @Override
    public boolean isSubGameInitialization() {
        return false;
    }

    @Override
    public boolean isSubGameEnder() {
        return false;
    }

    @Override
    public SequencedSet<Rule<T>> getRules() {
        return null;
    }
}
