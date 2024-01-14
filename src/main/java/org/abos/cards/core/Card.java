package org.abos.cards.core;

import org.abos.common.Named;

public interface Card extends Named {

    CardType getType();

    @Override
    default String getName() {
        return getType().getName();
    }

    default Object getProperty(final String property) {
        return getType().getProperty(property);
    }
}
