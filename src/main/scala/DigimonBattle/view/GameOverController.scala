package DigimonBattle.view

import DigimonBattle.MainApp
import DigimonBattle.model.SharedMemory
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.text.Text
import scalafxml.core.macros.sfxml

@sfxml
class GameOverController(private var winner: Text) {

  // switch to digimon selection scene
  def playAgain(actionEvent: ActionEvent): Unit = {
    MainApp.showDigimonSelection
  }

  // exit the app
  def exit(actionEvent: ActionEvent): Unit = {
    Platform.exit
  }

  // assign winner name to the SharedMemory's variable
  winner.text = SharedMemory.winner + " is the winner!"



}
