package org.abos.cards.core;

import org.abos.common.Named;

import java.util.Collections;
import java.util.Deque;
import java.util.List;

public interface Stack<T extends Card> extends Named, List<T> {

    default void shuffle() {
        Collections.shuffle(this);
    }

}
