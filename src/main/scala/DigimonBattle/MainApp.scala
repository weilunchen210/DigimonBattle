package DigimonBattle

import javafx.{scene => jfks}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}


object MainApp extends JFXApp {
  val rootResource = getClass.getResource("view/MainMenu.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfks.layout.AnchorPane]


  stage = new PrimaryStage {
    title = "DigimonBattle"
    scene = new Scene {
      root = roots
    }
  }


  def showMain(): Unit = {
    val rootResource = getClass.getResource("view/MainMenu.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfks.layout.AnchorPane]
    val mainMenuScene = new Scene(roots)
    stage.scene = mainMenuScene
  }

  def showInstructions(): Unit = {
    val rootResource = getClass.getResource("view/Instructions.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfks.layout.AnchorPane]
    val instructionScene = new Scene(roots)
    stage.scene = instructionScene
  }

  def showPlay(): Unit = {
    val rootResource = getClass.getResource("view/PlayGame.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfks.layout.AnchorPane]
    val playScene = new Scene(roots)
    stage.scene = playScene
  }

  def showDigimonSelection(): Unit = {
    val rootResource = getClass.getResource("view/DigimonSelection.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfks.layout.AnchorPane]
    val selectionScene = new Scene(roots)
    stage.scene = selectionScene
  }

  def showGameOver(): Unit = {
    val rootResource = getClass.getResource("view/GameOver.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfks.layout.AnchorPane]
    val gameOverScene = new Scene(roots)
    stage.scene = gameOverScene
  }

  def showPlayerNameInput(): Unit = {
    val rootResource = getClass.getResource("view/PlayerNameInput.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfks.layout.AnchorPane]
    val playerNameInputScene = new Scene(roots)
    stage.scene = playerNameInputScene
  }

}



