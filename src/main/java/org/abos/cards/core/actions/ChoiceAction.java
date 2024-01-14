package org.abos.cards.core.actions;

import org.abos.cards.core.Card;
import org.abos.cards.core.SubGame;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public abstract class ChoiceAction<T extends Card> extends StackAction<T> {

    protected final BiPredicate<SubGame<T>, T> selector;

    protected Card recentSelection;

    public ChoiceAction(SubGame<T> subGame, String stackName, BiPredicate<SubGame<T>, T> selector) {
        super(subGame, stackName);
        this.selector = Objects.requireNonNull(selector);
    }

    @Override
    public boolean isPossible() {
        return subGame.getBoard().getStackByName(stackName).stream().anyMatch(card -> selector.test(subGame, card));
    }

    protected abstract Card select(List<T> choices);

    @Override
    public void run() {
        recentSelection = select(subGame.getBoard().getStackByName(stackName).stream().filter(card -> selector.test(subGame, card)).toList());
    }

    public Card getRecentSelection() {
        return recentSelection;
    }
}
