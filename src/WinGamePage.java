package src;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.Background;

import java.awt.*;

public class WinGamePage {

	public WinGamePage(Stage primaryStage, Player p1) {
		VBox root = new VBox();
		Scene scene = new Scene(root, 800, 800);

		Button playAgain = new Button("End Credits");
		playAgain.setOnMouseClicked((MouseEvent m) -> {
			EndCreditsPage endCreditsPage = new EndCreditsPage(primaryStage);
		});


		root.getChildren().addAll(playAgain);
		primaryStage.setTitle("You Win the Game!");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
