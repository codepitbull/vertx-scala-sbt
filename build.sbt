import sbt.Package._
import sbt._

enablePlugins(DockerPlugin)

scalaVersion := "2.12.1"

libraryDependencies ++= Vector (
  Library.vertxLangScala,
  Library.vertxCodegen,
  Library.vertxWeb,
  Library.scalaTest       % "test"
)

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:io.vertx.scala.sbt.HttpVerticle"))

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) => MergeStrategy.last
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
  case PathList("codegen.json") => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

dockerfile in docker := {
  // The assembly task generates a fat JAR file
  val artifact: File = assembly.value
  val artifactTargetPath = s"/app/${artifact.name}"

  new Dockerfile {
    from("frolvlad/alpine-oraclejdk8:slim")
    add(artifact, artifactTargetPath)
    entryPoint("java", "-jar", artifactTargetPath)
    expose(8666)
  }
}