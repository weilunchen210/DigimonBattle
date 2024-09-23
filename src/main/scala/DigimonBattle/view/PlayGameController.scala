package DigimonBattle.view

import DigimonBattle.MainApp
import DigimonBattle.model.Digimon.Digimon
import DigimonBattle.model.Game.Game
import DigimonBattle.model.Action.Action
import DigimonBattle.model.Player.Player
import DigimonBattle.model.SharedMemory
import scalafx.animation.{PauseTransition, TranslateTransition}
import scalafx.event.ActionEvent
import scalafx.scene.Group
import scalafx.scene.control.{Button, ProgressBar}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text
import scalafx.util.Duration
import scalafxml.core.macros.sfxml


@sfxml
class PlayGameController (private val player1Digimon: ImageView,  private val player2Digimon: ImageView,
                          private val player1HpBar: ProgressBar, private val player2HpBar:ProgressBar,
                          private val player1SkillPoint1: ProgressBar, private val player1SkillPoint2: ProgressBar,
                          private val player1SkillPoint3: ProgressBar, private val player2SkillPoint1: ProgressBar,
                          private val player2SkillPoint2: ProgressBar, private val player2SkillPoint3: ProgressBar,
                          private var player1Buttons: HBox, private var player2Buttons: HBox, private var player1ChatBubble: Group,
                          private var digimon1Sound: Text, private var player2ChatBubble: Group,
                          private var digimon2Sound: Text, private var player1Button1: Button,
                          private var player1Button2: Button, private var player1Button3: Button,
                          private var player1Button4: Button, private var player2Button1: Button,
                          private var player2Button2: Button, private var player2Button3: Button,
                          private var player2Button4: Button, private var action1: ImageView,
                          private var action2:ImageView, private var textAction: Text, private var textAction2:Text,
                          private var hpText1: Text, private var hpText2: Text, private var action3: ImageView,
                          private var action4: ImageView, private var skillError: Text, private val digimonInfo: Group,
                          private val evolveError: Text){


  var game: Game = new Game(SharedMemory.playerList(0),SharedMemory.playerList(1))

  // Make the skill point bar
  val player1SkillPointBar: Array[ProgressBar] = Array(player1SkillPoint1, player1SkillPoint2, player1SkillPoint3)
  val player2SkillPointBar: Array[ProgressBar] = Array(player2SkillPoint1, player2SkillPoint2, player2SkillPoint3)
  for (i <- 0 until 3) {
    player1SkillPointBar(i).progress = 1.0
    player2SkillPointBar(i).progress = 1.0
  }

  // assign value to the progress bar based on Digimon's hp/maxHP
  player1HpBar.progress = game.player1.playerDigimon.HP / game.player1.playerDigimon.maxHP
  player2HpBar.progress = game.player2.playerDigimon.HP / game.player2.playerDigimon.maxHP

  // Assign image to the Imageview
  player1Digimon.image = new Image(getClass.getResourceAsStream(game.player1.playerDigimon.image))
  player2Digimon.image = new Image(getClass.getResourceAsStream(game.player2.playerDigimon.image))

  // Assign the digimon sound's text to the text
  digimon1Sound.text = game.player1.playerDigimon.digimonSound()
  digimon2Sound.text = game.player2.playerDigimon.digimonSound()

  // Change the button names based on the digimon's actionList
  player1Button1.text = game.player1.playerDigimon.actionList(0).skillName
  player1Button2.text = game.player1.playerDigimon.actionList(1).skillName
  player1Button3.text = game.player1.playerDigimon.actionList(2).skillName

  // Change the button names based on the digimon's actionList
  player2Button1.text = game.player2.playerDigimon.actionList(0).skillName
  player2Button2.text = game.player2.playerDigimon.actionList(1).skillName
  player2Button3.text = game.player2.playerDigimon.actionList(2).skillName

  // show the digimon's HP/maxHP beside HP bar
  hpText1.text = (game.player1.playerDigimon.HP + "/" + game.player1.playerDigimon.maxHP)
  hpText2.text = (game.player2.playerDigimon.HP + "/" + game.player2.playerDigimon.maxHP)


  // switch chat Bubble of the digimon
  def digimonSwitchChatBubble(): Unit = {
    if(game.currentTurn == game.player1){
      player1ChatBubble.visible = false
      val pause = new PauseTransition(Duration(2200))
      pause.setOnFinished { _ =>
        player2ChatBubble.visible = true
      }
      pause.play()
    }
    else if(game.currentTurn == game.player2){
      player2ChatBubble.visible = false
      val pause = new PauseTransition(Duration(2200))
      pause.setOnFinished { _ =>
        player1ChatBubble.visible = true
      }
      pause.play()
    }
  }

  // update the skill point bar
  def updateSkillPoint(player:Player): Unit = {
    var skillPointBar = new Array[ProgressBar](3)
    if(game.currentTurn == game.player1) {
      skillPointBar = player1SkillPointBar
    }
    else if(game.currentTurn == game.player2) {
      skillPointBar = player2SkillPointBar
    }
    for (i <- 0 until 3) {
      skillPointBar(i).progress = 0.0
    }
    for (i <- 0 until game.currentDigimon().skillPoint) {
      skillPointBar(i).progress = 1.0
    }
  }

  // check whether game is over. If not then hide current turn player's button and show next turn player's buttons
  def checkGameOver(): Unit = {
    game.oppositeDigimon.checkIsDead()
    game.checkGameOver()
    if(game.gameOver == true){
      SharedMemory.winner = game.winner.name
      MainApp.showGameOver()
    }
    else if(game.gameOver == false){
      val pause = new PauseTransition(Duration(2200))
      if (game.currentTurn == game.player1) {
        player1Buttons.visible = false
        pause.setOnFinished{ _ =>
        player2Buttons.visible = true}
        pause.play()
      }
      else if (game.currentTurn == game.player2) {
        player2Buttons.visible = false
        pause.setOnFinished{ _ =>
        player1Buttons.visible = true}
        pause.play()
      }
      game.switchPlayerTurn()
    }
  }


  // update hp bar
  def updateHP(): Unit = {
    if(game.oppositePlayer() == game.player2) {
      player2HpBar.progress = game.player2.playerDigimon.HP / game.player2.playerDigimon.maxHP
      hpText2.text = (game.player2.playerDigimon.HP + "/" + game.player2.playerDigimon.maxHP)
    }
    else if(game.oppositePlayer() == game.player1){
      player1HpBar.progress = game.player1.playerDigimon.HP / game.player1.playerDigimon.maxHP
      hpText1.text = (game.player1.playerDigimon.HP + "/" + game.player1.playerDigimon.maxHP)
    }
  }

  // update hp bar after evolution
  def updateEvolveHP(): Unit = {
    if (game.currentTurn == game.player2) {
      player2HpBar.progress = game.player2.playerDigimon.HP / game.player2.playerDigimon.maxHP
      hpText2.text = (game.player2.playerDigimon.HP + "/" + game.player2.playerDigimon.maxHP)
    }
    else if (game.currentTurn == game.player1) {
      player1HpBar.progress = game.player1.playerDigimon.HP / game.player1.playerDigimon.maxHP
      hpText1.text = (game.player1.playerDigimon.HP + "/" + game.player1.playerDigimon.maxHP)
    }
  }

  // use performAction() and return based on whether the action is used successfully
  def performedAction(i: Int): Boolean = {
    game.currentTurn.playerDigimon.performAction(game.currentDigimon().actionList(i), game.oppositeDigimon())
  }

  // Switch to Game Over scene and assign winner name to be displayed in Game Over scene
  def surrender(): Unit = {
    game.surrender()
    if(game.winner == game.player1){
      SharedMemory.winner = game.winner.name
    }
    else if(game.winner == game.player2){
      SharedMemory.winner = game.winner.name
    }
    MainApp.showGameOver()
  }

    // Animation for when Animation for when the player 1 digimon gets hit.
    // Waits 0.5 seconds before doing the transition action
    // reverseTransition then reverses it back to the original position after the transition
  def digimon1GotHitAnimation(image: ImageView): Unit = {
    val transition = new TranslateTransition(Duration(500), image)
    val pause = new PauseTransition(Duration(1000))
    val reverseTransition = new TranslateTransition(Duration(500), image)
    pause.setOnFinished { _ =>
      transition.toX = image.translateX() - 75
      transition.setOnFinished { _ =>
        reverseTransition.toX = 0
        reverseTransition.play()
      }
      transition.play()
    }
    pause.play()
  }

  // Animation for when the player 2 digimon gets hit.
  // Waits 0.5 seconds before doing the transition action
  // reverseTransition then reverses it back to the original positon after the transition
  def digimon2GotHitAnimation(image:ImageView): Unit = {
    val transition = new TranslateTransition(Duration(500), image)
    val pause = new PauseTransition(Duration(1000))
    val reverseTransition = new TranslateTransition(Duration(500), image)
    pause.setOnFinished{ _ =>
    transition.toX = image.translateX() + 75
    transition.setOnFinished { _ =>
      reverseTransition.toX = 0
      reverseTransition.play()
    }
    transition.play()
    }
    pause.play()
  }

  // Assign image to action 1 and move it to right by 400
  // reverseTransition then moves it back to original position and invisible it
  def actionRightAnimation(action:Action): Unit = {
    action1.image = new Image(getClass.getResourceAsStream(action.image))
    action1.visible = true
    val transition = new TranslateTransition(Duration(1000),action1)
    val reverseTransition = new TranslateTransition(Duration(100),action1)
    transition.toX = action1.translateX() + 400
    transition.setOnFinished{ _ =>
      action1.visible = false
      reverseTransition.toX = 0
      reverseTransition.play()
    }
    transition.play()
  }

  // Accepts ImageView and moves the imageview to the right by 400
  // Used to move the digimon when they use scratch
  def actionRightAnimation(image:ImageView): Unit = {
    val transition = new TranslateTransition(Duration(1000), image)
    val reverseTransition = new TranslateTransition(Duration(1000), image)
    transition.toX = image.translateX() + 400
    transition.setOnFinished { _ =>
      reverseTransition.toX = 0
      reverseTransition.play()
    }
    transition.play()
  }

  // Assign image to action 1 and move it to left by 400
  // reverseTransition then moves it back to original position and invisible it
  def actionLeftAnimation(action: Action): Unit = {
    action2.image = new Image(getClass.getResourceAsStream(action.image))
    action2.visible = true
    val transition = new TranslateTransition(Duration(1000), action2)
    val reverseTransition = new TranslateTransition(Duration(100), action2)
    transition.toX = action2.translateX() - 400
    transition.setOnFinished { _ =>
      action2.visible = false
      reverseTransition.toX = 0
      reverseTransition.play()
    }
    transition.play()
  }

  // Accepts ImageView and moves the imageView to the left by 400
  // Used to move the digimon when they use scratch
  def actionLeftAnimation(image: ImageView): Unit = {
    val transition = new TranslateTransition(Duration(1000), image)
    val reverseTransition = new TranslateTransition(Duration(1000), image)
    transition.toX = image.translateX() - 400
    transition.setOnFinished { _ =>
      reverseTransition.toX = 0
      reverseTransition.play()
    }
    transition.play()
  }

  // Assign image to action 4 and move it to down by 150
  // reverseTransition then moves it back to original position and invisible it
  def actionDropAnimationRight(move:Action): Unit = {
    action4.image = new Image(getClass.getResourceAsStream(move.image))
    action4.visible = true
    val transition = new TranslateTransition(Duration(1000), action4)
    val reverseTransition = new TranslateTransition(Duration(100), action4)
    transition.toY = action4.translateY() + 150
    transition.setOnFinished { _ =>
      action4.visible = false
      reverseTransition.toY = 0
      reverseTransition.play()
    }
    transition.play()
  }

  // Assign image to action 3 and move it to down by 400
  // reverseTransition then moves it back to original position and invisible it
  def actionDropAnimationLeft(move: Action): Unit = {
    action3.image = new Image(getClass.getResourceAsStream(move.image))
    action3.visible = true
    val transition = new TranslateTransition(Duration(1000), action3)
    val reverseTransition = new TranslateTransition(Duration(100), action3)
    transition.toY = action3.translateY() + 150
    transition.setOnFinished { _ =>
      action3.visible = false
      reverseTransition.toY = 0
      reverseTransition.play()
    }
    transition.play()
  }

  // display based on whether the digimon is weak to the type
  def displayEffectiveness(move: Action, enemy: Digimon): String = {
    var elementalWeakness = game.currentDigimon().checkElementalWeakness(move, enemy)
    if (elementalWeakness == "weak") {
      "Lesser damage dealt due to type disadvantage"
    }
    else if (elementalWeakness == "strong") {
      "More damage dealt due to type advantage"
    }
    else {
      ""
    }
  }

  // display skill Error for 1 second
  def showSkillError(): Unit = {
    val pause = new PauseTransition(Duration(1000))
    skillError.visible = true
    pause.setOnFinished{_ =>
      skillError.visible = false
    }
    pause.play()
  }

  // display eveolve error for 1 second
  def showEvolveError(): Unit = {
    val pause = new PauseTransition(Duration(1000))
    evolveError.visible = true
    pause.setOnFinished { _ =>
      evolveError.visible = false
    }
    pause.play()
  }

  // display digimon Infos
  def showInfo(): Unit = {
    digimonInfo.visible = true
  }

  // display digimon Infos
  def exitInfo(): Unit = {
    digimonInfo.visible = false
  }

  def digimon1Action1(actionEvent: ActionEvent): Unit = {
    actionRightAnimation(player1Digimon)
    digimon2GotHitAnimation(player2Digimon)
    performedAction(0)
    updateHP()
    updateSkillPoint(game.currentTurn)
    textAction.text = game.player1.playerDigimon.name + " used " + game.player1.playerDigimon.actionList(0).skillName
    textAction2.text = ""
    digimonSwitchChatBubble()
    checkGameOver()
  }

  def digimon1Action2(actionEvent: ActionEvent): Unit = {
    if(performedAction(1) == true){
      actionRightAnimation(game.player1.playerDigimon.actionList(1))
      digimon2GotHitAnimation(player2Digimon)
      updateHP()
      updateSkillPoint(game.currentTurn)
      digimonSwitchChatBubble()
      textAction.text = game.player1.playerDigimon.name + " used " + game.player1.playerDigimon.actionList(1).skillName
      textAction2.text = displayEffectiveness(game.player1.playerDigimon.actionList(1),game.oppositeDigimon())
      checkGameOver()
    }
    else{
      showSkillError()
    }
  }

  def digimon1Action3(actionEvent: ActionEvent): Unit = {
    if(performedAction(2) == true){
      actionDropAnimationRight(game.player1.playerDigimon.actionList(2))
      digimon2GotHitAnimation(player2Digimon)
      updateHP()
      updateSkillPoint(game.currentTurn)
      digimonSwitchChatBubble()
      textAction.text = game.player1.playerDigimon.name + " used " + game.player1.playerDigimon.actionList(2).skillName
      textAction2.text = displayEffectiveness(game.player1.playerDigimon.actionList(2), game.oppositeDigimon())
      checkGameOver()}
    else {
      showSkillError()
    }
  }

  def digimon1Action4(actionEvent: ActionEvent): Unit = {
    if (game.currentTurn.playerDigimon.checkEvolveRequirements() == true) {
      textAction.text = game.player1.playerDigimon.name + " evolved into " + game.checkDigimonAdultForm(game.currentTurn.playerDigimon).name + "!"
      game.digimonEvolve()
      textAction2.text = ""
      updateSkillPoint(game.currentTurn)
      updateEvolveHP()
      digimon1Sound.text = game.player1.playerDigimon.digimonSound()
      player1Digimon.image = new Image(getClass.getResourceAsStream(game.player1.playerDigimon.image))
      player1Button4.visible =false
    }
    else{
      showEvolveError()
    }

  }

  def digimon2Action1(actionEvent: ActionEvent): Unit = {
    actionLeftAnimation(player2Digimon)
    digimon1GotHitAnimation(player1Digimon)
    performedAction(0)
    updateHP()
    digimonSwitchChatBubble()
    textAction.text = game.player2.playerDigimon.name + " used " + game.player2.playerDigimon.actionList(0).skillName
    textAction2.text = ""
    updateSkillPoint(game.currentTurn)
    checkGameOver()
  }

  def digimon2Action2(actionEvent: ActionEvent): Unit = {
    if(performedAction(1) == true){
      actionLeftAnimation(game.player2.playerDigimon.actionList(1))
      digimon1GotHitAnimation(player1Digimon)
      updateHP()
      updateSkillPoint(game.currentTurn)
      digimonSwitchChatBubble()
      textAction.text = game.player2.playerDigimon.name + " used " + game.player2.playerDigimon.actionList(1).skillName
      textAction2.text = displayEffectiveness(game.player2.playerDigimon.actionList(1), game.oppositeDigimon())
      checkGameOver()}
    else {
      showSkillError()
    }
  }

  def digimon2Action3(actionEvent: ActionEvent): Unit = {
    if(performedAction(2) == true){
      actionDropAnimationLeft(game.player2.playerDigimon.actionList(2))
      digimon1GotHitAnimation(player1Digimon)
      updateHP()
      updateSkillPoint(game.currentTurn)
      digimonSwitchChatBubble()
      textAction.text = game.player2.playerDigimon.name + " used " + game.player2.playerDigimon.actionList(2).skillName
      textAction2.text = displayEffectiveness(game.player2.playerDigimon.actionList(2), game.oppositeDigimon())
      checkGameOver()}
    else {
      showSkillError()
    }
  }

  def digimon2Action4(actionEvent: ActionEvent): Unit = {
    if(game.currentTurn.playerDigimon.checkEvolveRequirements() == true){
      textAction.text = game.player2.playerDigimon.name + " evolved into " + game.checkDigimonAdultForm(game.currentTurn.playerDigimon).name+ "!"
      game.digimonEvolve()
      digimon2Sound.text = game.player2.playerDigimon.digimonSound()
      textAction2.text = ""
      updateSkillPoint(game.currentTurn)
      updateEvolveHP()
      player2Digimon.image = new Image(getClass.getResourceAsStream(game.player2.playerDigimon.image))
      player2Button4.visible =false
    }
    else{
      showEvolveError()
    }
  }
}




