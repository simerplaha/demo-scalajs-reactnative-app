package mobile.components

import chandu0101.macros.tojs.JSMacro
import japgolly.scalajs.react.React
import mobile.events.ReactEvent

import scala.scalajs.js

case class WebView(contentInset: js.UndefOr[EdgeInsets] = js.undefined,
                   url: String,
                   style: js.UndefOr[js.Any] = js.undefined,
                   javaScriptEnabledAndroid: js.UndefOr[Boolean] = js.undefined,
                   ref: js.UndefOr[WebViewM => _] = js.undefined,
                   injectedJavaScript: js.UndefOr[String] = js.undefined,
                   scalesPageToFit: js.UndefOr[Boolean] = js.undefined,
                   key: js.UndefOr[String] = js.undefined,
                   scrollEnabled: js.UndefOr[Boolean] = js.undefined,
                   onNavigationStateChange: js.UndefOr[NavigationState => Unit] = js.undefined,
                   bounces: js.UndefOr[Boolean] = js.undefined,
                   renderLoading: js.UndefOr[js.Function] = js.undefined,
                   automaticallyAdjustContentInsets: js.UndefOr[Boolean] = js.undefined,
                   renderError: js.UndefOr[js.Function] = js.undefined,
                   html: js.UndefOr[String] = js.undefined,
                   startInLoadingState: js.UndefOr[Boolean] = js.undefined) {

  def apply() = {
    val props = JSMacro[WebView](this)
    React.createElement(React.WebView, props)
  }

}

@js.native
trait NavigationState extends js.Object {

  def url: String = js.native

  def title: String = js.native

  def loading: Boolean = js.native

  def canGoBack: Boolean = js.native

  def canGoForward: Boolean = js.native

}

@js.native
trait WebViewM extends js.Object {

  def reload(): Unit = js.native

  def updateNavigationState(event: ReactEvent): NavigationState = js.native

  def getWebWiewHandle(): js.Dynamic = js.native

  def goForward(): Unit = js.native

  def goBack(): Unit = js.native

  def onLoadingStart(event: ReactEvent): Unit = js.native

  def onLoadingError(event: ReactEvent): Unit = js.native

  def onLoadingFinish(event: ReactEvent): Unit = js.native

}
