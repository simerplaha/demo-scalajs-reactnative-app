
import japgolly.scalajs.react.React
import mobile.components.ListViewDataSource

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g, literal => json}


package object mobile {
  @inline def load[T](lib: String): T = g.require(lib).asInstanceOf[T]

//  lazy val ReactNative = load[React]("react-native")

  lazy val Dimensions = load[js.Dynamic]("Dimensions")

//  lazy val FBSDKLogin = load[js.Dynamic]("react-native-fbsdklogin")

  /**
    * http://stackoverflow.com/questions/31097923/cannot-call-a-class-as-a-function-scala-js
    * https://github.com/timoxley/to-factory
    * https://github.com/babel/babel/issues/798
    */
  lazy val toFactory = load[js.Dynamic]("to-factory")

  type NEvent = js.Dynamic

  //  def createListViewDataSource[T,H](rowHasChanged: (T, T) => Boolean,
  //                                    sectionHeaderHasChanged: js.UndefOr[(H,H) => Boolean] = js.undefined,
  //                                    getRowData : js.UndefOr[(_,String,String) => _] = js.undefined,
  //                                    getSectionHeaderData : js.UndefOr[(_,String) => _] = js.undefined): ListViewDataSource[T] = {
  //    val ListDataSource = toFactory(React.ListView.asInstanceOf[js.Dynamic].DataSource)
  //    val j = json(rowHasChanged = rowHasChanged)
  //    sectionHeaderHasChanged.foreach(v => j.updateDynamic("sectionHeaderHasChanged")(v))
  //    getRowData.foreach(v => j.updateDynamic("getRowData")(v))
  //    getSectionHeaderData.foreach(v => j.updateDynamic("getSectionHeaderData")(v))
  //    js.Dynamic.newInstance(ListDataSource)(j).asInstanceOf[ListViewDataSource[T]]
  //  }

  def createListViewDataSource[T, H](rowHasChanged: (T, T) => Boolean,
                                     sectionHeaderHasChanged: js.UndefOr[(H, H) => Boolean] = js.undefined,
                                     getRowData: js.UndefOr[(_, String, String) => _] = js.undefined,
                                     getSectionHeaderData: js.UndefOr[(_, String) => _] = js.undefined): ListViewDataSource[T, H] = {
    val j = json(rowHasChanged = rowHasChanged)
    sectionHeaderHasChanged.foreach(v => j.updateDynamic("sectionHeaderHasChanged")(v))
    getRowData.foreach(v => j.updateDynamic("getRowData")(v))
    getSectionHeaderData.foreach(v => j.updateDynamic("getSectionHeaderData")(v))
    new ListViewDataSource[T, H](j)
  }


}