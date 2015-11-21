package com.grymco.stringreverse.actors

import akka.actor.Actor
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import spray.routing.HttpService

/**
 * Created by Amit on 21/11/2015.
 */
// this trait defines our service behavior independently from the service actor
trait ReverseService extends HttpService {
  val logger = Logger(LoggerFactory.getLogger("ReverseService"))
  val reverseRoute =
  get {
    path("reversestring") {
      parameters("string".asInstanceOf[String]) { (string) =>
        logger.info("get request recieved with the following word: " + string + " and reversed to: " + string.reverse )
        complete(s"${string.reverse}")
      }
    }
  }
}
// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class StringServiceActor extends Actor with ReverseService{
  val loggerStringServiceActor = Logger(LoggerFactory.getLogger("StringServiceActor"))
  override def preStart() = {loggerStringServiceActor.info("Actor " +  self.path.name  + " initialized")}
  override def postStop() = {loggerStringServiceActor.info("Actor " +  self.path.name  + " stoped")}
  def actorRefFactory = context
  def receive: Receive =  runRoute(reverseRoute)
}