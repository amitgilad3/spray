package com.grymco.stringreverse.actors

import akka.actor.Actor
import spray.routing.HttpService

/**
 * Created by Amit on 21/11/2015.
 */
// this trait defines our service behavior independently from the service actor
trait ReverseService extends HttpService {


  val reverseRoute =
  get {
    path("reversestring") {
      parameters("string".asInstanceOf[String]) { (string) =>
        complete(s"${string.reverse}")
      }
    }
  }


}

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class StringServiceActor extends Actor with ReverseService{
  def actorRefFactory = context
  def receive: Receive = runRoute(reverseRoute)

}