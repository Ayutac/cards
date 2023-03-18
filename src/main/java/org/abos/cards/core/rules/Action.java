package org.abos.cards.core.rules;

import org.abos.cards.core.Board;
import org.abos.cards.core.Card;

public interface Action<T extends Card> extends Runnable {

    Board<T> getBoard();

    boolean isPossible();

}
