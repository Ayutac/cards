package org.abos.cards.core;

public interface Game<T extends Card> {

    Board<T> getBoard();

}
