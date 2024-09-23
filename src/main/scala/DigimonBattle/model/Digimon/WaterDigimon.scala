package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{Bubble, Waterfall}

// trait for digimon
trait WaterDigimon {
  val elementalType: String = "water"
  val skill: Bubble
  val ultimate: Waterfall
}
