package org.abos.cards.core;

public interface Game<T extends Card> {

    int getSubGameSize();

    SubGame<T> getSubGame(final int index) throws IndexOutOfBoundsException;

}
