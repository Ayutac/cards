package org.abos.cards.examples.classic.skat;

import org.abos.cards.core.SubGame;
import org.abos.cards.core.simple.SimpleCard;
import org.abos.cards.examples.classic.FrenchRank;
import org.abos.cards.examples.classic.FrenchSuit;
import org.abos.cards.examples.classic.FrenchType;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class SkatSelector implements BiPredicate<SubGame<SimpleCard>, SimpleCard> {

    private final SkatPlayer player;

    public SkatSelector(final SkatPlayer player) {
        this.player = Objects.requireNonNull(player);
    }

    @Override
    public boolean test(SubGame<SimpleCard> subGame, SimpleCard t) {
        if (subGame instanceof SkatSubGame game) {
            final Optional<SimpleCard> pileCard = game.getBoard().firstPlayed();
            if (pileCard.isEmpty()) {
                return true;
            }
            if (pileCard.get().getType() instanceof FrenchType pileType) {
                if (t.getType() instanceof FrenchType chosenType) {
                    final Predicate<FrenchType> handMatcher = getHandMatcher(game.getTrump(), pileType);
                    if (handMatcher.test(chosenType)) {
                        return true;
                    }
                    return subGame.getBoard().getStackByName(SkatBoard.HAND_STACKS.get(player)).stream()
                            .filter(card -> card.getType() instanceof FrenchType)
                            .map(card -> (FrenchType)card.getType())
                            .noneMatch(handMatcher);
                }
            }
        }
        return false;
    }

    private static Predicate<FrenchType> getHandMatcher(final SkatTrump trump, final FrenchType pileType) {
        if (trump == null) {
            throw new IllegalStateException("No trump selected!");
        }
        return switch (trump) {
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
}
