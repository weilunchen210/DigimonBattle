package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{LightningStrike, Thunder}

// lightning digimon
trait LightningDigimon {
  val elementalType: String = "lightning"
  val skill: LightningStrike
  val ultimate: Thunder

}
