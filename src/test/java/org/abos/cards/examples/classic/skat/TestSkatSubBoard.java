package org.abos.cards.examples.classic.skat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link SkatSubGame}.
 */
public class TestSkatSubBoard {

    @Test
    public void testDealing() {
        var subGame = new SkatSubGame(SkatPlayer.ONE);
        Assertions.assertEquals(32, subGame.getBoard().getStackByName(SkatBoard.DECK_STACK).size());
        Assertions.assertEquals(0, subGame.getBoard().getStackByName(SkatBoard.PILE_STACK).size());
        subGame.getPhase(0).selectRule().run(); // will deal the cards
        Assertions.assertEquals(0, subGame.getBoard().getStackByName(SkatBoard.DECK_STACK).size());
        Assertions.assertEquals(2, subGame.getBoard().getStackByName(SkatBoard.SKAT_STACK).size());
        Assertions.assertEquals(10, subGame.getBoard().getStackByName(SkatBoard.HAND_STACKS.get(SkatPlayer.ONE)).size());
        Assertions.assertEquals(10, subGame.getBoard().getStackByName(SkatBoard.HAND_STACKS.get(SkatPlayer.TWO)).size());
        Assertions.assertEquals(10, subGame.getBoard().getStackByName(SkatBoard.HAND_STACKS.get(SkatPlayer.THREE)).size());
    }

}
