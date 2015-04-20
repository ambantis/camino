package com.okosmos.camino.domain;

import java.util.List;

public abstract class Position {

    abstract int getX();

    abstract int getY();

    abstract List<Position> nextMoves();

    @Override
    public String toString() {
        return "Position(" + getX() + "," + getY() + ")";
    }

}
