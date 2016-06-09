package mobile.components


import chandu0101.macros.tojs.JSMacro
import japgolly.scalajs.react.React

import scala.scalajs.js


case class ActivityIndicatorIOS(size: js.UndefOr[ActivityIndicatorIOSSize] = js.undefined,
                                hidesWhenStopped: js.UndefOr[Boolean] = js.undefined,
                                style: js.UndefOr[js.Any] = js.undefined,
                                onLayout: js.UndefOr[js.Function] = js.undefined,
                                ref: js.UndefOr[ActivityIndicatorIOSM => _] = js.undefined,
                                color: js.UndefOr[String] = js.undefined,
                                key: js.UndefOr[String] = js.undefined,
                                animating: js.UndefOr[Boolean] = js.undefined) {

  def apply() = {
    val props = JSMacro[ActivityIndicatorIOS](this)
    React.createElement(React.ActivityIndicatorIOS, props)
  }

}


class ActivityIndicatorIOSSize private(val value: String) extends AnyVal

object ActivityIndicatorIOSSize {

  val SMALL = new ActivityIndicatorIOSSize("small")
  val LARGE = new ActivityIndicatorIOSSize("large")

  def newSize(size: String) = new ActivityIndicatorIOSSize(size)
}

@js.native
trait ActivityIndicatorIOSM extends js.Object