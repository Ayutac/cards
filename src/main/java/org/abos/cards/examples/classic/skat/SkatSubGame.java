package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.*;

import java.util.Objects;

public class SkatSubGame implements SubGame<SimpleCard> {

    protected final SkatBoard board;

    protected final SkatPlayer dealingPlayer;

    protected boolean initialized = false;

    protected SkatTrump trump;

    public SkatSubGame(SkatPlayer dealingPlayer) {
        this.board = new SkatBoard(new SkatDeck());
        this.dealingPlayer = Objects.requireNonNull(dealingPlayer);
    }

    @Override
    public SkatBoard getBoard() {
        return board;
    }

    public SkatPlayer getDealingPlayer() {
        return dealingPlayer;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    public SkatTrump getTrump() {
        return trump;
    }

    public void setTrump(SkatTrump trump) {
        this.trump = trump;
    }

    @Override
    public void initialize() {
        if (initialized) {
            throw new IllegalStateException("Already initialized!");
        }
        new SkatDealingRule(this).run();
        initialized = true;
    }
}
