package examples

import chandu0101.scalajs.rn.styles.NativeStyleSheet
import japgolly.scalajs.react._
import mobile.components._

import scala.scalajs.js
import scala.scalajs.js.{JSON, Math}

object NavigationBarSample {


  val NavButton = ReactComponentB[(() => Unit, String)]("NavButton")
    .render(P => {
      val props = P.props
      TouchableHighlight(
        style = styles.button,
        underlayColor = "#B5B5B5",
        onPress = props._1
      )(Text(style = styles.buttonText)(props._2))
    }).build


  class Backend(t: BackendScope[NavigatorM, _]) {
    var count = 0

    def newRandomRoute(): js.Dynamic = {
      count += 1
      NavigatorRoute(title = s"#$count", component = null, getProps = null).toJson
    }

    def onPress(nav: NavigatorM) = {
      //      () => nav.immediatelyResetRouteStack(js.Array(
      //        newRandomRoute()
      //        //        newRandomRoute(),
      //        //        newRandomRoute()
      //      ))
      () =>
        nav.push(newRandomRoute().asInstanceOf[js.Object])
    }

    def renderScene(route: NavigatorRoute, navigator: NavigatorM) = {
      ScrollView(style = styles.scene)(
        Text(style = styles.messageText)(route.title),
        NavButton((onPress(navigator), "Product 1")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 2")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 3")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 4")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 5")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 6")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 7")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 8")),
        NavButton((() => {count = 0; navigator.popToTop()}, "Product 9"))
      )
    }

    def onLeftButton(route: js.Dynamic, navigator: NavigatorM, index: Int, navState: js.Dynamic): ReactElement = {
      //      println("JSONNNNNNNN \n\n\n" + JSON.stringify(navState.asInstanceOf[js.Object]))
      val navStateObj = NavigationBarNavState.fromJson(navState)
      if (index > 0) {
        val previousRoute = navStateObj.routeStack(index - 1)
        TouchableOpacity(onPress = () => navigator.pop())(
          View(style = styles.navBarLeftButton)(
            Text(style = styles.combinedText)(previousRoute.title.asInstanceOf[ReactNode])
          )
        )
      } else null
    }

    def onRightButtonButton(route: js.Dynamic, navigator: NavigatorM, index: Int, navState: js.Dynamic): ReactElement = {
      TouchableOpacity(onPress = () => navigator.push(newRandomRoute().asInstanceOf[js.Object]))(
        View(style = styles.navBarRightButton)(
          Text(style = styles.combinedText)("Next")
        )
      )
    }

    def onTitle(route: js.Dynamic, navigator: NavigatorM, index: Int, navState: js.Dynamic): ReactElement = {
      Text(style = styles.navBarTitleText)(
        s"${route.title}[${index}]"
      )
    }


  }


  val component = ReactComponentB[NavigatorM]("NavigationBarSample")
    .stateless
    .backend(new Backend(_))
    .render((P) => {
      val backend = P.backend
      Navigator(style = styles.container,
        renderScene = (route, nav) => backend.renderScene(NavigatorRoute.fromJson(route), nav),
        initialRoute = NavigatorRoute.fromJson(backend.newRandomRoute()).toJson.asInstanceOf[js.Object],
        navigationBar =
          NavigatorNavigationBar(
            style = styles.navBar,
            routeMapper =
              NavigationBarRouteMapper(
                Title = backend.onTitle _,
                LeftButton = backend.onLeftButton _,
                RightButton = backend.onRightButtonButton _
              ).toJS
          )()
      )()

    })
    .build

  object styles extends NativeStyleSheet {


    val messageText = style(fontSize := 17,
      fontWeight._500,
      padding := 15,
      marginTop := 50,
      marginLeft := 15)


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

    val navBar = style(backgroundColor := "white",
      height := 64,
      paddingBottom := 5,
      borderBottomWidth := 1.0 / React.PixelRatio.get(),
      borderBottomColor := "rgba(0, 0, 0, 0.5)",
      marginTop := 0)

    val navBarText = style(fontSize := 16,
      marginVertical := 10)

    val navBarTitleText = style(color := "black",
      fontWeight._500,
      marginVertical := 9)

    val navBarLeftButton = style(paddingLeft := 10)

    val navBarRightButton = style(paddingRight := 10)

    val navBarButtonText = style(color := "#5890ff")

    val container = style(flexOne)

    val combinedText = styleE(navBarText, navBarButtonText)()


  }

  def apply(navigator: NavigatorM) = component(navigator)

}
