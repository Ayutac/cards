package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.simple.OneRulePhase;
import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.core.simple.SimpleSubGame;

import java.util.List;
import java.util.Objects;

public class SkatSubGame extends SimpleSubGame<SimpleCard> {

    protected final SkatPlayer dealingPlayer;

    protected SkatTrump trump;

    public SkatSubGame(final SkatPlayer dealingPlayer) {
        super(new SkatBoard(new SkatDeck()), List.of(new OneRulePhase<>(new SkatDealingRule(), true, false)));
        this.dealingPlayer = Objects.requireNonNull(dealingPlayer);
        phases.forEach(phase -> phase.setSubGame(this));
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

    public SkatTrump getTrump() {
        return trump;
    }

    public void setTrump(SkatTrump trump) {
        this.trump = trump;
    }
}
