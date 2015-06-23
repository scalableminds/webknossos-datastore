import sbt._
import sbt.Keys._
import play.Play.autoImport._
import PlayKeys._
import sbt.Task
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Dependencies{
  val braingamesVersion = "6.9.16-master-fix"

  val braingamesDataStore = "com.scalableminds" %% "braingames-datastore" % braingamesVersion
}

object Resolvers {
  val scmRel = Resolver.url("Scalableminds REL Repo", url("http://scalableminds.github.com/releases/"))(Resolver.ivyStylePatterns)
  val scmIntRel = "scm.io intern releases repo" at "http://maven.scm.io/releases/"
  val scmIntSnaps = "scm.io intern snapshots repo" at "http://maven.scm.io/snapshots/"
}

object ApplicationBuild extends Build {
  import Dependencies._
  import Resolvers._

  lazy val standaloneDatastore = Project("standalone-datastore", file("."))
    .enablePlugins(play.PlayScala)
    .settings(
      version := braingamesVersion, 
      libraryDependencies += braingamesDataStore, 
      scalaVersion := "2.10.2",
      resolvers ++= Seq(scmRel, scmIntRel, scmIntSnaps)
    )
}

