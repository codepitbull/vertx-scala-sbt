import sbt.Package._

lazy val `vertx-scala-sbt` = project
  .in(file("."))

version := "0.1-SNAPSHOT"
name := "vertx-scala-sbt"
organization := "io.vertx"
scalaVersion := "2.12.1"

libraryDependencies ++= Vector (
  Library.vertxLangScala,
  Library.vertxCodegen,
  Library.vertxWeb,
  Library.scalaTest       % "test"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) => MergeStrategy.last
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
  case PathList("codegen.json") => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:io.vertx.scala.sbt.DemoVerticle"))

initialCommands := """|import io.vertx.lang.scala._
                     |import io.vertx.scala.core._
                     |import io.vertx.scala.sbt._
                     |import scala.concurrent.Future
                     |import scala.concurrent.Promise
                     |import scala.util.Success
                     |import scala.util.Failure
                     |val vertx = Vertx.vertx
                     |implicit val executionContext = io.vertx.lang.scala.VertxExecutionContext(vertx.getOrCreateContext)
                     |""".stripMargin