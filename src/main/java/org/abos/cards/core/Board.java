package org.abos.cards.core;

public interface Board<T extends Card> {

    boolean hasStack(String name);

    Stack<T> getStackByName(String name);

}
