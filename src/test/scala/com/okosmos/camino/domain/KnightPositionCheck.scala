package com.okosmos.camino.domain

import com.okosmos.camino.FunSpecChecker
import org.scalacheck.Gen

class KnightPositionCheck extends FunSpecChecker with KnightPositionGenerator {

  describe("a knight") {
    it("should only make legal knight moves") (pending)
    it("should always make 8 moves from a given position") (pending)
  }

}

trait KnightPositionGenerator {

  private val coordinateGen = Gen.chooseNum(Int.MinValue + 2, Int.MaxValue - 2)

  val knightPosGen =
    for {
      x <- coordinateGen
      y <- coordinateGen
    } yield KnightPosition(x, y)
}
