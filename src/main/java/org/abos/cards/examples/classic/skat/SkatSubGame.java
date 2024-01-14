package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.*;

import java.util.Objects;

public class SkatSubGame extends SimpleSubGame<SimpleCard> {

    protected final SkatPlayer dealingPlayer;

    protected boolean initialized = false;

    protected SkatTrump trump;

    public SkatSubGame(final SkatPlayer dealingPlayer) {
        super(new SkatBoard(new SkatDeck()));
        this.dealingPlayer = Objects.requireNonNull(dealingPlayer);
    }

    @Override
    public SkatBoard getBoard() {
        return (SkatBoard)board;
    }

    @Override
    public SkatBoard getHistoricBoard(int index) throws IndexOutOfBoundsException {
        return (SkatBoard)super.getHistoricBoard(index);
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
