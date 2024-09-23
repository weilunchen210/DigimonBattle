package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{FireBall, FlameDrop, Action, Scratch, Waterfall}

class Agumon extends Digimon with FireDigimon{
  val name: String = "agumon"
  val maxHP: Double = 125.0
  var HP: Double = 125.0
  var attack: Int = 5
  var skillPoint: Int = 3
  var maxSkillPoint: Int = 3
  val skill: FireBall = new FireBall
  val ultimate: FlameDrop = new FlameDrop
  override val actionList: Array[Action] = Array(basicAttack, skill, ultimate)
  val image: String = "/DigimonBattle/image/agumon.png"
  val adultForm:String = "greymon"

  // override to replace with agumon's sound
  override def digimonSound(): String = {
    "Agu Agu. \nAwaiting player's command"
  }

}


