package org.abos.cards.core;

import java.util.Collection;

public interface Stack<T extends Card> extends Named, Collection<T> {

    void shuffle();

}
