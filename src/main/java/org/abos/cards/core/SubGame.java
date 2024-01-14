package org.abos.cards.core;

public interface SubGame<T extends Card> {

    Board<T> getBoard();

    boolean isInitialized();

    void initialize();
}
