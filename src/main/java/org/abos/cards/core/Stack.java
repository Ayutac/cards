package org.abos.cards.core;

import org.abos.common.Named;

import java.util.Deque;
import java.util.List;

public interface Stack<T extends Card> extends Named, List<T>, Deque<T> {

    void shuffle();

}
