package io.vertx.scala.sbt

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class DemoVerticle extends ScalaVerticle {


  override def start(): Future[Unit] = {
    val promise = Promise[Unit]
    val router = Router.router(vertx)
    router.get("/hello").handler(_.response().end("world"))

    vertx
      .createHttpServer()
      .requestHandler(a => a.response().end("Hello World"))
      .listenFuture(8666)
      .andThen{
        case Success(_) => promise.success(())
        case Failure(t) => promise.failure(t)
      }

    promise.future
  }
}
