package org.abos.cards.core.simple;

import org.abos.cards.core.Board;
import org.abos.cards.core.Card;
import org.abos.cards.core.Phase;
import org.abos.cards.core.SubGame;

import java.util.ArrayList;
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
        board = historicBoards.getLast().clone();
    }

    @Override
    public int getPhasesSize() {
        return phases.size();
    }

    @Override
    public Phase<T> getPhase(int index) throws IndexOutOfBoundsException {
        return phases.get(index);
    }

    @Override
    public void run() {
        boolean initialized = false;
        boolean ended = false;
        do {
            for (Phase<T> phase : phases) {
                if (phase.isSubGameInitialization() && initialized || !phase.hasAvailableOptions()) {
                    continue;
                }
                while (!phase.isDone()) {
                    phase.selectRule().run();
                }
                initialized = true;
                if (phase.isSubGameEnder()) {
                    ended = true;
                }
            }
        } while(!ended);
    }
}
