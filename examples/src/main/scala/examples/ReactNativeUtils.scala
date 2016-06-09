package examples

import japgolly.scalajs.react._

object ReactNativeUtils {

  implicit def toNative(spec: ReactComponentSpec[Unit, Unit, Unit, TopNode]) =
    React.createElement(React.createClass(spec)).asInstanceOf[ReactComponentU_]


}
