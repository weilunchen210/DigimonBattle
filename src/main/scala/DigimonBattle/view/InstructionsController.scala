package DigimonBattle.view

import DigimonBattle.MainApp
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class InstructionsController {

  // go back to main menu
  def back(actionEvent: ActionEvent): Unit = {
    MainApp.showMain()
  }

}
