
name := "grymco-spray-exam"

version := "1.0"



scalaVersion := "2.11.7"
libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "ch.qos.logback" % "logback-classic" % "1.1.3"
  )
}
lazy val root = (project in file(".")).
  settings(
    name := "spray-grymco",
    version := "1.0",
    scalaVersion := "2.11.7",
    mainClass in Compile := Some("com.grymco.stringreverse.main.Boot")
  )
