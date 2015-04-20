package com.okosmos.camino.service

import akka.actor.{Props, Actor, ActorLogging}
import akka.routing.BalancingPool
import com.okosmos.camino.domain.{Position, Board}
import com.okosmos.camino.service.SolutionFinder.BoardSolution

object SolutionFinder {
  val name = "solution-finder"
  def props = Props(classOf[SolutionFinder])

  case class BoardRequest(board: Board)
  case class BoardSolution(solution: List[Position])
}

class SolutionFinder extends Actor with ActorLogging {
  import context.actorOf
  import SolutionFinder.BoardRequest

  val cores = Runtime.getRuntime.availableProcessors()

  val master = actorOf(BalancingPool(cores).props(Worker.props), "master")

  override def receive = {
    case BoardRequest(board) =>
      master ! board

    case BoardSolution(sol) =>
      log.info(s"${self.path.name} got a solution: $sol")

  }
}
