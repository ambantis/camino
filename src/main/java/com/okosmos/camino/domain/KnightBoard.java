package com.okosmos.camino.domain;

import java.util.*;

public class KnightBoard extends Board {
    private Position knight;
    private int[][] squares;
    private int min;
    private int max;
    private int i;
    private int remaining;

    private KnightBoard() {
    }

    public KnightBoard(int n, int x, int y) {
        this(new KnightPosition(x, y), 0, n - 1, 1, n * n - 1, new int[n][n]);
        squares[y][x] = 1;
    }

    public KnightBoard(Position knight, int min, int max, int i, int remaining, int[][] squares) {
        this.knight = knight;
        this.min = min;
        this.max = max;
        this.i = i;
        this.remaining = remaining;
        this.squares = copyFrom(squares);
    }

    @Override
    public Optional<Solution> solution() {
        if (!isDone()) {
            return Optional.empty();
        } else {
            Position[] moves = new Position[max];
            int sort;
            for (int col = 0; col < squares.length; col++) {
                for (int row = 0; row < squares[col].length; row++) {
                    if ((sort = squares[col][row]) != 0) {
                        moves[sort] = new KnightPosition(row, col);
                    }
                }
            }
            return Optional.of(new Solution(Arrays.asList(moves)));
        }
    }

    @Override
    public boolean isDone() {
        return remaining == 0;
    }

    @Override
    public int getRemaining() {
        return remaining;
    }

    @Override
    public Board[] next() {
        return knight.nextMoves()
                .stream()
                .map(this::update)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted((b1, b2) -> b2.getRemaining() - b1.getRemaining())
                .toArray(Board[]::new);
    }

    private int[][] copyFrom(int[][] that) {
        int[][] result = new int[that.length][that[0].length];
        for (int p = 0; p < that.length; p++)
            result[p] = Arrays.copyOf(that[p], that[p].length);
        return result;
    }

    private boolean isFree(Position pos) {
        return isValid(pos) && squares[pos.getY()][pos.getX()] == 0;
    }

    private boolean isValid(Position pos) {
        return pos.getX() >= min && pos.getX() <= max && pos.getY() >= min && pos.getY() <= max;
    }

    private Optional<KnightBoard> update(Position pos) {
        if (isFree(pos)) {
            int[][] updatedSquares = copyFrom(squares);
            updatedSquares[pos.getY()][pos.getX()] = i + 1;
            return Optional.of(new KnightBoard(pos, min, max, i + 1, remaining - 1, updatedSquares));
        } else {
            return Optional.empty();
        }
    }

    private String rowToString(int[] row) {
        StringBuilder sb = new StringBuilder();
        String lineBreak = System.getProperty("line.separator");
        sb.append("[ ");
        for (int i : row) {
            String entry = (i == 0) ? "   " : String.format("%02d", i) + " ";
            sb.append(entry);
        }
        sb.append(']');
        sb.append(lineBreak);
        return sb.toString();
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        String lineBreak = System.getProperty("line.separator");
        sb.append("KnightBoard, i = " + i + " remaining = " + remaining + lineBreak);
        for (int i = squares.length-1; i >= 0; i--) {
            sb.append(rowToString(squares[i]));
        }
        return sb.toString();
    }
}
