package DigimonBattle.model.Digimon

class Garurumon extends Gabumon with AdultForm{
  override val name: String = "garurumon"
  override val maxHP: Double = 150.0
  override val image: String = "/DigimonBattle/image/garurumon.png"
  attack = 7

  // override to replace with garurumon's sound
  override def digimonSound(): String = {
    "ROAR ROAR. \nAwaiting player's command"
  }

}

