package org.abos.cards.core;

import java.util.List;

public interface Stack<T extends Card> extends Named, List<T> {

    void shuffle();

}
