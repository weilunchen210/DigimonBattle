package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{MudDrop, RockThrow}

trait EarthDigimon {
  val elementalType: String = "earth"
  val skill: RockThrow
  val ultimate: MudDrop

}
