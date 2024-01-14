package org.abos.cards.examples.classic.skat;

import org.abos.common.Named;

public enum SkatPlayer implements Named {

    ONE,
    TWO,
    THREE;

    final String name;

    SkatPlayer() {
        name = "player." + name();
    }

    @Override
    public String getName() {
        return name;
    }

    public SkatPlayer[] dealingOrder() {
        if (this == ONE) {
            return new SkatPlayer[]{TWO, THREE, ONE};
        }
        else if (this == TWO) {
            return new SkatPlayer[]{THREE, ONE, TWO};
        }
        else if (this == THREE) {
            return new SkatPlayer[]{ONE, TWO, THREE};
        }
        else {
            throw new AssertionError("Illegal Player Number detected!");
        }
    }
}

