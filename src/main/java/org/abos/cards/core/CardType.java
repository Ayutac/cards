package org.abos.cards.core;

import org.abos.common.Named;

public interface CardType extends Named {

    String NAME_PROPERTY = "Name";

    Object getProperty(final String property) throws IllegalArgumentException;
}
