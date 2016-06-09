package examples

//import chandu0101.scalajs.rn.ReactComponentB
import chandu0101.scalajs.rn.styles.NativeStyleSheet
import japgolly.scalajs.react.ReactComponentB
import mobile.components.{Text, View}

import scala.scalajs.js.Dynamic.{literal => json}


object HelloNative {

  val component = ReactComponentB[Unit]("HelloNative")
    .render(P => {
      View(style = Styles.container("red"))(
        Text(style = Styles.text)("Welcome to Scala-JS React"),
        Text(style = Styles.text)("To get started, edit HelloNative.scala ")
      )
    }).buildSpec


  object Styles extends NativeStyleSheet {
    def container(color: String) = style(flex := 12,
      alignItems.center,
      justifyContent.center,
      backgroundColor := color)

    val text = style(fontSize := 15, padding := 100, justifyContent.flexEnd)

    val baby = styleE(text, container("d"))(flexDirection.row, justifyContent.spaceAround)
  }

  def apply() = component
}
