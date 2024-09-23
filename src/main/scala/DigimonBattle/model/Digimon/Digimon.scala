package DigimonBattle.model.Digimon

import DigimonBattle.model.Action.{ Action, Scratch, Waterfall}

// Digimon parent class
abstract class Digimon {
  val name: String
  val maxHP: Double
  var HP: Double
  var attack: Int
  var skillPoint: Int
  var maxSkillPoint: Int
  val elementalType: String
  val basicAttack: Scratch = new Scratch
  val skill: Action
  val ultimate: Action
  val actionList: Array[Action] = Array(basicAttack, skill, ultimate)
  var isDead: Boolean = false
  val image: String
  val adultForm: String

  // method to calculate the Damage and minus it off the receiver's hp
  def receiveDamage(receiver: Digimon, power: Int): Unit = {
    val damage: Int = attack*power
    receiver.HP = receiver.HP - damage
    if(receiver.HP < 0){
      receiver.HP = 0
    }
  }

  // check for weakness. 0 = neutral ,1 = action weak against digimon, 2 = action strong against digimon
  def checkElementalWeakness(action: Action, enemy: Digimon): String = {
    var elementalWeakness: String = ""
    (action.elementalType, enemy.elementalType) match {
      case ("fire", "water") => elementalWeakness = "weak"
      case ("fire", "earth") => elementalWeakness = "strong"
      case ("water", "fire") => elementalWeakness = "strong"
      case ("water", "lightning") => elementalWeakness = "weak"
      case ("earth", "water") => elementalWeakness = "strong"
      case ("earth", "fire") => elementalWeakness= "weak"
      case ("lightning", "water") => elementalWeakness = "strong"
      case ("lightning", "fire") => elementalWeakness = "weak"
      case _ => elementalWeakness = "normal"
    }
    elementalWeakness
  }

  // increase damage based on whether action is strong against digimon
  def effectivenessDamage(elementalWeakness: String, move: Action): Unit = {
    elementalWeakness match{
      case "weak" => move.power = move.power/2
      case "strong" => move.power = move.power*2
      case _ => move.power
    }
  }

  // check if they have the required skill point. If the digimon has then checkelementalweakness() will run
  // and damage will be dealth to the enemy with receiveDamage()
  def performAction(move: Action, enemy:Digimon): Boolean = {
    if(skillPoint < move.skillPointNeeded){
      false
    }
    else{
      val originalSkillPower = move.power
      skillPoint = skillPoint - move.skillPointNeeded
      effectivenessDamage(checkElementalWeakness(move,enemy),move)
      receiveDamage(enemy,move.power)
      move.power = originalSkillPower
      if(skillPoint > 3){
        skillPoint = 3
      }
      true
    }
  }

  // Assign true or alse based on whether the digimon is dead or alive
  def checkIsDead(): Unit = {
    if(HP <= 0){
      isDead = true
    }
    else{
      isDead = false
    }
  }

  // digimon sound in String
  def digimonSound(): String ={
    "digimon's sound"
  }

  // check whether digimon the requirements to evolve
  // return true or false based on whether they fulfil the requirement
  def checkEvolveRequirements(): Boolean = {
    if(HP <= (70.0/100.0)*maxHP && skillPoint == 3){
      true
    }
    else{
      false
    }
  }

}
