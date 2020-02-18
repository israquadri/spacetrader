package src;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MarketPage {

    public MarketPage(Stage primaryStage, Player p1, Region region, Region[] array) {
        VBox root = new VBox(40);
        Scene mktscene = new Scene(root, 800, 800);

        //HBox for middle of screen
        HBox mid = new HBox(40);
        mid.setAlignment(Pos.TOP_CENTER);
        mid.setPrefHeight(400);

        //Welcome text for market
        Text welcome = new Text("Welcome to the \n" + region.getDescription() + " market");
        welcome.setStyle("-fx-font-size: 15px; -fx-font-family: 'Press Start 2P', cursive;");
        welcome.setTextAlignment(TextAlignment.CENTER);
        mid.getChildren().add(welcome);

        //Instantiating items
        Item item1 = new Item(region.getTax(), p1.getCredits(), region.getTechnologyLevel(), region.getItem1Name(), region.getItem1Description(), region.getItem1Quantity());
        Item item2 = new Item(region.getTax(), p1.getCredits(), region.getTechnologyLevel(), region.getItem2Name(), region.getItem2Description(), region.getItem2Quantity());
        Item item3 = new Item(region.getTax(), p1.getCredits(), region.getTechnologyLevel(), region.getItem3Name(), region.getItem3Description(), region.getItem3Quantity());

        //HBox for the top of the screen
        HBox top = new HBox(40);
        top.setAlignment(Pos.TOP_LEFT);
        top.setPrefHeight(400);

        //Back to Region Page button
        Button back = new Button("Back to Orbit");
        back.setStyle("-fx-font-family: 'Press Start 2P', cursive;"
                + " -fx-background-color: black; -fx-font-size: 20px;");
        back.setTextFill(Color.WHITE);
        back.setOnMouseClicked((MouseEvent m) -> {
            RegionPage r = new RegionPage(primaryStage, p1, region, array);
        });

        //Drop shadow effect
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CORAL);
        shadow.setWidth(1.5);

        //Drop shadow effect being applied to back button
        back.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        back.setEffect(shadow);
                    }
                });
        //adding the shadow when the mouse cursor is on
        back.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        back.setEffect(null);
                    }
                });
        top.getChildren().add(back);

        //Adding different hboxes to root vbox node
        root.getChildren().addAll(top, mid);

        //Making scene show
        primaryStage.setScene(mktscene);
        primaryStage.show();

    }

}
