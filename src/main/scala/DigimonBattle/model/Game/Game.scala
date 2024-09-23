package DigimonBattle.model.Game

import DigimonBattle.model.Digimon.{Agumon, Digimon, Gabumon, Garurumon, Greymon, Onixmon, Thundermon}
import DigimonBattle.model.Player.Player

// need 2 parameter to initialized
class Game(p1:Player, p2: Player){
  var player1: Player = p1
  var player2: Player = p2
  var currentTurn: Player = player1
  var winner: Player = null
  var gameOver: Boolean = false

  // switch player turn
  def switchPlayerTurn(): Unit = {
    if(currentTurn == player1){
      currentTurn = player2
    }
    else if(currentTurn == player2){
      currentTurn = player1
    }
  }

  //return the opposite digimon
  def oppositeDigimon(): Digimon = {
    if(currentTurn == player1){
      player2.playerDigimon
    }
    else{
      player1.playerDigimon
    }
  }

  // return opposite Player
  def oppositePlayer(): Player = {
    if (currentTurn == player1) {
      player2
    }
    else {
      player1
    }
  }
  // return currentDigimon
  def currentDigimon(): Digimon = {
    if (currentTurn == player1) {
      player1.playerDigimon
    }
    else {
      player2.playerDigimon
    }
  }

  // check for game Over based on whether the opposite digimon is dead
  def checkGameOver(): Unit = {
    if(oppositePlayer().playerDigimon.isDead == true){
      winner = currentTurn
      gameOver = true
    }
    else{
      gameOver = false
    }
  }

  // surrender. make the opposite player the winner and assign true to gameOver
  def surrender(): Unit = {
    winner = oppositePlayer()
    gameOver = true
  }

  // evolve the digimon by switching the active Digimon to the adult form
  // Assign the current Hp to the previous evolution HP and make the skill Point to 0
  def digimonEvolve(): Unit = {
    val previousEvolutionHP: Double = currentTurn.playerDigimon.HP
    currentTurn.playerDigimon = checkDigimonAdultForm(currentTurn.playerDigimon)
    currentTurn.playerDigimon.HP = previousEvolutionHP + 25.0
    currentTurn.playerDigimon.skillPoint = 0
  }

  // Makes the digimon's adult form's object based on which digimon is passed through
  def checkDigimonAdultForm(digimon: Digimon): Digimon = {
    val digimonAdultForm: Digimon = digimon.adultForm match {
      case "greymon" => new Greymon
      case "garurumon" => new Garurumon
      case "onixmon" => new Onixmon
      case "thundermon" => new Thundermon
    }
    digimonAdultForm
  }


}
