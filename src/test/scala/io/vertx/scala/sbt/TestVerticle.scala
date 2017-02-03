package io.vertx.scala.sbt

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class TestVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    val promise = Promise[Unit]
    vertx
      .eventBus()
      .consumer[String]("testAddress")
      .handler(_.reply("Hello World!"))
      .completionFuture()
      .andThen {
        case Success(_) => promise.success(())
        case Failure(t) => promise.failure(t)
      }
    promise.future
  }
}
