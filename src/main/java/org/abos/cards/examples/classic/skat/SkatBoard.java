package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.Board;
import org.abos.cards.core.SimpleCard;
import org.abos.cards.core.SimpleStack;
import org.abos.cards.core.Stack;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public SkatBoard(SkatDeck deck) {
        stacks.put(DECK_STACK, Objects.requireNonNull(deck));
        stacks.put(SKAT_STACK, new SimpleStack<>(SKAT_STACK));
        stacks.put(PILE_STACK, new SimpleStack<>(PILE_STACK));
        for (SkatPlayer player: SkatPlayer.values()) {
            stacks.put(HAND_STACKS.get(player), new SimpleStack<>(HAND_STACKS.get(player)));
            stacks.put(PILE_STACKS.get(player), new SimpleStack<>(PILE_STACKS.get(player)));
        }
    }

    @Override
    public boolean hasStack(String name) {
        return stacks.containsKey(name);
    }

    @Override
    public Stack<SimpleCard> getStackByName(String name) {
        return stacks.get(name);
    }
}
