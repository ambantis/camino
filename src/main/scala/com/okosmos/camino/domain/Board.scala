package com.okosmos.camino.domain

import scala.language.postfixOps

trait Board {
  def solution: List[Position]
  def isDone: Boolean
  def remaining: Int
  def next: Seq[Board]
  def show(): Unit
}

object Board {
  def forKnights(n: Int, start: Position = KnightPosition(0,0)): Board = {
    require(start.x >= 0 && start.y >= 0 && start.x < n && start.y < n, "invalid start position")
    require(n > 1, "invalid board size")
    require(n <= Int.MaxValue - 2, "warning: size of board risks error due to integer overflow")
    val row = Vector.fill(n)(0)
    KnightBoard(
      knight = start,
      min = 0,
      max = n - 1,
      i = 1,
      remaining = n * n - 1,
      squares = Vector.fill(n)(row).updated(start.y, row.updated(start.x, 1))
    )
  }
}

private [domain]
case class KnightBoard(knight: Position,
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

  def isValid(pos: Position): Boolean =
    pos.x >= min && pos.y >= min && pos.x <= max && pos.y <= max

  def isFree(pos: Position): Boolean = isValid(pos) && squares(pos.y)(pos.x) == 0

  def update(pos: Position): Option[KnightBoard] =
    if (isFree(pos))
      Some(copy(
        knight = pos,
        i = i + 1,
        remaining = remaining - 1,
        squares = squares.updated(pos.y, squares(pos.y).updated(pos.x, i + 1))
      ))
    else None

  def show(): Unit = {
    println(s"i = $i, Knight = $knight")
    for (row <- squares.reverse) println(row.map(x => f"$x%02d").mkString(","))
  }
}
