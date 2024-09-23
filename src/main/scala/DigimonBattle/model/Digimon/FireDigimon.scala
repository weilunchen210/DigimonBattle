package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{FireBall, FlameDrop}

// trait for fire type digimon
trait FireDigimon {
  // assign fire type
  val elementalType: String ="fire"
  val skill: FireBall
  val ultimate: FlameDrop
}
