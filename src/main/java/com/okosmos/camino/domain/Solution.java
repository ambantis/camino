package com.okosmos.camino.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {
    private List<Position> moves;

    private Solution() {
    }

    public Solution(List<Position> moves) {
        this.moves = new ArrayList<>(Objects.requireNonNull(moves));
        if (moves.isEmpty())
            throw new IllegalArgumentException("a solution must have at least one move");
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        String lineBreak = System.getProperty("line.separator");
        sb.append("Solution in " + moves.size() + " moves:" + lineBreak);
        for (int i = 0; i < moves.size(); i++) {
            sb.append(i + 1 + ". " + moves.get(i).toString() + lineBreak);
        }
        return sb.toString();
    }

}
