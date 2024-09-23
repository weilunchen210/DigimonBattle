package DigimonBattle.model.Action

class FireBall extends Action with Skill{
  val skillName: String = "Fire Ball"
  var power: Int = 3
  val elementalType: String = "fire"
  val image: String = "/DigimonBattle/image/fireball.png"
}
