package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.simple.OneOptionPhase;
import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.core.simple.SimpleSubGame;
import org.abos.cards.examples.classic.FrenchRank;
import org.abos.cards.examples.classic.FrenchSuit;
import org.abos.cards.examples.classic.FrenchType;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class SkatSubGame extends SimpleSubGame<SimpleCard> {

    protected final SkatPlayer dealingPlayer;

    protected SkatTrump trump;

    public SkatSubGame(final SkatPlayer dealingPlayer) {
        super(new SkatBoard(new SkatDeck()), List.of(new OneOptionPhase<>(new SkatDealingOption(), true, false)));
        this.dealingPlayer = Objects.requireNonNull(dealingPlayer);
        phases.forEach(phase -> phase.setSubGame(this));
    }

    public Predicate<FrenchType> getPileMatcher(final FrenchType pileType) {
        if (getTrump() == null) {
            throw new IllegalStateException("No trump selected!");
        }
        return switch (getTrump()) {
            case NULL -> type -> type.getSuit() == pileType.getSuit();
            case JACK -> type -> (type.getSuit() == pileType.getSuit() && pileType.getRank() != FrenchRank.JACK) ||
                        (pileType.getRank() == FrenchRank.JACK && type.getRank() == FrenchRank.JACK);
            case CLUBS -> type -> (type.getSuit() == pileType.getSuit() && pileType.getRank() != FrenchRank.JACK && pileType.getSuit() != FrenchSuit.CLUBS) ||
                    ((pileType.getRank() == FrenchRank.JACK || pileType.getSuit() == FrenchSuit.CLUBS) && (type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.CLUBS));
            case DIAMONDS -> type -> (type.getSuit() == pileType.getSuit() && pileType.getRank() != FrenchRank.JACK && pileType.getSuit() != FrenchSuit.DIAMONDS) ||
                    ((pileType.getRank() == FrenchRank.JACK || pileType.getSuit() == FrenchSuit.DIAMONDS) && (type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.DIAMONDS));
            case HEARTS -> type -> (type.getSuit() == pileType.getSuit() && pileType.getRank() != FrenchRank.JACK && pileType.getSuit() != FrenchSuit.HEARTS) ||
                    ((pileType.getRank() == FrenchRank.JACK || pileType.getSuit() == FrenchSuit.HEARTS) && (type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.HEARTS));
            case SPADES -> type -> (type.getSuit() == pileType.getSuit() && pileType.getRank() != FrenchRank.JACK && pileType.getSuit() != FrenchSuit.SPADES) ||
                    ((pileType.getRank() == FrenchRank.JACK || pileType.getSuit() == FrenchSuit.SPADES) && (type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.SPADES));
        };
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
