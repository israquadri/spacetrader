package src;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class CharacterPage {

	public CharacterPage(Stage primaryStage, Player p1) {
		
		// SCENE 3 BACKING STRUCTURE SET UP
		VBox vb3 = new VBox(20.0);
		Scene scene3 = new Scene(vb3, 800, 800);
		scene3.getStylesheets().add("https://fonts.googleapis.com/css?family=Press+Start+2P&display=swap");
		vb3.setStyle("-fx-border-color: lightcoral; -fx-border-width: 10px");
		BackgroundImage myBI = new BackgroundImage(new Image("galaxy.jpg", 800,
				800, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		vb3.setBackground(new Background(myBI));

		Button toScene4 = new Button("Into the Universe!");
		toScene4.setTextFill(Color.WHITE);
		toScene4.setStyle("-fx-font-size: 20px; -fx-background-color: midnightblue;");

		// INTRO MUSIC FOR STOPPING DURING BUTTON PRESS
		Media spaceTraderIntroSong = new Media(new File("SpaceTraderIntroSong.m4a").toURI().toString());
		MediaPlayer introsongplayer = new MediaPlayer(spaceTraderIntroSong);

		HBox bt2 = new HBox();
//        bt2.getChildren().add(backToScene2);
//        bt2.setAlignment(Pos.BASELINE_LEFT);

		// ADDING CHARACTER SHEET SCREEN TEXT NODES
		Text yourCharacter = new Text("WELCOME,\n" + p1.getName());
		yourCharacter.setFill(Color.INDIANRED);
		yourCharacter.setStyle("-fx-background-color: black; -fx-font-size: 60px;"
				+ " -fx-font-family: 'Press Start 2P', cursive;");

		Text yourNameIs = new Text("Your name is: " + p1.getName());
		yourNameIs.setFill(Color.WHITE);
		yourNameIs.setStyle("-fx-font-size: 20px; -fx-background-color: purple;"
				+ " -fx-font-family: 'Press Start 2P', cursive;");

		Text yourTraits = new Text("Your points for Pilot: " + p1.getTrait1Val()
				+ "\nYour points for Fighter: " + p1.getTrait2Val()
				+ "\nYour points for Merchant: " + p1.getTrait3Val()
				+ "\nYour points for Engineer: " + p1.getTrait4Val());
		yourTraits.setFill(Color.WHITE);
		yourTraits.setStyle("-fx-font-size: 20px; -fx-background-color: purple;"
				+ " -fx-font-family: 'Press Start 2P', cursive;");

		Text yourDiff = new Text("Based on your difficulty level, you\nhave "
				+ p1.getCredits() + " credits");
		yourDiff.setFill(Color.WHITE);
		yourDiff.setStyle("-fx-font-size: 20px; -fx-background-color: purple;"
				+ " -fx-font-family: 'Press Start 2P', cursive;");
		vb3.getChildren().addAll(yourCharacter, bt2, yourNameIs, yourTraits, yourDiff, toScene4);

		toScene4.setOnMouseClicked(mouseEvent -> {
			src.RegionPage regionPage = new RegionPage(primaryStage);
		});

		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.CORAL);
		shadow.setWidth(1.5);
		Button backToScene2 = new Button("Back to Character\nConfiguration");
		backToScene2.setTextFill(Color.WHITE);
		backToScene2.setStyle("-fx-background-color: black; -fx-font-size: 20px;"
				+ " -fx-font-family: 'Press Start 2P', cursive;");

		//DROP SHADOW EFFECT
		backToScene2.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						backToScene2.setEffect(shadow);
					}
				});
		backToScene2.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						backToScene2.setEffect(null);
					}
				});

/*	//BACK TO SCENE 2 BUTTON
        backToScene2.setOnMouseClicked((mouseEvent -> {
		//Stop intro song and start character finish song
		//introsongplayer.play();
		//soundplyr.stop();
		//primaryStage.setScene(configPage);
		primaryStage.setTitle("Welcome user!");
		primaryStage.show();
	}));*/
		primaryStage.setTitle("Your character");
		primaryStage.setScene(scene3);
		primaryStage.show();
	}
}
