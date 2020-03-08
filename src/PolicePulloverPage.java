package src;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class PolicePulloverPage {

	public PolicePulloverPage(Stage primaryStage, Region[] regions, Player p1, Police police) {
		VBox root = new VBox();
		Scene scene = new Scene(root, 800, 800);
		BackgroundImage myBI = new BackgroundImage(new Image("starback.jpg", 800,
				800, true, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));

		//itemWanted.quantity is incorrect
		ArrayList<Item> itemsCopy = new ArrayList<>(p1.getSpaceShip().getInventory().keySet());
		//for testing
//		for(Item i : itemsCopy) {
//			System.out.println("item: " + i.getName());
//		}
		int index = new Random().nextInt(p1.getSpaceShip().getInventory().size());
		police.setItemWanted(itemsCopy.get(index));
		System.out.println("item wanted: " + police.getItemWanted());

		//System.out.println("item wanted: " + police.getItemWanted());


		ImageView policeShip = new ImageView(new Image("police.png"));
		policeShip.setFitHeight(300);
		policeShip.setFitWidth(300);
//		ImageView policeShip2 = new ImageView(new Image("police.png"));
//		policeShip.setFitHeight(200);
//		policeShip.setFitWidth(200);

		Path path = new Path();
		path.getElements().add (new MoveTo(800, 50));
		path.getElements().add (new HLineTo(-20));
		path.getElements().add (new MoveTo(800, 700));
		path.getElements().add (new HLineTo(-40));

//		Path path2 = new Path();
//		path2.getElements().add (new MoveTo(-40, 700));
//		path2.getElements().add (new HLineTo(800));
//		path2.getElements().add (new MoveTo(800, 700));
//		path2.getElements().add (new HLineTo(-40));

		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(8));
		pathTransition.setNode(policeShip);
		//pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setPath(path);
		pathTransition.setCycleCount(Animation.INDEFINITE);

//		PathTransition pathTransition2 = new PathTransition();
//		pathTransition2.setDuration(Duration.seconds(8));
//		pathTransition2.setNode(policeShip2);
//		//pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//		pathTransition2.setPath(path2);
//		pathTransition2.setCycleCount(Animation.INDEFINITE);

		pathTransition.play();
//		pathTransition2.play();

		// 3 options that are buttons presented to the user

		VBox optionBox = new VBox();
		optionBox.setPadding(new Insets(10, 10, 10, 10));
		optionBox.setSpacing(20.0);

		// Option 1
	/*
	Forfeit the items to the Police and continue to the desired destination.
	 */

		Button option1 = new Button("Comply with Police");
		option1.setOnMouseClicked(mouseEvent ->  {
			police.addToPoliceInventory(police.getItemWanted());
			p1.getSpaceShip().getInventory().remove(police.getItemWanted());
			RegionPage rp = new RegionPage(primaryStage, p1, p1.getDestination(), regions);
			Alert a1 = new Alert(Alert.AlertType.CONFIRMATION, "You complied, gave the police your " + police.getItemWanted().toString() + ", and get to continue to the next region.");
			a1.show();
		});
		option1.setAlignment(Pos.CENTER);
		option1.setTextFill(Color.WHITE);
		option1.setStyle("-fx-font-family: 'Press Start 2P', cursive;"
				+ " -fx-background-color: black; -fx-font-size: 17px;");


		/*
		 Try to flee back to the previous region. The success of fleeing is dependent on the
		player’s Pilot skill (higher Pilot level, higher chance of escape). If the player successfully
		flees back to the original region, they should still lose the fuel required to travel initially,
		but they keep all their items and they are safe. If the player fails to flee, the Police will
		confiscate the stolen items, damage the health value of the player's ship, and force the
		player to pay a fine for evasion. Then the player returns to the previous region.
		 */

		Button option2 = new Button("Attempt to flee");
		option2.setOnMouseClicked(mouseEvent -> {
			if (police.determineSuccess(p1.getPilotSkill())) {
				p1.getSpaceShip().setFuelAfterTravel(p1.getCurrentRegion().distanceBetween(p1.getDestination()));
				RegionPage proceed = new RegionPage(primaryStage, p1, p1.getDestination(), regions);
				Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have successfully fled from the police!");
				alert.show();
			} else {
				p1.getSpaceShip().getInventory().remove(police.getItemWanted());
				p1.getSpaceShip().setHealth(p1.getSpaceShip().getHealth() -1);
				p1.setCredits(p1.getCredits() - police.getFineDemanded());
				p1.getSpaceShip().setFuelAfterTravel(p1.getCurrentRegion().distanceBetween(p1.getDestination()));
				RegionPage proceed = new RegionPage(primaryStage, p1, p1.getDestination(), regions);
				Alert a2 = new Alert(Alert.AlertType.INFORMATION, "The police have decreased your ship health, confiscated the stolen items, and charged you a fine of " + police.getFineDemanded() + " credits.");
				a2.show();
			}
		});
		option2.setAlignment(Pos.CENTER);
		option2.setTextFill(Color.WHITE);
		option2.setStyle("-fx-font-family: 'Press Start 2P', cursive;"
				+ " -fx-background-color: black; -fx-font-size: 17px;");


		// Option 3:
		/*
		Try to fight off the police. The success of defeating the police is dependent on the
		player’s Fighter skill (higher Fighter level, higher chance of winning). Successfully
		fighting off the police will allow the player to travel as intended to the desired
		destination, keeping the stolen items in their inventory.
		 */

		Button option3 = new Button("Attempt to fight off police");
		option3.setOnMouseClicked(mouseEvent -> {
			if (police.determineSuccess(p1.getFighterSkill())) {
				// They get to go to the desired region
				p1.getSpaceShip().setFuelAfterTravel(p1.getCurrentRegion().distanceBetween(p1.getDestination()));
				RegionPage proceed = new RegionPage(primaryStage, p1, p1.getDestination(), regions);
				Alert a1 = new Alert(Alert.AlertType.CONFIRMATION, "You fought them off successfully!");
				a1.show();
			} else {
				RegionPage rp2 = new RegionPage(primaryStage, p1, p1.getCurrentRegion(), regions);
				Alert a2 = new Alert(Alert.AlertType.INFORMATION, "You did not fight them off, so you ended up at your last region.");
				a2.show();
			}
		});
		option3.setAlignment(Pos.CENTER);
		option3.setTextFill(Color.WHITE);
		option3.setStyle("-fx-font-family: 'Press Start 2P', cursive;"
				+ " -fx-background-color: black; -fx-font-size: 17px;");

		//Drop Shadow effect
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.CORAL);
		shadow.setWidth(1.5);
		option1.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						option1.setEffect(shadow);
					}
				});
		option2.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						option2.setEffect(shadow);
					}
				});
		option3.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						option3.setEffect(shadow);
					}
				});
		//adding the shadow when the mouse cursor is on
		option1.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						option1.setEffect(null);
					}
				});
		option2.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						option2.setEffect(null);
					}
				});
		option3.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						option3.setEffect(null);
					}
				});

		optionBox.getChildren().addAll(option1, option2, option3);
		optionBox.setAlignment(Pos.CENTER);
		optionBox.setPadding(new Insets(40, 5, 0, 0));

		//ImageView policePic = new ImageView(new Image("spacePolice.png"));

		Text policeText = new Text("You've been pulled\nover by the \nspace police!");
		policeText.setStyle("-fx-font-size: 40px; -fx-font-family: 'Press Start 2P', cursive;");
		policeText.setTextAlignment(TextAlignment.CENTER);
		policeText.setFill(Color.WHITE);
		policeText.setTextAlignment(TextAlignment.CENTER);


		VBox box2 = new VBox();
		//box2.setPadding(new Insets(10, 10, 10, 10));
		box2.setAlignment(Pos.CENTER);
		box2.getChildren().addAll(policeText, optionBox);


		root.getChildren().addAll(policeShip, box2);
		root.setPadding(new Insets(15, 15, 15, 15));

		primaryStage.setScene(scene);
		primaryStage.setTitle("The police have pulled you over");
		primaryStage.show();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
