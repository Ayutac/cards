package org.abos.cards.core;

public interface SubGame<T extends Card> {

    Board<T> getBoard();

    int getHistoricBoardsSize();

    Board<T> getHistoricBoard(final int index) throws IndexOutOfBoundsException;

    void historizeBoard();

    boolean isInitialized();

    void initialize();
}
