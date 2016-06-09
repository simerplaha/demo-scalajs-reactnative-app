package examples

import japgolly.scalajs.react._
import mobile.{NEvent}
import mobile.components._

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => json}
import chandu0101.scalajs.rn.styles.NativeStyleSheet
import japgolly.scalajs.react.{BackendScope, ReactComponentB}
import scala.scalajs.js.Dynamic.{global => g, literal => json}

import mobile.Dimensions

object RootNavigation {

  var windowSize = Dimensions.get("window")

  val NavButton = ReactComponentB[(() => Unit, String)]("NavButton")
    .render(P => {
      val props = P.props
      TouchableHighlight(
        style = js.Array(styles.signin),
        underlayColor = "#B5B5B5",
        onPress = props._1
      )(Text(style = js.Array(styles.buttonText, styles.whiteFont))(props._2))
    }).build


  case class NavMenuProps(message: String, navigator: NavigatorM)

  val NavMenu = ReactComponentB[NavMenuProps]("NavMenu")
    .render(P => {
      val props = P.props
      //      ScrollView(style = styles.scene)(
      View(style = styles.container)(
        Image(style = styles.bg, source = ImageSource(uri = "http://i.imgur.com/xlQ56UK.jpg"))(),
        View(style = styles.header)(
          Image(style = styles.mark, source = ImageSource(uri = "http://i.imgur.com/da4G0Io.png"))()
        ),
        View(style = styles.inputs)(
          View(style = styles.facebookLogin)(
//            FBSDKLoginButton(
//              onLoginFinished = (error, result) => println(error + " --- " + result),
//              onLogoutFinished = () => println("logout"),
//              readPermissions = js.Array(""),
//              publishPermissions = js.Array("publish_actions")
//            )()
          ),
          View(style = styles.inputContainer)(
            Image(style = styles.inputUsername, source = ImageSource(uri = "http://i.imgur.com/iVVVMRX.png"))(),
            TextInput(
              style = js.Array(styles.input, styles.whiteFont),
              placeholder = "Username",
              placeholderTextColor = "#FFF",
              selectTextOnFocus = true)()
          ),
          View(style = styles.inputContainer)(
            Image(style = styles.inputPassword, source = ImageSource(uri = "http://i.imgur.com/ON58SIG.png"))(),
            TextInput(
              password = true,
              style = js.Array(styles.input, styles.whiteFont),
              placeholder = "Password",
              placeholderTextColor = "#FFF",
              selectTextOnFocus = true)()
          ),
          View(style = styles.forgotContainer)(
            Text(style = styles.whiteFont)("Forgot Password?")
          )
        ),
        View()(
          NavButton((() => props.navigator.push(json(id = "navbar")), "Sign In"))
          //          Text(style = styles.greyFont)("Sign In")
        ),
        View(style = styles.signup)(
          Text(style = styles.greyFont)("Don't have an account? Sign Up!")
        )
      )
    }).build


  class Backend(t: BackendScope[_, _]) {

    def renderScene(route: NavigatorRoute, nav: NavigatorM) = {
      if (route.id.isDefined) {
        route.id.get match {
          case "navbar" => NavigationBarSample(nav)
          //          case "jumpnav" => JumpingNavSample(nav)
          case "breadcrumbs" => BreadCrumbNavigationBarSample(nav)
          case _ => NavMenu(NavMenuProps(message = route.title, navigator = nav))
        }
      } else NavMenu(NavMenuProps(message = route.title, navigator = nav))
    }

    def configureScene(route: NavigatorRoute): NavigatorSceneConfig = {
      if (route.sceneConfig.isDefined) route.sceneConfig.get.asInstanceOf[NavigatorSceneConfig]
      else NavigatorS.SceneConfigs.FloatFromRight
    }
  }


  val component = ReactComponentB[Unit]("NavigatorExample")
    .stateless
    .backend(new Backend(_))
    .render((P) => {
      val component = null
      val backend = P.backend
      Navigator(
        initialRoute = NavigatorRoute(title = "First Scene", getProps = null, component = component).toJson.asInstanceOf[js.Object],
        renderScene = (route, nav) => backend.renderScene(NavigatorRoute.fromJson(route), nav),
        configureScene = (dynamic: js.Dynamic) => backend.configureScene(NavigatorRoute.fromJson(dynamic))
      )()
    }).buildSpec


  object styles extends NativeStyleSheet {

    val container = style(
      flexDirection := "column",
      flex := 1,
      backgroundColor := "transparent"
    )


    val bg = style(
      position := "absolute",
      left := 0,
      top := 0,
      width := windowSize.width.asInstanceOf[Double],
      height := windowSize.height.asInstanceOf[Double]
    )

    val header = style(
      justifyContent := "center",
      alignItems := "center",
      flex := .5,
      backgroundColor := "transparent"
    )

    val facebookLogin = style(
      justifyContent := "center",
      alignItems := "center",
      padding := 10,
      borderWidth := 1,
      borderBottomColor := "#CCC",
      borderColor := "transparent"
    )

    val mark = style(
      width := 150,
      height := 150
    )
    val signin = style(
      backgroundColor := "#3B5998",
      padding := 20,
      alignItems := "center"
    )
    val signup = style(
      justifyContent := "center",
      alignItems := "center",
      flex := .15
    )
    val inputs = style(
      marginTop := 10,
      marginBottom := 10,
      flex := .25
    )
    val inputPassword = style(
      marginLeft := 15,
      width := 20,
      height := 21
    )
    val inputUsername = style(
      marginLeft := 15,
      width := 20,
      height := 20
    )
    val inputContainer = style(
      padding := 10,
      borderWidth := 1,
      borderBottomColor := "#CCC",
      borderColor := "transparent"
    )
    val input = style(
      position := "absolute",
      left := 61,
      top := 12,
      right := 0,
      height := 20,
      fontSize := 14
    )
    val forgotContainer = style(
      alignItems := "flex-end",
      padding := 15
    )
    val greyFont = style(
      color := "#D8D8D8"
    )
    val whiteFont = style(
      color := "#FFF"
    )

    val logo = style(
      color := "#FFF",
      fontSize := 20
    )

    val logoCaption = style(
      color := "#FFF",
      fontSize := 13
    )

    val buttonText = style(fontSize := 17,
      fontWeight._500)
  }

  def title: String = "Navigator"

  def description: String = "JS-implemented navigation"
}
