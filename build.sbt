name := "camino"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8",
  "-language:postfixOps"
)

libraryDependencies ++= {
  val akkaVersion = "2.3.9"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
    "org.scalatest" %% "scalatest" % "2.2.1" % "test",
    "org.scalacheck" %% "scalacheck" % "1.12.0" % "test"
  )
}
