package org.abos.cards.core.simple;

import org.abos.cards.core.Card;
import org.abos.cards.core.Phase;
import org.abos.cards.core.Option;
import org.abos.cards.core.SubGame;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.SequencedSet;
import java.util.Set;

public abstract class SimplePhase<T extends Card> implements Phase<T> {

    protected SubGame<T> subGame;

    protected final SequencedSet<Option<T>> options = new LinkedHashSet<>();

    protected final boolean initializer;

    protected final boolean ender;

    protected SimplePhase(final SequencedSet<Option<T>> options, final boolean initializer, final boolean ender) {
        this.options.addAll(options);
        if (initializer && ender) {
            throw new IllegalArgumentException("A phase can only be initializer OR ender!");
        }
        this.initializer = initializer;
        this.ender = ender;
    }

    protected SimplePhase(final Option<T> option, final boolean initializer, final boolean ender) {
        this(new LinkedHashSet<>(Set.of(option)), initializer, ender);
    }

    @Override
    public SubGame<T> getSubGame() {
        return subGame;
    }

    @Override
    public void setSubGame(SubGame<T> subGame) {
        this.subGame = Objects.requireNonNull(subGame);
        options.forEach(option -> option.setSubGame(subGame));
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
    public SequencedSet<Option<T>> getRules() {
        return Collections.unmodifiableSequencedSet(options);
    }

    @Override
    public String toString() {
        return "Phase: " + options;
    }
}
