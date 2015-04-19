package com.okosmos.camino.domain

import com.okosmos.camino.FunSpecChecker
import org.scalacheck.{Arbitrary, Gen}

class KnightPositionCheck extends FunSpecChecker with KnightPositionGenerator {

  describe("a knight") {
    it("should always make 8 moves from a given position") {
      forAll() { (pos: KnightPosition) =>
        pos.nextMoves should have size 8
      }
    }
    it("should only make legal knight moves") {
      forAll() { (pos: KnightPosition) =>
        pos.nextMoves forall { deltaPos =>
          (pos, deltaPos) match {
            case (p1, p2) if math.abs(p1.x - p2.x) == 1 => math.abs(p1.y - p2.y) == 2
            case (p1, p2) if math.abs(p1.x - p2.x) == 2 => math.abs(p1.y - p2.y) == 1
            case _ => false
          }
        }
      }
    }
  }

}

trait KnightPositionGenerator {

  private val coordinateGen = Gen.chooseNum(Int.MinValue + 2, Int.MaxValue - 2)

  implicit val arbKnightPosition: Arbitrary[KnightPosition] = Arbitrary {
    for {
      x <- coordinateGen
      y <- coordinateGen
    } yield KnightPosition(x, y)
  }
}
