package mobile.components

import chandu0101.macros.tojs.JSMacro
import japgolly.scalajs.react.{React, ReactElement}

import scala.scalajs.js

case class TouchableHighlight(onPressIn: js.UndefOr[() => Unit] = js.undefined,
                              onPress: js.UndefOr[() => Unit] = js.undefined,
                              style: js.UndefOr[js.Any] = js.undefined,
                              delayPressIn: js.UndefOr[Int] = js.undefined,
                              onHideUnderlay: js.UndefOr[() => Unit] = js.undefined,
                              ref: js.UndefOr[TouchableHighlightM => _] = js.undefined,
                              onPressOut: js.UndefOr[() => Unit] = js.undefined,
                              key: js.UndefOr[String] = js.undefined,
                              onLongPress: js.UndefOr[() => Unit] = js.undefined,
                              underlayColor: js.UndefOr[String] = js.undefined,
                              delayPressOut: js.UndefOr[Int] = js.undefined,
                              delayLongPress: js.UndefOr[Int] = js.undefined,
                              onShowUnderlay: js.UndefOr[() => Unit] = js.undefined,
                              activeOpacity: js.UndefOr[Int] = js.undefined,
                              accessible: js.UndefOr[Boolean] = js.undefined) {

  def apply(children: ReactElement) = {
    val props = JSMacro[TouchableHighlight](this)
    React.createElement(React.TouchableHighlight, props, children)
  }
}

@js.native
trait TouchableHighlightM extends js.Object