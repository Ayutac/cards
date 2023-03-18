package org.abos.cards.core.rules;

import org.abos.cards.core.Board;
import org.abos.cards.core.Card;

import java.util.Comparator;
import java.util.Objects;

public class SortAction<T extends Card> extends StackAction<T> {

    protected final Comparator<? super T> comparator;

    public SortAction(Board<T> board, String stackName, Comparator<? super T> comparator) {
        super(board, stackName);
        this.comparator = Objects.requireNonNull(comparator);
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public void run() {
        board.getStackByName(stackName).sort(comparator);
    }

    public Comparator<? super T> getComparator() {
        return comparator;
    }
}
