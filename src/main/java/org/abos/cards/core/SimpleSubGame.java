package org.abos.cards.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class SimpleSubGame<T extends Card> implements SubGame<T> {

    protected Board<T> board;

    protected final List<Board<T>> historicBoards = new LinkedList<>();

    protected final List<Phase<T>> phases = new ArrayList<>();

    protected SimpleSubGame(final Board<T> board, final List<Phase<T>> phases) {
        this.board = Objects.requireNonNull(board).clone();
        historizeBoard();
        this.phases.addAll(phases);
    }

    @Override
    public int getHistoricBoardsSize() {
        return historicBoards.size();
    }

    @Override
    public Board<T> getHistoricBoard(int index) throws IndexOutOfBoundsException {
        return historicBoards.get(index);
    }

    @Override
    public final void historizeBoard() {
        historicBoards.add(board.clone());
    }

    @Override
    public void resetToLastBoard() {
        board = historicBoards.get(historicBoards.size()-1).clone();
    }
}
