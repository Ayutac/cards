package org.abos.cards.core.actions;

import org.abos.cards.core.Card;
import org.abos.cards.core.SubGame;

import java.util.Comparator;
import java.util.Objects;

public class SortAction<T extends Card> extends StackAction<T> {

    protected final Comparator<? super T> comparator;

    public SortAction(SubGame<T> subGame, String stackName, Comparator<? super T> comparator) {
        super(subGame, stackName);
        this.comparator = Objects.requireNonNull(comparator);
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public void run() {
        subGame.getBoard().getStackByName(stackName).sort(comparator);
    }

    public Comparator<? super T> getComparator() {
        return comparator;
    }
}
