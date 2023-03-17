package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.Board;
import org.abos.cards.core.SimpleCard;
import org.abos.cards.core.SimpleStack;
import org.abos.cards.core.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SkatBoard implements Board {

    public static final String DECK_STACK = "deck";

    public static final String SKAT_STACK = "skat";

    public static final String PILE_STACK = "pile";

    public static final String PLAYER_1_HAND_STACK = "player1hand";

    public static final String PLAYER_2_HAND_STACK = "player2hand";

    public static final String PLAYER_3_HAND_STACK = "player3hand";

    public static final String PLAYER_1_PILE_STACK = "player1pile";

    public static final String PLAYER_2_PILE_STACK = "player2pile";

    public static final String PLAYER_3_PILE_STACK = "player3pile";

    private final Map<String, Stack<SimpleCard>> stacks = new HashMap<>();

    public SkatBoard(SkatDeck deck) {
        stacks.put(DECK_STACK, Objects.requireNonNull(deck));
        stacks.put(SKAT_STACK, new SimpleStack<>(SKAT_STACK));
        stacks.put(PILE_STACK, new SimpleStack<>(PILE_STACK));
        stacks.put(PLAYER_1_HAND_STACK, new SimpleStack<>(PLAYER_1_HAND_STACK));
        stacks.put(PLAYER_2_HAND_STACK, new SimpleStack<>(PLAYER_2_HAND_STACK));
        stacks.put(PLAYER_3_HAND_STACK, new SimpleStack<>(PLAYER_3_HAND_STACK));
        stacks.put(PLAYER_1_PILE_STACK, new SimpleStack<>(PLAYER_1_PILE_STACK));
        stacks.put(PLAYER_2_PILE_STACK, new SimpleStack<>(PLAYER_2_PILE_STACK));
        stacks.put(PLAYER_3_PILE_STACK, new SimpleStack<>(PLAYER_3_PILE_STACK));
    }

    @Override
    public Stack getByName(String name) {
        return stacks.get(name);
    }
}
