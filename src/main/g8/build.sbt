// -*- scala -*-
// Copyright (c) $year$, $copyright$

name := "$name$"

organization := "$organization$"

scalaVersion := "2.9.2"

maxErrors := 3

fork := true

// -Yrepl-sync fixes console issues in sbt
scalacOptions := Seq("-deprecation", "-unchecked", "-explaintypes", "-Yrepl-sync")

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

publishMavenStyle := true

initialCommands in console := "import $organization$._"

// Akka deps
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  val akkaVersion = "2.0.2"
  def akkaDep(artifactId: String) = "com.typesafe.akka" % artifactId % akkaVersion
  Seq(
    akkaDep("akka-testkit") % "test",
    akkaDep("akka-actor"),
    akkaDep("akka-slf4j"))
}

// Other deps
libraryDependencies ++= {
  val slf4jVersion = "1.6.4"
  def slf4jDep(artifactId: String) = "org.slf4j" % artifactId % slf4jVersion
  Seq(
    "org.scalatest" %% "scalatest" % "1.7.2" % "test",
    "com.jsuereth" %% "scala-arm" % "1.2",
    slf4jDep("jul-to-slf4j"),
    slf4jDep("slf4j-api"))
}

//resolvers += "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"

testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))

initialCommands in console := "import $organization$._"
