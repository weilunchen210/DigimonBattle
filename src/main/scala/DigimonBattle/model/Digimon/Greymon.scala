package DigimonBattle.model.Digimon

class Greymon extends Agumon with AdultForm {
  override val name: String = "greymon"
  override val maxHP: Double = 150.0
  override val image: String = "/DigimonBattle/image/greymon.png"
  attack = 7
  HP = 0
  override val adultForm = null

  // override to replace with greymon's sound
  override def digimonSound(): String = {
    "ROAR ROAR. \nAwaiting player's command"
  }
}

