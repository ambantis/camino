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

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.0" % "test"
)
