package com.okosmos.camino

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object CaminoApp extends App {

 val name = "camino-app"
 val config = ConfigFactory.load()

 val system = ActorSystem(name, config)



}
