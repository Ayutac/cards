package org.abos.cards.core.rules;

import org.abos.cards.core.Board;
import org.abos.cards.core.Card;
import org.abos.cards.core.Stack;

import java.util.Objects;
import java.util.Random;

public class MoveAction<T extends Card> implements Action<T> {

    protected final Board<T> board;
    protected final String stackFromName;
    protected final String stackToName;
    protected final MoveType moveType;
    protected final int count;

    public MoveAction(Board<T> board, String stackFromName, String stackToName, MoveType moveType, int count) {
        this.board = Objects.requireNonNull(board);
        this.stackFromName = Objects.requireNonNull(stackFromName);
        this.stackToName = Objects.requireNonNull(stackToName);
        this.moveType = Objects.requireNonNull(moveType);
        this.count = count;
        if (!board.hasStack(stackFromName)) {
            throw new IllegalArgumentException(String.format("Given board doesn't contain %s!", stackFromName));
        }
        if (!board.hasStack(stackToName)) {
            throw new IllegalArgumentException(String.format("Given board doesn't contain %s!", stackToName));
        }
    }

    @Override
    public boolean isPossible() {
        return board.getStackByName(stackFromName).size() >= count;
    }

    @Override
    public void run() {
        Stack<T> from = board.getStackByName(stackFromName);
        Stack<T> to = board.getStackByName(stackToName);
        switch (moveType) {
            case TOP -> {
                for (int i = 0; i < count; i++) {
                    to.add(from.remove(0));
                }
            }
            case BOTTOM -> {
                for (int i = 0; i < count; i++) {
                    to.add(from.remove(from.size()-1));
                }
            }
            case RANDOM -> {
                Random rng = new Random();
                for (int i = 0; i < count; i++) {
                    to.add(from.remove(rng.nextInt(from.size())));
                }
            }
            default -> throw new AssertionError(String.format("Unknown %s enum entry!", MoveType.class.getName()));
        }
    }

    @Override
    public Board getBoard() {
        return board;
    }

    public String getStackFromName() {
        return stackFromName;
    }

    public String getStackToName() {
        return stackToName;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public int getCount() {
        return count;
    }
}
