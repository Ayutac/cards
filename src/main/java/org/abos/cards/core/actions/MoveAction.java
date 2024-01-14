package org.abos.cards.core.actions;

import org.abos.cards.core.Card;
import org.abos.cards.core.Stack;
import org.abos.cards.core.SubGame;
import org.abos.common.ErrorUtil;

import java.util.Objects;
import java.util.Random;

public class MoveAction<T extends Card> implements Action<T> {

    protected final SubGame<T> subGame;
    protected final String stackFromName;
    protected final String stackToName;
    protected final MoveType moveType;
    protected final int count;

    public MoveAction(SubGame<T> game, String stackFromName, String stackToName, MoveType moveType, int count) {
        this.subGame = Objects.requireNonNull(game);
        this.stackFromName = Objects.requireNonNull(stackFromName);
        this.stackToName = Objects.requireNonNull(stackToName);
        this.moveType = Objects.requireNonNull(moveType);
        this.count = count;
        if (!game.getBoard().hasStack(stackFromName)) {
            throw new IllegalArgumentException(String.format("Given board doesn't contain %s!", stackFromName));
        }
        if (!game.getBoard().hasStack(stackToName)) {
            throw new IllegalArgumentException(String.format("Given board doesn't contain %s!", stackToName));
        }
    }

    @Override
    public boolean isPossible() {
        return subGame.getBoard().getStackByName(stackFromName).size() >= count;
    }

    @Override
    public void run() {
        Stack<T> from = subGame.getBoard().getStackByName(stackFromName);
        Stack<T> to = subGame.getBoard().getStackByName(stackToName);
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
            default -> ErrorUtil.unknownEnumEntry(moveType);
        }
    }

    @Override
    public SubGame<T> getSubGame() {
        return subGame;
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
