package DigimonBattle.model.Digimon

class Onixmon extends Gotsumon with AdultForm{
  override val name: String = "onixmon"
  override val maxHP: Double = 150.0
  override val image: String = "/DigimonBattle/image/onixmon.png"
  attack = 7
  override val adultForm = null


  // override to replace with onixmon's sound
  override def digimonSound(): String = {
    "Onix Onix. \nAwaiting player's command"
  }

}

