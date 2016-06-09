package examples

import chandu0101.scalajs.rn.styles.NativeStyleSheet
import japgolly.scalajs.react._
import mobile.components._

import scala.scalajs.js
import scala.scalajs.js.Math

object BreadCrumbNavigationBarSample {

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

    def newRandomRoute(): js.Dynamic = {
      NavigatorRoute(title = s"#${Math.ceil(Math.random() * 1000)}", component = null, getProps = null).toJson
    }

    def renderScene(route: NavigatorRoute, navigator: NavigatorM) = {
      ScrollView(style = styles.scene)(
        NavButton((() => navigator.push(newRandomRoute().asInstanceOf[js.Object]), "Push")),
        NavButton((() => navigator.pop(), "Close breadcrumb Example"))
      )
    }

    def rightContentForRoute(route: NavigatorRoute, navigator: NavigatorM): ReactElement = {
      null
    }

    def titleContentForRoute(route: NavigatorRoute, navigator: NavigatorM): ReactElement = {
      TouchableOpacity(onPress = () => navigator.push(newRandomRoute().asInstanceOf[js.Object]))(
        View()(
          Text(style = styles.titleText)(route.title)
        )
      )
    }

    def iconForRoute(route: js.Dynamic, navigator: NavigatorM): ReactElement = {
      TouchableOpacity(onPress = () => navigator.popToRoute(route.asInstanceOf[js.Object]))(
        View(style = styles.crumbIconPlaceholder)(
        )
      )
    }

    def separatorForRoute(route: NavigatorRoute, navigator: NavigatorM): ReactElement = {
      TouchableOpacity(onPress = () => navigator.pop())(
        View(style = styles.crumbSeparatorPlaceholder)(
        )
      )
    }

  }


  val component = ReactComponentB[NavigatorM]("BreadCrumbNavigationBarSample")
    .stateless
    .backend(new Backend(_))
    .render((P) => {
      val backend = P.backend
      Navigator(
        style = styles.container,

        renderScene = (route, nav) => backend.renderScene(NavigatorRoute.fromJson(route), nav),
        initialRoute = NavigatorRoute.fromJson(backend.newRandomRoute()).toJson.asInstanceOf[js.Object],
        navigationBar =
          NavigatorBreadcrumbNavigationBar(
            style = styles.breadCrumbBar,
            routeMapper =
              BreadcrumbNavigationBarRouteMapper(
                //                iconForRoute = backend.iconForRoute _,
                //                titleContentForRoute = backend.titleContentForRoute _,
                //                rightContentForRoute = backend.rightContentForRoute _,
                //                separatorForRoute = backend.separatorForRoute _,
                iconForRoute = (route, nav) => backend.iconForRoute(route, nav),
                titleContentForRoute = (route, nav) => backend.titleContentForRoute(NavigatorRoute.fromJson(route), nav),
                rightContentForRoute = (route, nav) => backend.rightContentForRoute(NavigatorRoute.fromJson(route), nav),
                separatorForRoute = (route, nav) => backend.separatorForRoute(NavigatorRoute.fromJson(route), nav)

              )
          )()
      )()
    })
    .build

  object styles extends NativeStyleSheet {


    val titleText = style(fontSize := 18,
      color := "#666666",
      fontWeight.bold,
      textAlign.center,
      lineHeight := 32)


    val button = style(backgroundColor := "white",
      padding := 15,
      borderBottomWidth := 1.0 / React.PixelRatio.get(),
      borderBottomColor := "#CDCDCD"
    )

    val buttonText = style(fontSize := 17,
      fontWeight._500)

    val scene = style(flexOne,
      paddingTop := 90)

    val container = style(flexOne,
      overflow.hidden,
      backgroundColor := "#dddddd")

    val crumbIconPlaceholder = style(flexOne,
      backgroundColor := "#666666")


    val crumbSeparatorPlaceholder = style(
      flexOne,
      backgroundColor := "#aaaaaa"
    )
    val breadCrumbBar = style(marginTop := 70)

  }

  def apply(navigator: NavigatorM) = component(navigator)

}
