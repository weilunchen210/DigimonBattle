package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{LightningStrike, Action, Thunder}

class Veemon extends Digimon with LightningDigimon{
  val name: String = "veemon"
  val maxHP: Double = 100.0
  var HP: Double = 100.0
  var attack: Int = 5
  var skillPoint: Int = 3
  var maxSkillPoint: Int = 3
  val skill: LightningStrike = new LightningStrike
  val ultimate: Thunder = new Thunder
  override val actionList: Array[Action] = Array(basicAttack, skill, ultimate)
  val image: String = "/DigimonBattle/image/veemon.png"
  val adultForm: String = "thundermon"

  // override to replace with veemon's sound
  override def digimonSound(): String = {
    "Vee Vee. \nAwaiting player's command"
  }


}
