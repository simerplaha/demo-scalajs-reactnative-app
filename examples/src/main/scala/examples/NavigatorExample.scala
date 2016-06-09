package examples

import mobile.components._
import japgolly.scalajs.react._
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => json}

//import chandu0101.scalajs.rn.ReactComponentB

import chandu0101.scalajs.rn.styles.NativeStyleSheet
import japgolly.scalajs.react.{BackendScope, ReactComponentB}

object NavigatorExample {


  val NavButton = ReactComponentB[(() => Unit, String)]("NavButton")
    .render(P => {
      val props = P.props
      TouchableHighlight(
        style = styles.button,
        underlayColor = "#B5B5B5",
        onPress = props._1
      )(Text(style = styles.buttonText)(props._2))
    }).build


  case class NavMenuProps(message: String, navigator: NavigatorM)

  val NavMenu = ReactComponentB[NavMenuProps]("NavMenu")
    .render(P => {
      val props = P.props
      ScrollView(style = styles.scene)(

        Image(style = styles.img, source = ImageSource(uri = "http://facebook.github.io/react/img/logo_og.png"))(),
        Text(style = styles.messageText)(props.message),
        NavButton((() => props.navigator.push(json(title = "Swipe right to dismiss",
          sceneConfig = NavigatorS.SceneConfigs.FloatFromRight)),
          "Float in from right")),
        NavButton((() => props.navigator.push(json(title = "Swipe down to dismiss",
          sceneConfig = NavigatorS.SceneConfigs.FloatFromBottom)),
          "Float in from Bottom")),
        NavButton((() => props.navigator.pop(), "Pop")),
        NavButton((() => props.navigator.popToTop(), "Pop to Top")),
        NavButton((() => props.navigator.push(json(id = "navbar")),
          "Nav Bar Example")),
        NavButton((() => props.navigator.push(json(id = "jumpnav")),
          "Jumping Nav Bar Example")),
        NavButton((() => props.navigator.push(json(id = "breadcrumbs")),
          "Breadcrumb Nav Bar Example"))
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
        //        ref = "navigator",
        style = styles.container,
        initialRoute = NavigatorRoute(title = "First Scene", getProps = null, component = component).toJson.asInstanceOf[js.Object],
        renderScene = (route, nav) => backend.renderScene(NavigatorRoute.fromJson(route), nav),
        configureScene = (dynamic: js.Dynamic) => backend.configureScene(NavigatorRoute.fromJson(dynamic))
      )()
    }).buildSpec


  object styles extends NativeStyleSheet {


    val messageText = style(fontSize := 17,
      fontWeight._500,
      padding := 15,
      marginTop := 50,
      marginLeft := 15)

    val container = style(flexOne)

    val button = style(backgroundColor := "white",
      padding := 15,
      borderBottomWidth := 1.0 / React.PixelRatio.get(),
      borderBottomColor := "#CDCDCD"
    )

    val buttonText = style(fontSize := 17,
      fontWeight._500)

    val scene = style(flexOne,
      paddingTop := 20,
      backgroundColor := "#EAEAEA")
    val img = style(width := 64, height := 64)

  }

  def title: String = "Navigator"

  def description: String = "JS-implemented navigation"
}
