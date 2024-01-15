package org.abos.cards.core.simple;

import org.abos.cards.core.Card;
import org.abos.cards.core.Stack;

import java.util.LinkedList;
import java.util.Objects;

public class SimpleStack<T extends Card> extends LinkedList<T> implements Stack<T> {

    private final String name;

    public SimpleStack(final String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
