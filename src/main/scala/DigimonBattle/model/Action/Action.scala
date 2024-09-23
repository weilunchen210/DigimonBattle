package DigimonBattle.model.Action

// parent class for action
abstract class Action {
  val skillName: String
  var power: Int
  val elementalType: String
  val image: String
  val skillPointNeeded: Int
}
