import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin.autoImport._
import sbtassembly.PathList

object Build extends AutoPlugin {

  override def trigger = allRequirements

  override def projectSettings =
    Vector(
      resolvers ++= Vector(
//        "Sonatype SNAPSHOTS" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
      ),
      scalaVersion := Version.Scala,
      scalacOptions ++= Vector(
        "-unchecked",
        "-deprecation",
        "-language:_",
        "-target:jvm-1.8",
        "-encoding", "UTF-8"
      ),
      mainClass := Some("io.vertx.core.Launcher"),
      unmanagedSourceDirectories in Compile := Vector(scalaSource.in(Compile).value),
      unmanagedSourceDirectories in Test := Vector(scalaSource.in(Test).value)

    )
}
