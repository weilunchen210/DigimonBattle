package DigimonBattle.view

import DigimonBattle.MainApp
import DigimonBattle.model.Digimon.{Agumon, Gabumon, Gotsumon, Veemon}
import DigimonBattle.model.Player.Player
import DigimonBattle.model.SharedMemory
import scalafx.event.ActionEvent
import scalafx.scene.Group
import scalafx.scene.text.Text
import scalafxml.core.macros.sfxml


@sfxml
class DigimonSelectionController(private var text3: Text, private var turnToPickText: Text, private val digimonSelectionInfo: Group){
  var numOfDigimonSelected = 0
  val player1agumon: Agumon = new Agumon
  val player1gabumon: Gabumon = new Gabumon
  val player1gotsumon: Gotsumon = new Gotsumon
  val player1veemon: Veemon = new Veemon
  val player2agumon: Agumon = new Agumon
  val player2gabumon: Gabumon = new Gabumon
  val player2gotsumon: Gotsumon = new Gotsumon
  val player2veemon: Veemon = new Veemon

  turnToPickText.text = SharedMemory.player1Name +"'s Turn to Pick"

  // check whether each of player choose a digimon
  def checkDigimonSelected(): Unit = {
    if (numOfDigimonSelected == 2) {
      MainApp.showPlay()
    }
  }

  // show selectionInfo
  def showInfo(actionEvent: ActionEvent): Unit = {
    digimonSelectionInfo.visible = true
  }

  // show exitInfo
  def exitInfo(actionEvent: ActionEvent): Unit = {
    digimonSelectionInfo.visible = false
  }

  // switch to PlayerInput scene
  def backToInputName(actionEvent: ActionEvent){
    MainApp.showPlayerNameInput()
  }

  // Selects agumon
  def selectAgumon(actionEvent: ActionEvent): Unit = {
    if(numOfDigimonSelected == 0){
      var player1: Player = new Player(player1agumon,SharedMemory.player1Name)
      SharedMemory.playerList(0) = player1
      numOfDigimonSelected += 1
      turnToPickText.text = SharedMemory.player2Name +"'s Turn to Pick"}
    else if(numOfDigimonSelected == 1){
      var player2: Player = new Player(player2agumon,SharedMemory.player2Name)
      SharedMemory.playerList(1) = player2
      numOfDigimonSelected += 1
    }
    checkDigimonSelected()
  }

  // selects gabumon
  def selectGabumon(actionEvent: ActionEvent): Unit = {
    if (numOfDigimonSelected == 0) {
      var player1: Player = new Player(player1gabumon,SharedMemory.player1Name)
      SharedMemory.playerList(0) = player1
      numOfDigimonSelected += 1
      turnToPickText.text = SharedMemory.player2Name +"'s Turn to Pick"
    }
    else if (numOfDigimonSelected == 1) {
      var player2: Player = new Player(player2gabumon,SharedMemory.player2Name)
      SharedMemory.playerList(1) = player2
      numOfDigimonSelected += 1
    }
    checkDigimonSelected()
  }

  // select gotsumon
  def selectGotsumon(actionEvent: ActionEvent): Unit = {
    if (numOfDigimonSelected == 0) {
      var player1: Player = new Player(player1gotsumon,SharedMemory.player1Name)
      SharedMemory.playerList(0) = player1
      numOfDigimonSelected += 1
      turnToPickText.text = SharedMemory.player2Name +"'s Turn to Pick"
    }
    else if (numOfDigimonSelected == 1) {
      var player2: Player = new Player(player2gotsumon,SharedMemory.player2Name)
      SharedMemory.playerList(1) = player2
      numOfDigimonSelected += 1
    }
    checkDigimonSelected()
  }

  // selects biyomon
  def selectBiyomon(actionEvent: ActionEvent): Unit = {
    if (numOfDigimonSelected == 0) {
      var player1: Player = new Player(player1veemon,SharedMemory.player1Name)
      SharedMemory.playerList(0) = player1
      numOfDigimonSelected += 1
      turnToPickText.text = "Player 2's Turn to Pick"
    }
    else if (numOfDigimonSelected == 1) {
      var player2: Player = new Player(player2veemon,SharedMemory.player2Name)
      SharedMemory.playerList(1) = player2
      numOfDigimonSelected += 1
    }
    checkDigimonSelected()
  }

}
