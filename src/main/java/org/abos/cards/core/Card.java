package org.abos.cards.core;

public interface Card extends Named {

    CardType getType();

    @Override
    default String getName() {
        return getType().getName();
    }

    default Object getProperty(String property) {
        return getType().getProperty(property);
    }
}
