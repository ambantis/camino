package com.okosmos.camino.domain

import scala.language.postfixOps

trait Board {
  def solution: List[Position]
  def isDone: Boolean
  def next: Seq[Board]
}

object Board {
  def apply(n: Int, start: KnightPosition = KnightPosition(0,0)): Board = {
    require(start.x >= 0 && start.y >= 0 && start.x < n && start.y < n)
    val row = Vector.fill(n)(0)
    KnightBoard(start, Vector.fill(n)(row)).update(start).get
  }
}

private [domain] case class KnightBoard(knight: KnightPosition, squares: Vector[Vector[Int]]) extends Board {
  val max = squares.map(_.indices.max).max
  val min = squares.map(_.indices.min).min
  val nextCounter: Int = squares.flatten.max + 1

  override lazy val solution: List[KnightPosition] = {
    lazy val pointsWithIndex: List[(KnightPosition, Int)] =
      for {
        (row, x) <- squares.zipWithIndex.toList
        (n, y) <- row.zipWithIndex if n > 0
    } yield KnightPosition(x, y) -> n
    if (isDone) pointsWithIndex.sortBy(_._2).map(_._1) else List.empty
  }

  override def isDone: Boolean = squares.flatten.forall(_ > 0)

  override def next: Seq[Board] = knight.nextMoves map update flatten

  def isValid(pos: KnightPosition): Boolean =
    pos.x >= min && pos.y >= min && pos.x <= max && pos.y <= max

  def isFree(pos: KnightPosition): Boolean = isValid(pos) && squares(pos.x)(pos.y) > 0

  def update(pos: KnightPosition): Option[Board] =
    if (isFree(pos)) Some(KnightBoard(pos, squares.updated(pos.x, squares(pos.x).updated(pos.y, nextCounter))))
    else None
}
