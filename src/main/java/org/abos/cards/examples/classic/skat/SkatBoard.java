package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.Board;
import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.core.simple.SimpleStack;
import org.abos.cards.core.Stack;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SkatBoard implements Board<SimpleCard> {

    public static final String DECK_STACK = "deck";

    public static final String SKAT_STACK = "skat";

    public static final String PILE_STACK = "pile";

    public static final EnumMap<SkatPlayer, String> HAND_STACKS = new EnumMap<>(SkatPlayer.class);

    public static final EnumMap<SkatPlayer, String> PILE_STACKS = new EnumMap<>(SkatPlayer.class);

    static {
        for (SkatPlayer player : SkatPlayer.values()) {
            HAND_STACKS.put(player, "hand." + player.name());
            PILE_STACKS.put(player, "pile." + player.name());
        }
    }

    private final Map<String, Stack<SimpleCard>> stacks = new HashMap<>();

    public SkatBoard(final SkatDeck deck) {
        stacks.put(DECK_STACK, Objects.requireNonNull(deck));
        stacks.put(SKAT_STACK, new SimpleStack<>(SKAT_STACK));
        stacks.put(PILE_STACK, new SimpleStack<>(PILE_STACK));
        for (SkatPlayer player: SkatPlayer.values()) {
            stacks.put(HAND_STACKS.get(player), new SimpleStack<>(HAND_STACKS.get(player)));
            stacks.put(PILE_STACKS.get(player), new SimpleStack<>(PILE_STACKS.get(player)));
        }
    }

    protected SkatBoard() {
        this(new SkatDeck());
    }

    @Override
    public boolean hasStack(final String name) {
        return stacks.containsKey(name);
    }

    @Override
    public Stack<SimpleCard> getStackByName(final String name) throws IllegalArgumentException {
        if (!hasStack(name)) {
            throw new IllegalArgumentException("Unknown stack!");
        }
        return stacks.get(name);
    }

    /**
     * Returns the first played card on the pile if there is any.
     * @return the first played card on the pile, not {@code null}
     */
    public Optional<SimpleCard> firstPlayed() {
        final Stack<SimpleCard> pile = stacks.get(PILE_STACK);
        if (pile.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(pile.getFirst());
    }

    @Override
    public SkatBoard clone() {
        final SkatBoard clone = new SkatBoard();
        clone.stacks.put(DECK_STACK, stacks.get(DECK_STACK));
        clone.stacks.put(SKAT_STACK, stacks.get(SKAT_STACK));
        clone.stacks.put(PILE_STACK, stacks.get(PILE_STACK));
        for (SkatPlayer player: SkatPlayer.values()) {
            clone.stacks.put(HAND_STACKS.get(player), stacks.get(HAND_STACKS.get(player)));
            clone.stacks.put(PILE_STACKS.get(player), stacks.get(PILE_STACKS.get(player)));
        }
        return clone;
    }

    @Override
    public String toString() {
        return "Board: " + stacks;
    }
}
