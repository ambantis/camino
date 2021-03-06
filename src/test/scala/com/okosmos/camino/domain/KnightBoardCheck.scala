package com.okosmos.camino.domain

import com.okosmos.camino.FunSpecChecker
import org.scalacheck.Gen

class KnightBoardCheck extends FunSpecChecker {

  def knightBoardIter(n: Int): KnightBoard = {
    def iter(acc: KnightBoard, i: Int): KnightBoard = {
      if (i == n) acc
      else {
        iter(acc.next.head, i + 1)
      }
    }
    iter(Board.forKnights(8).asInstanceOf[KnightBoard], 1)
  }

  def knightBoardIterSeq(n: Int): Seq[KnightBoard] = {
    def iter(acc: Seq[KnightBoard], i: Int): Seq[KnightBoard] = {
      if (i == n) acc
      else {
        val nextAcc = acc.flatMap(_.next)
        println(s"i = $i, size = ${nextAcc.size}")
        iter(nextAcc, i + 1)
      }
    }
    iter(Seq(Board.forKnights(8).asInstanceOf[KnightBoard]), 1)
  }


  val stdBoardIdx = Gen.chooseNum(0, 7)

  describe("a knight board") {
    it("should have remaining spaces equal to `n * n - 1` on a newly created board") {
      forAll(Gen.chooseNum(2, 100)) { n =>
        Board.forKnights(n) match {
          case (k: KnightBoard) =>
            n * n - 1 shouldBe k.remaining
        }
      }
    }
    it("should have a knight position coordinates that correspond to the chess board") {
      forAll(stdBoardIdx, stdBoardIdx) { (x: Int, y: Int) =>
        Board.forKnights(8, KnightPosition(x, y)) match {
          case board: KnightBoard =>
            board.squares(y)(x) shouldBe 1
        }
      }
    }
    it("should have remaining and taken squares that correspond to total squares") {
      forAll(Gen.chooseNum(1, 10)) { n =>
        val board = knightBoardIter(n)
        board.i + board.remaining shouldBe board.squares.flatten.size
      }
    }
    it("should have remaining spaces that correspond to the number of moves") {
      forAll(Gen.chooseNum(1, 10)) { n =>
        knightBoardIter(n).i shouldBe n
      }
    }
    it("should not be done if fewer moves than the number of spaces have been made") {
      knightBoardIterSeq(10) forall (_.isDone) shouldBe false
    }
    it("should generate 8 boards with `this.next` if all knight moves are valid")(pending)
    it("should generate N boards with `this.next` according to `8 - invalidMoves`")(pending)
  }
}
