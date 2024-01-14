package org.abos.cards.core;

import java.util.List;

public interface Game<T extends Card> {

    List<SubGame<T>> getSubGames();

}
