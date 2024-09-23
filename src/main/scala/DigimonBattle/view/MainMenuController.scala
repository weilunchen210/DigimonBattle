package DigimonBattle.view

import DigimonBattle.MainApp
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.text.Text
import scalafxml.core.macros.sfxml

@sfxml
class MainMenuController (private var text4: Text){
  // show info
  def clickInfo(actionEvent: ActionEvent): Unit = {
    MainApp.showInstructions()
  }
  // exit main menu
  def clickExit(actionEvent: ActionEvent): Unit = {
    Platform.exit
  }
  // switch to player name input
  def clickPlay(actionEvent: ActionEvent): Unit={
    MainApp.showPlayerNameInput()
  }


}