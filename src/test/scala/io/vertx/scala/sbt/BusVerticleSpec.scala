package io.vertx.scala.sbt

import org.scalatest._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class BusVerticleSpec extends VerticleTesting[BusVerticle] with Matchers {

  "BusVerticle" should "reply to a message" in {
    val res = Await.result(
      vertx
        .eventBus()
        .sendFuture[String]("testAddress", "msg"),
      10000 millis
    )

    res.body() should equal("Hello World!")
  }

}
