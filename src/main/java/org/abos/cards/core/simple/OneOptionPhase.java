package org.abos.cards.core.simple;

import org.abos.cards.core.Card;
import org.abos.cards.core.Option;

public class OneOptionPhase<T extends Card> extends SimplePhase<T> {

    boolean done = false;

    public OneOptionPhase(Option<T> option, boolean initializer, boolean ender) {
        super(option, initializer, ender);
    }

    @Override
    public Option<T> selectOption() {
        done();
        return options.getFirst();
    }

    @Override
    public void done() {
        done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
