package com.okosmos.camino

import akka.actor.{Props, ActorSystem}
import com.okosmos.camino.domain.Board
import com.okosmos.camino.service.SolutionFinder
import com.okosmos.camino.service.SolutionFinder.BoardRequest
import com.typesafe.config.ConfigFactory

object CaminoApp extends App {

 val name = "camino-app"
 val config = ConfigFactory.load()

 val system = ActorSystem(name, config)

 val finder = system.actorOf(Props(classOf[SolutionFinder]), "solution-finder")

 println(s"system is starting up")

 finder ! BoardRequest(Board.forKnights(8))

}
