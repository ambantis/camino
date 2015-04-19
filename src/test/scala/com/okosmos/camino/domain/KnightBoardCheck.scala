package com.okosmos.camino.domain

import com.okosmos.camino.FunSpecChecker

class KnightBoardCheck extends FunSpecChecker {

  describe("a knight board") {
    it("should have remaining and taken squares that correspond to total squares") (pending)
    it("should have remaining spaces equal to `n * n - 1` on a newly created board") (pending)
    it("should have remaining spaces that correspond to the number of moves") (pending)
    it("should not be done if fewer moves than the number of spaces have been made") (pending)
    it("should generate 8 boards with `this.next` if all knight moves are valid") (pending)
    it("should generate N boards with `this.next` according to `8 - invalidMoves`") (pending)
  }
}
