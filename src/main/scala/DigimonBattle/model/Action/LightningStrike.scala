package DigimonBattle.model.Action

class LightningStrike extends Action with Skill{
  val skillName: String = "Lightning Strike"
  var power: Int = 3
  val elementalType: String = "lightning"
  val image: String = "/DigimonBattle/image/lightningstrike.png"

}
