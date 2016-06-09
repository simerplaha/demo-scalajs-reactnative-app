import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._

object Dependencies {

  //whatever version you have deployed locally of https://github.com/simerplaha/scalajs-react
  val scalajsReactVersion = "0.11.2-SNAPSHOT"

  val scalaAsyncVersion = "0.9.2"

  val scalaJSReact = libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % scalajsReactVersion

  val scalaJSReactExtra = libraryDependencies += "com.github.japgolly.scalajs-react" %%% "extra" % scalajsReactVersion

  val scalaAsync = libraryDependencies += "org.scala-lang.modules" %% "scala-async" % scalaAsyncVersion

  val chandu0101Macros = libraryDependencies += "com.github.chandu0101" %%% "macros" % "0.5.1"

  val coreModuleDeps = Seq(scalaJSReact, scalaJSReactExtra, chandu0101Macros)

  val exampleModuleDeps = Seq(scalaAsync)

}