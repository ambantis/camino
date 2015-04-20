package com.okosmos.camino.service

import akka.actor.{Props, ActorLogging, Actor}
import akka.dispatch.RequiresMessageQueue
import com.okosmos.camino.domain.Board
import com.okosmos.camino.service.SolutionFinder.BoardSolution

object Worker {
  def name(n: Int) = s"worker-num-$n"
  def props = Props(classOf[Worker]).withDispatcher("prio-dispatcher")//.withMailbox("worker-mailbox")
}

class Worker extends Actor with ActorLogging /*with RequiresMessageQueue[ChessBoardMailbox]*/ {

  import context.parent
  override def receive = {
    case b: Board =>
      val (solved, unsolved) = b.next partition(_.isDone)
      log.info(s"processing ${unsolved.size} unsolved with priorities: ${unsolved.map(_.remaining).mkString(",")}")
      log.info(s"FOUND SOLUTIONS: $solved")
      solved foreach (s => sender().tell(BoardSolution(s.solution), parent))
      unsolved foreach (parent ! _)
  }
}
