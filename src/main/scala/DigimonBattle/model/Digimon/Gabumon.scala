package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{Bubble, FireBall, Action, Scratch, Waterfall}

// class for gabumon
class Gabumon extends Digimon with WaterDigimon{
  val name: String = "gabumon"
  val maxHP: Double = 125.0
  var HP: Double = 125.0
  var attack: Int = 5
  var skillPoint: Int = 3
  var maxSkillPoint: Int = 3
  val skill: Bubble = new Bubble
  val ultimate: Waterfall = new Waterfall
  override val actionList: Array[Action] = Array(basicAttack, skill, ultimate)
  val image: String = "/DigimonBattle/image/gabumon.png"
  val adultForm:String = "garurumon"

  // override to replace with gabumon's sound
  override def digimonSound(): String = {
    "Gabu Gabu. \nAwaiting player's command"
  }



}
