package com.jm2dev

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers
import spray.testkit.ScalatestRouteTest
import spray.http._
import StatusCodes._

class MyServiceSpec extends FunSpec with ScalatestRouteTest with MyService with MustMatchers {
  def actorRefFactory = system
  
  describe("MyService") {
    it("should return a greeting for GET requests to the root path") {
      Get() ~> myRoute ~> check {
        entityAs[String] must include("Say hello")
      }
    }

    it("should leave GET requests to other paths unhandled") {
      Get("/kermit") ~> myRoute ~> check {
        handled === false
      }
    }

    it("should return a MethodNotAllowed error for PUT requests to the root path") {
      Put() ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        entityAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
