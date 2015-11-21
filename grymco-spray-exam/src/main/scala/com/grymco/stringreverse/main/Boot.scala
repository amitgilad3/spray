package com.grymco.stringreverse.main

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.grymco.stringreverse.actors.StringServiceActor
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Created by Amit on 21/11/2015.
 */
object Boot extends App{
  //logger
  val logger = Logger(LoggerFactory.getLogger("ReverseBoot"))

  logger.info("initializing application")

  // ActorSystem to host our application in
  logger.info("creating actor system for  application")
  implicit val actorSystem = ActorSystem("reverse-string-system")

  logger.info("creating actor  of type StringServiceActor")
  val reverseService = actorSystem.actorOf(Props[StringServiceActor] , "reverse-string-actor")

  implicit val timeout = Timeout(5.seconds)

  // start a new HTTP server on port 8080 with our service actor as the handler
  logger.info("Binding actor StringServiceActor  to localhost:8080")
  IO(Http) ? Http.Bind(reverseService, interface = "localhost", port = 8080)


}
