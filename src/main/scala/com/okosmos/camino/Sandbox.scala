package com.okosmos.camino

import com.okosmos.camino.domain.Board

object Sandbox {

  def findSolutionFor(n: Long): Board = {
    def iter(acc: Seq[Board], rem: Seq[Board]): Board = {
      if (rem.isEmpty || acc.nonEmpty ) {
        acc.head.show()
        acc.head
      }
      else {
        val nextRem = (rem.head.next ++ rem.tail).sortBy(_.remaining)
        println(s"nextRem.size = ${nextRem.size} with head remaining = ${nextRem.head.remaining}")
        iter(acc, nextRem)
      }
    }
    iter(Nil, Board.forKnights(8).next.toList)
  }
}
