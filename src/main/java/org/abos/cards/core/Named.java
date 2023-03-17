package org.abos.cards.core;

import java.util.Locale;

public interface Named {

    static String NAME_PROPERTY = "Name";

    String getName();

    static String fromUpperCase(String word) {
        return word.charAt(0) + word.substring(1).toLowerCase(Locale.ROOT);
    }

}
