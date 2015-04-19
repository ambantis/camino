package com.okosmos.camino

import org.scalatest.prop.{GeneratorDrivenPropertyChecks, Checkers}
import org.scalatest.{FunSpec, Matchers}

trait FunSpecChecker extends FunSpec with Matchers with GeneratorDrivenPropertyChecks
