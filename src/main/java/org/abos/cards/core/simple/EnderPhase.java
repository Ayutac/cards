package org.abos.cards.core.simple;

import org.abos.cards.core.Card;
import org.abos.cards.core.Option;
import org.abos.cards.core.SubGame;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.function.Predicate;

public class EnderPhase<T extends Card> extends SimplePhase<T> {

    private final Predicate<SubGame<T>> condition;

    public EnderPhase(final Predicate<SubGame<T>> condition) {
        super(new LinkedHashSet<>(), false, true);
        this.condition = Objects.requireNonNull(condition);
    }

    @Override
    public boolean hasAvailableOptions() {
        return condition.test(getSubGame());
    }

    @Override
    public Option<T> selectOption() {
        return null;
    }

    @Override
    public void done() {
        /* Nothing happens. */
    }

    @Override
    public boolean isDone() {
        return true;
    }
}
