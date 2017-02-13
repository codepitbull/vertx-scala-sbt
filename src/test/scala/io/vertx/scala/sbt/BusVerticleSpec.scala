package io.vertx.scala.sbt

import org.scalatest._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class BusVerticleSpec extends VerticleTesting[BusVerticle] with Matchers {

  "BusVerticle" should "reply to a message" in {
    Await.result(
      vertx
        .eventBus()
        .sendFuture[String]("testAddress", "msg")
        .andThen {
          case Success(d) => d.body() should equal("Hello World!")
          case Failure(t) => throw new RuntimeException(t)
        },
      10000 millis
    )
  }

}
