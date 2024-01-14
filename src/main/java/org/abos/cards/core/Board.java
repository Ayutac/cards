package org.abos.cards.core;

public interface Board<T extends Card> extends Cloneable {

    boolean hasStack(final String name);

    Stack<T> getStackByName(final String name) throws IllegalArgumentException;

    Board<T> clone();

}
