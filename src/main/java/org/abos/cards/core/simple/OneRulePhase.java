package org.abos.cards.core.simple;

import org.abos.cards.core.Card;
import org.abos.cards.core.Rule;
import org.abos.cards.core.SubGame;
import org.abos.cards.examples.classic.skat.SkatDealingRule;

public class OneRulePhase<T extends Card> extends SimplePhase<T> {

    boolean done = false;

    public OneRulePhase(Rule<T> rule, boolean initializer, boolean ender) {
        super(rule, initializer, ender);
    }

    @Override
    public Rule<T> selectRule() {
        done();
        return rules.getFirst();
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
