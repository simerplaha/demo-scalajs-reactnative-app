package mobile.components

import chandu0101.macros.tojs.JSMacro
import japgolly.scalajs.react.React

import scala.scalajs.js

case class SliderIOS(style: js.UndefOr[js.Any] = js.undefined,
                     minimumTrackTintColor: js.UndefOr[String] = js.undefined,
                     minimumValue: js.UndefOr[Double] = js.undefined,
                     onSlidingComplete: js.UndefOr[Double => Unit] = js.undefined,
                     ref: js.UndefOr[SliderIOSM => _] = js.undefined,
                     maximumTrackTintColor: js.UndefOr[String] = js.undefined,
                     key: js.UndefOr[String] = js.undefined,
                     onValueChange: js.UndefOr[Double => Unit] = js.undefined,
                     value: js.UndefOr[Double] = js.undefined,
                     maximumValue: js.UndefOr[Double] = js.undefined) {

  def apply() = {
    val props = JSMacro[SliderIOS](this)
    React.createElement(React.SliderIOS, props)
  }

}

@js.native
trait SliderIOSM extends js.Object