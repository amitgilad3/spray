package com.grymco.stringreverse.actors

import akka.actor.Actor
import spray.routing.HttpService

/**
 * Created by Amit on 21/11/2015.
 */
class StringServiceActor extends Actor with HttpService{
   val myRoute =
    parameters("string".asInstanceOf[String]) { (string) =>

      complete(s"${string.reverse}")
    }
   def receive: Receive = runRoute(myRoute)

  def actorRefFactory = context
}
