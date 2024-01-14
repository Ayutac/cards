package org.abos.cards.core;

import java.util.LinkedList;
import java.util.Objects;

public class SimpleStack<T extends Card> extends LinkedList<T> implements Stack<T> {

    private final String name;

    public SimpleStack(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
