package DigimonBattle.model.Digimon

class Thundermon extends Veemon with AdultForm{
  override val name: String = "thundermon"
  override val maxHP: Double = 150.0
  override val image: String = "/DigimonBattle/image/thundermon.png"
  attack = 7
  override val adultForm = null

  // override to replace with thundermon's sound
  override def digimonSound(): String = {
    "ROAR ROAR. \nAwaiting player's command"
  }


}

