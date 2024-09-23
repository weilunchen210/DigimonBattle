package DigimonBattle.model

import DigimonBattle.model.Player.Player

// SharedMemory for controller to access the input from other scenes
object SharedMemory {
  var playerList: Array[Player] = new Array(2)
  var player1Name: String = ""
  var player2Name: String = ""
  var winner: String=""
}
