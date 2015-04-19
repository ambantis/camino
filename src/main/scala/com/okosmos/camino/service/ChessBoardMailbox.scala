package com.okosmos.camino.service

import akka.actor.ActorSystem
import akka.dispatch.PriorityGenerator
import akka.dispatch.UnboundedPriorityMailbox
import com.okosmos.camino.domain.Board
import com.typesafe.config.Config

class ChessBoardMailbox(settings: ActorSystem.Settings, config: Config)
  extends UnboundedPriorityMailbox(
    PriorityGenerator {
      case b: Board => b.remaining
      case _        => 0
    }
  )




