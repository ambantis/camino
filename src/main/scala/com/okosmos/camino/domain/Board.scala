package com.okosmos.camino.domain

import scala.language.postfixOps

trait Board {
  def solution: List[Position]
  def isDone: Boolean
  def next: Seq[Board]
}

object Board {
  def forKnights(n: Int, start: KnightPosition = KnightPosition(0,0)): Board = {
    require(start.x >= 0 && start.y >= 0 && start.x < n && start.y < n, "invalid start position")
    require(n <= Int.MaxValue - 2, "warning: size of board risks error due to integer overflow")
    val row = Vector.fill(n)(0)
    KnightBoard(
      knight = start,
      min = 0,
      max = n - 1,
      i = 0,
      remaining = n * n,
      squares = Vector.fill(n)(row)
    ).update(start).get
  }
}

private [domain] case class KnightBoard(knight: KnightPosition,
                                        min: Int,
                                        max: Int,
                                        i: Int,
                                        remaining: Int,
                                        squares: Vector[Vector[Int]]) extends Board {
  override lazy val solution: List[KnightPosition] = {
    lazy val pointsWithIndex: List[(KnightPosition, Int)] =
      for {
        (row, x) <- squares.zipWithIndex.toList
        (n, y) <- row.zipWithIndex if n > 0
    } yield KnightPosition(x, y) -> n
    if (isDone) pointsWithIndex.sortBy(_._2).map(_._1) else List.empty
  }

  override def isDone: Boolean = remaining == 0

  override def next: Seq[Board] = knight.nextMoves map update flatten

  def isValid(pos: KnightPosition): Boolean =
    pos.x >= min && pos.y >= min && pos.x <= max && pos.y <= max

  def isFree(pos: KnightPosition): Boolean = isValid(pos) && squares(pos.x)(pos.y) > 0

  def update(pos: KnightPosition): Option[Board] =
    if (isFree(pos))
      Some(copy(
        knight = pos,
        i = i + 1,
        remaining = remaining - 1,
        squares = squares.updated(pos.x, squares(pos.x).updated(pos.y, i + 1))
      ))
    else None
}
