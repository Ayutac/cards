package org.abos.cards.core;

import java.util.List;

public interface SubGame<T extends Card> extends Runnable {

    Board<T> getBoard();

    int getHistoricBoardsSize();

    Board<T> getHistoricBoard(final int index) throws IndexOutOfBoundsException;

    void historizeBoard();

    void resetToLastBoard();

    boolean isInitialized();

    void initialize();

    int getPhasesSize();

    Phase<T> getPhase(final int index) throws IndexOutOfBoundsException;
}
