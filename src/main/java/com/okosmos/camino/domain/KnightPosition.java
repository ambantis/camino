package com.okosmos.camino.domain;

import java.util.ArrayList;
import java.util.List;

public class KnightPosition extends Position {
    private int x;
    private int y;

    private int[] signs = {-1, 1};
    private int[] moves = {1, 2};

    private KnightPosition() {
    }

    public KnightPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public List<Position> nextMoves() {
        List<Position> result = new ArrayList<>(8);
        for (int xSign : signs) {
            for (int ySign : signs) {
                for (int xDelta : moves) {
                    int yDelta = (xDelta == 1) ? 2 : 1;
                    result.add(new KnightPosition(x + (xSign * xDelta), y + (ySign * yDelta)));
                }
            }
        }
        return result;
    }

    @Override public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof KnightPosition))
            return false;
        KnightPosition that = (KnightPosition) obj;
        return x == that.getX() && y == that.getY();
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
