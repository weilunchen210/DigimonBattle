package DigimonBattle.model.Action

class RockThrow extends Action with Skill{
  val skillName: String = "Rock Throw"
  var power: Int = 3
  val elementalType: String = "earth"
  val image: String = "/DigimonBattle/image/rockthrow.png"

}
