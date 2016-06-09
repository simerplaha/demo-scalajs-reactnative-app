package mobile.components

import chandu0101.macros.tojs.JSMacro
import japgolly.scalajs.react.React

import scala.scalajs.js
import scala.scalajs.js.{UndefOr => U, undefined}

case class Switch(key: U[String] = undefined,
                  style: U[js.Any] = undefined,
                  ref: U[SwitchM => _] = undefined,
                  value: U[Boolean] = undefined,
                  disabled: U[Boolean] = undefined,
                  onValueChange: U[Boolean => _] = undefined,
                  testID: U[String => _] = undefined,
                  tintColor: U[String => _] = undefined,
                  onTintColor: U[String => _] = undefined,
                  thumbTintColor: U[String => _] = undefined
                 ) {
  def apply() = {
    val props = JSMacro[Switch](this)
    React.createElement(React.Switch, props)
  }

}

@js.native
trait SwitchM extends js.Object
