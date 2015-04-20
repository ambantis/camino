package com.okosmos.camino.domain;

import java.util.LinkedList;
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
        List<Position> result = new LinkedList<>();
        for (int xSign : signs) {
            for (int ySign : signs) {
                for (int xDelta : moves) {
                    for (int yDelta : moves) {
                        result.add(new KnightPosition(x + (xSign * xDelta), y + (ySign * yDelta)));
                    }
                }
            }
        }
        return result;
    }
}
