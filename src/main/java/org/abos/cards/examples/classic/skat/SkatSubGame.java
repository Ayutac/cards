package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.Stack;
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

    protected SkatPlayer startingPlayer;

    protected SkatTrump trump;

    public SkatSubGame(final SkatPlayer dealingPlayer) {
        super(new SkatBoard(new SkatDeck()), List.of(new OneOptionPhase<>(new SkatDealingOption(), true, false)));
        this.dealingPlayer = Objects.requireNonNull(dealingPlayer);
        startingPlayer = dealingPlayer.dealingOrder()[1];
        phases.forEach(phase -> phase.setSubGame(this));
    }

    private static boolean matchTrumpSuit(final FrenchType type, final FrenchType pileType, final FrenchSuit suit) {
        return (type.getSuit() == pileType.getSuit() && pileType.getRank() != FrenchRank.JACK && pileType.getSuit() != suit) ||
                ((pileType.getRank() == FrenchRank.JACK || pileType.getSuit() == suit) && (type.getRank() == FrenchRank.JACK || type.getSuit() == suit));
    }

    public Predicate<FrenchType> getPileMatcher(final FrenchType pileType) {
        if (getTrump() == null) {
            throw new IllegalStateException("No trump selected!");
        }
        return switch (getTrump()) {
            case NULL -> type -> type.getSuit() == pileType.getSuit();
            case JACK -> type -> (type.getSuit() == pileType.getSuit() && pileType.getRank() != FrenchRank.JACK) ||
                        (pileType.getRank() == FrenchRank.JACK && type.getRank() == FrenchRank.JACK);
            case CLUBS -> type -> matchTrumpSuit(type, pileType, FrenchSuit.CLUBS);
            case DIAMONDS -> type -> matchTrumpSuit(type, pileType, FrenchSuit.DIAMONDS);
            case HEARTS -> type -> matchTrumpSuit(type, pileType, FrenchSuit.HEARTS);
            case SPADES -> type -> matchTrumpSuit(type, pileType, FrenchSuit.SPADES);
        };
    }

    private boolean isTrump(FrenchType type) {
        if (getTrump() == null) {
            throw new IllegalStateException("No trump selected!");
        }
        return switch (getTrump()) {
            case NULL -> false;
            case JACK -> type.getRank() == FrenchRank.JACK;
            case CLUBS -> type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.CLUBS;
            case DIAMONDS -> type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.DIAMONDS;
            case HEARTS -> type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.HEARTS;
            case SPADES -> type.getRank() == FrenchRank.JACK || type.getSuit() == FrenchSuit.SPADES;
        };
    }

    public SkatPlayer getPileWinner() {
        final Stack<SimpleCard> pile = getBoard().getStackByName(SkatBoard.PILE_STACK);
        if (pile.size() != 3) {
            throw new IllegalStateException("Pile is not full!");
        }
        if (pile.stream().anyMatch(card -> !(card.getType() instanceof FrenchType))) {
            throw new IllegalStateException("Wrong card in pile detected!");
        }
        final FrenchType type1 = (FrenchType)pile.getFirst().getType();
        final FrenchType type2 = (FrenchType)pile.get(1).getType();
        final FrenchType type3 = (FrenchType)pile.getLast().getType();
        final Predicate<FrenchType> pileMatcher = getPileMatcher(type1);
        FrenchType currentBest = type1;
        int winnerIndex = 0;
        if (pileMatcher.test(type2) || isTrump(type2)) {
            if (SkatComparator.byTrump(getTrump()).compare(currentBest, type2) < 0) {
                currentBest = type2;
                winnerIndex = 1;
            }
        }
        if (pileMatcher.test(type3) || isTrump(type3)) {
            if (SkatComparator.byTrump(getTrump()).compare(currentBest, type3) < 0) {
                winnerIndex = 2;
            }
        }
        return startingPlayer.dealingOrder()[winnerIndex];
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

    public SkatPlayer getStartingPlayer() {
        return startingPlayer;
    }

    public SkatTrump getTrump() {
        return trump;
    }

    public void setTrump(SkatTrump trump) {
        this.trump = trump;
    }
}
