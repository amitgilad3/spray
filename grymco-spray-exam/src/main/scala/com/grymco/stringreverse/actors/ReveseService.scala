package com.grymco.stringreverse.actors

import akka.actor.Actor
import spray.routing.HttpService

/**
 * Created by Amit on 21/11/2015.
 */
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
class StringServiceActor extends Actor with ReverseService{
  def actorRefFactory = context
  def receive: Receive = runRoute(reverseRoute)

}