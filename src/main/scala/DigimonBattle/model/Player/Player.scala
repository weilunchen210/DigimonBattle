package DigimonBattle.model.Player

import DigimonBattle.model.Digimon.Digimon



class Player(var digimon: Digimon, var name: String){
  var playerDigimon: Digimon = digimon
  var playerName: String = name
}

