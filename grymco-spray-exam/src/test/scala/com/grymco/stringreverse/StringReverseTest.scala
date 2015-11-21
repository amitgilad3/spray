package com.grymco.stringreverse

import com.grymco.stringreverse.actors.{ReverseService, StringServiceActor}
import org.specs2.mutable.Specification
import shapeless.~>
import spray.routing.{MissingQueryParamRejection, HttpService}
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

/**
 * Created by Amit on 21/11/2015.
 */
class StringReverseTest extends Specification with Specs2RouteTest with ReverseService  {
  def actorRefFactory = system

  "StringReverseService" should {

    //small test to check reverse
    "reverse the string grymco to ocmyrg" in {
      Get("/reversestring?string=grymco") ~> reverseRoute ~> check {
        status === OK
        responseAs[String] === "ocmyrg"
      }
    }

    //Sealing Routes -  translates Rejections to HTTP
    //I Can also add testing for other methods that are not get bet in any case of method that is not get the respone will be HTTP method not allowed, supported methods: GET
    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(reverseRoute) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }

    //missing query params
    "reject the request with a Missing Query Param Rejection if a required parameters is missing(in this case the param string is missing)" in {
      Get("/reversestring?strisssng=grymco") ~> reverseRoute ~> check { rejection === MissingQueryParamRejection("string") }
    }







  }
}
