//package mobile
//
//import chandu0101.macros.tojs.JSMacro
//import japgolly.scalajs.react._
//
//import scala.scalajs.js
//import scala.scalajs.js.Dynamic.{global => g, literal => json}
//import scala.scalajs.js.JSON
//
//
//case class FBSDKLoginButton(onLoginFinished: (js.Object, js.Object) => Unit,
//                            onLogoutFinished: () => Unit,
//                            readPermissions: js.Array[String],
//                            publishPermissions: js.Array[String]) {
//  def apply() = {
//    val props = JSMacro[FBSDKLoginButton](this)
//    println("props:" + JSON.stringify(props))
//    val loginButtonFactory = React.createFactory(FBSDKLogin.FBSDKLoginButton.asInstanceOf[js.Object])
//    loginButtonFactory(props).asInstanceOf[ReactComponentM[FBSDKLoginButton, Unit, Unit, TopNode]]
//  }
//}