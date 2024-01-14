package org.abos.cards.core;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.SequencedSet;

public abstract class SimplePhase<T extends Card> implements Phase<T> {

    protected final SubGame subGame;

    protected final SequencedSet<Rule<T>> rules = new LinkedHashSet<>();

    protected final boolean initializer;

    protected final boolean ender;

    protected SimplePhase(final SubGame subGame, final SequencedSet<Rule<T>> rules, final boolean initializer, final boolean ender) {
        this.subGame = Objects.requireNonNull(subGame);
        this.rules.addAll(rules);
        if (!(initializer ^ ender)) {
            throw new IllegalArgumentException("A phase can only be initializer OR ender!");
        }
        this.initializer = initializer;
        this.ender = ender;
    }

    @Override
    public SubGame<T> getSubGame() {
        return subGame;
    }

    @Override
    public boolean isSubGameInitialization() {
        return initializer;
    }

    @Override
    public boolean isSubGameEnder() {
        return ender;
    }

    @Override
    public SequencedSet<Rule<T>> getRules() {
        return Collections.unmodifiableSequencedSet(rules);
    }
}
