package DigimonBattle.view

import DigimonBattle.MainApp
import DigimonBattle.model.SharedMemory
import scalafx.event.ActionEvent
import scalafx.scene.control.TextField
import scalafxml.core.macros.sfxml

@sfxml
class PlayerNameInputController (private val p1Name: TextField, private val p2Name:TextField) {

  // submit Name and assign the name from the input to SharedMemory's player name
  def submitName(actionEvent: ActionEvent): Unit = {
    SharedMemory.player1Name = p1Name.getText
    SharedMemory.player2Name = p2Name.getText
    MainApp.showDigimonSelection()
  }

  // go back to main menu
  def backToMainMenu(actionEvent: ActionEvent): Unit = {
    MainApp.showMain()
  }



}
