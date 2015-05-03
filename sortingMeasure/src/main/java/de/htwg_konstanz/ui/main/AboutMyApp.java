package de.htwg_konstanz.ui.main;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutMyApp {
	
    private double initX;
    private double initY;
	
	public AboutMyApp(HostServices hostServices) {
        //create stage which has set stage style transparent
        final Stage stage = new Stage(StageStyle.TRANSPARENT);
        //create root node of scene, i.e. group
        Group rootGroup = new Group();
        //create scene with set width, height and color
        Scene scene = new Scene(rootGroup, 400,400, Color.TRANSPARENT);
        //set scene to stage
        stage.setScene(scene);
        //center stage on screen
        stage.centerOnScreen();
        //show the stage
        stage.show();
        
        //create dragger with desired size
        Circle dragger = new Circle(200, 200, 200);
        //fill the dragger with some nice radial background
        dragger.setFill(new RadialGradient(-0.3, 135, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
            new Stop(0, Color.DARKGRAY),
            new Stop(1, Color.BLACK)
         }));
        
        
        //when mouse button is pressed, save the initial position of screen
        rootGroup.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                initX = me.getScreenX() - stage.getX();
                initY = me.getScreenY() - stage.getY();
            }
        });

        //when screen is dragged, translate it accordingly
        rootGroup.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                stage.setX(me.getScreenX() - initX);
                stage.setY(me.getScreenY() - initY);
            }
        });
        
        Button close = new Button("Close me");
        close.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //in case we would like to close whole demo
                //javafx.application.Platform.exit();

                //however we want to close only this instance of stage
                stage.close();
            }
        });
        // CREATE SIMPLE TEXT NODE
        Text text = new Text("Measure\nAlgorithms"); //20, 110,
        text.setFill(Color.WHITESMOKE);
        text.setEffect(new Lighting());
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setFont(Font.font(Font.getDefault().getFamily(), 50));
        
        Text description = new Text("Programmed by Philip Benischke");
        description.setFont(Font.font(Font.getDefault().getFamily(), 13));
        
        Hyperlink h1 = new Hyperlink("Philip's CV");
        h1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				hostServices.showDocument("www.google.com");
				
			}
		});
        
        

        // USE A LAYOUT VBOX FOR EASIER POSITIONING OF THE VISUAL NODES ON SCENE
        VBox vBox = new VBox();
        vBox.setPrefWidth(300);
        vBox.setPrefHeight(300);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(60, 0, 0, 60));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text,description, h1, close);

        //add all nodes to main root group
        rootGroup.getChildren().addAll(dragger, vBox);
	}
	

}
