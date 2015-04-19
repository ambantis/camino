package com.okosmos.camino.service

import akka.actor.{ActorRef, ActorLogging, Actor}
import akka.dispatch.RequiresMessageQueue
import com.okosmos.camino.domain.Board

class Worker(collector: ActorRef) extends Actor with ActorLogging with RequiresMessageQueue[ChessBoardMailbox] {

  import context.parent
  override def receive = {
    case b: Board =>
      val (solved, unsolved) = b.next partition(_.isDone)
      solved foreach (collector ! _)
      unsolved foreach (parent ! _)
  }
}
