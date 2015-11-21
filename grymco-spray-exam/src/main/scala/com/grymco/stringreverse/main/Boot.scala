package com.grymco.stringreverse.main

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.grymco.stringreverse.actors.StringServiceActor
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Created by Amit on 21/11/2015.
 */
object Boot extends App{
  // ActorSystem to host our application in
  implicit val actorSystem = ActorSystem("reverse-string-system")

  val reverseService = actorSystem.actorOf(Props[StringServiceActor] , "reverse-string-actor")

  implicit val timeout = Timeout(5.seconds)

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(reverseService, interface = "localhost", port = 8080)


}
