package org.abos.cards.core.simple;

import org.abos.cards.core.Card;
import org.abos.cards.core.Phase;
import org.abos.cards.core.Rule;
import org.abos.cards.core.SubGame;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.SequencedSet;
import java.util.Set;

public abstract class SimplePhase<T extends Card> implements Phase<T> {

    protected SubGame subGame;

    protected final SequencedSet<Rule<T>> rules = new LinkedHashSet<>();

    protected final boolean initializer;

    protected final boolean ender;

    protected SimplePhase(final SequencedSet<Rule<T>> rules, final boolean initializer, final boolean ender) {
        this.rules.addAll(rules);
        if (!(initializer ^ ender)) {
            throw new IllegalArgumentException("A phase can only be initializer OR ender!");
        }
        this.initializer = initializer;
        this.ender = ender;
    }

    protected SimplePhase(final Rule<T> rule, final boolean initializer, final boolean ender) {
        this(new LinkedHashSet<>(Set.of(rule)), initializer, ender);
    }

    @Override
    public SubGame<T> getSubGame() {
        return subGame;
    }

    @Override
    public void setSubGame(SubGame<T> subGame) {
        this.subGame = Objects.requireNonNull(subGame);
        rules.forEach(rule -> rule.setSubGame(subGame));
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
