package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{Action, MudDrop, RockThrow}

class Gotsumon extends Digimon with EarthDigimon{
  val name: String = "gotsumon"
  val maxHP: Double = 150.0
  var HP: Double = 150.0
  var attack: Int = 4
  var skillPoint: Int = 3
  var maxSkillPoint: Int = 3
  val skill: RockThrow= new RockThrow
  val ultimate: MudDrop = new MudDrop
  override val actionList: Array[Action] = Array(basicAttack, skill, ultimate)
  val image:String = "/DigimonBattle/image/gotsumon.png"
  val adultForm:String = "gotsumon"

  // override to replace with gotsumon's sound
  override def digimonSound(): String = {
    "Gotsu Gotsu. \nAwaiting player's command"
  }

}
