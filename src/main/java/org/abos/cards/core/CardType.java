package org.abos.cards.core;

public interface CardType extends Named {

    Object getProperty(String property);

    Card create();
}
