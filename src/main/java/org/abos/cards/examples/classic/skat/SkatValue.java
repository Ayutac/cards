package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.Card;
import org.abos.cards.core.Stack;
import org.abos.cards.examples.classic.FrenchType;

import java.util.function.ToIntFunction;

public final class SkatValue implements ToIntFunction<Card> {

    public static SkatValue INSTANCE = new SkatValue();

    private SkatValue() {
        /* Singleton. */
    }

    @Override
    public int applyAsInt(Card value) {
        if (value.getType() instanceof FrenchType type) {
            return switch (type.getRank()) {
                case ACE -> 11;
                case TEN -> 10;
                case KING -> 4;
                case QUEEN -> 3;
                case JACK -> 2;
                default -> 0;
            };
        }
        return 0;
    }

    public int of(final Stack<? extends Card> stack) {
        return stack.stream().mapToInt(INSTANCE).sum();
    }

}
