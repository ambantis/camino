package com.okosmos.camino.domain;

import java.util.List;
import java.util.Optional;

public abstract class Board {
    public abstract Optional<Solution> solution();

    public abstract boolean isDone();

    public abstract int getRemaining();

    public abstract List<Board> next();

}
