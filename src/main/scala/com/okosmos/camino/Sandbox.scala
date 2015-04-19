package com.okosmos.camino

import com.okosmos.camino.domain.Board

object Sandbox {

  def findSolutionFor(n: Long): Board = {
    def iter(acc: Seq[Board], i: Long, rem: Seq[Board]): Board = {
      if (i == n || rem.isEmpty) {
        rem.head.show()
        rem.head
      }
      else {
        val nextRem = rem.head.next ++ rem.tail
        println(s"i = $i, nextRem.size = ${nextRem.size}")
        iter(acc, i + 1, nextRem)
      }
    }
    iter(Nil, 2, Board.forKnights(8).next.toList)
  }
}
