package com.okosmos.camino.domain

trait Position {
  def x: Int
  def y: Int
  def nextMoves: Seq[Position]
}

case class KnightPosition(x: Int, y: Int) extends Position {
  def nextMoves: Seq[KnightPosition] = {
    val signs = Seq(-1, 1)
    val moves = Seq(1, 2)
    for {
      xSign <- signs
      ySign <- signs
      xDelta <- moves
      yDelta <- moves diff Seq(xDelta)
    } yield KnightPosition(x + (xSign * xDelta), y + (ySign * yDelta))
  }
}

