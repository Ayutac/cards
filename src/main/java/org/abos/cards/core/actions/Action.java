package org.abos.cards.core.actions;

import org.abos.cards.core.Card;
import org.abos.cards.core.SubGame;

public interface Action<T extends Card> extends Runnable {

    SubGame<T> getSubGame();

    boolean isPossible();

}
