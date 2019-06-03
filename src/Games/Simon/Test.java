package Games.Simon;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Test extends Application {

    public static Button create(String style, Color stroke, double translateX, double translateY, Pane pane){
        Button b = new Button();
        b.setStyle("-fx-background-color: #" + style);
        b.setBorder(new Border(new BorderStroke(stroke,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        b.translateYProperty().bind(pane.heightProperty().divide(translateY));
        b.translateXProperty().bind(pane.widthProperty().divide(translateX));
        return b;
    }

    @Override
    public void start(Stage primaryStage){

        int turn = 1;

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #404040");

        Button yellow = create("ffff66", Color.LIGHTGOLDENRODYELLOW, 1.7,5.5, pane);
        yellow.prefHeightProperty().bind(pane.heightProperty().divide(4));
        yellow.prefWidthProperty().bind(pane.widthProperty().divide(4));

        Button red = create("ff3300", Color.PINK, 5.5,  1.7, pane);
        red.prefHeightProperty().bind(pane.heightProperty().divide(4));
        red.prefWidthProperty().bind(pane.widthProperty().divide(4));

        Button green = create("55aa33", Color.LIGHTGREEN, 1.7, 1.7, pane);
        green.prefHeightProperty().bind(pane.heightProperty().divide(4));
        green.prefWidthProperty().bind(pane.widthProperty().divide(4));


        Button blue = create("0099ff", Color.LIGHTBLUE, 5.5, 5.5, pane);
        blue.prefHeightProperty().bind(pane.heightProperty().divide(4));
        blue.prefWidthProperty().bind(pane.widthProperty().divide(4));

        Image image = new Image("image/simon1.png");
        ImageView logo = new ImageView(image);
        logo.fitWidthProperty().bind(pane.widthProperty().divide(3));
        logo.fitHeightProperty().bind(pane.heightProperty().divide(3));
        logo.layoutXProperty().bind(pane.widthProperty().divide(2.9));
        logo.layoutYProperty().bind(pane.heightProperty().divide(2.9));

        Circle wrong = new Circle();
        wrong.setRadius(15);
        wrong.setFill(Color.PINK);
        wrong.setStroke(Color.RED);
        wrong.setStrokeWidth(4);
        wrong.translateXProperty().bind(pane.widthProperty().divide(4));
        wrong.translateYProperty().bind(pane.heightProperty().divide(9));

        Circle right = new Circle();
        right.setRadius(15);
        right.setFill(Color.LIGHTGREEN);
        right.setStroke(Color.DARKGREEN);
        right.setStrokeWidth(4);
        right.translateXProperty().bind(pane.widthProperty().divide(2.7));
        right.translateYProperty().bind(pane.heightProperty().divide(9));

        Button start = new Button();
        start.setText("Start!");
        start.setFont(Font.font ("Verdana", 30));
        start.setTextFill(Color.LIGHTGREEN);
        start.setStyle("-fx-background-color: #55aa33");
        start.prefWidthProperty().bind(pane.widthProperty().divide(3));
        start.prefHeightProperty().bind(pane.heightProperty().divide(7));
        start.layoutYProperty().bind(pane.heightProperty().divide(50));
        start.layoutXProperty().bind(pane.widthProperty().divide(1.6));

        Label counter = new Label();
        counter.setText("0 / " + turn);
        counter.translateXProperty().bind(pane.widthProperty().divide(10));
        counter.translateYProperty().bind(pane.heightProperty().divide(2.2));
        counter.maxHeightProperty().bind(pane.heightProperty().divide(7));
        counter.maxWidthProperty().bind(pane.widthProperty().divide(4));
        counter.setFont(Font.font("Oswald", 40));
        counter.setTextFill(Color.BLACK);

        pane.getChildren().addAll(blue, yellow, green, red, logo, wrong, right, start, counter);

        int[] order = new int[100];
        for (int i = 0; i < 100; i++){
            int num = (int)(Math.random() * 4) + 1;
            order[i] = num;
        }

        yellow.setOnMouseEntered(e -> {
            yellow.setStyle("-fx-background-color: #ffffbb");
        });

        yellow.setOnMouseExited(e -> {
            yellow.setStyle("-fx-background-color: #ffff66");
        });

        red.setOnMouseEntered(e -> {
            red.setStyle("-fx-background-color: #ff6666");
        });

        red.setOnMouseExited(e -> {
            red.setStyle("-fx-background-color: #ff3300");
        });

        green.setOnMouseEntered(e -> {
            green.setStyle("-fx-background-color: #33cc33");
        });

        green.setOnMouseExited(e -> {
            green.setStyle("-fx-background-color: #55aa33");
        });

        blue.setOnMouseEntered(e -> {
            blue.setStyle("-fx-background-color: #99ccff");
        });

        blue.setOnMouseExited(e -> {
            blue.setStyle("-fx-background-color: #0099ff");
        });

        start.setOnMousePressed(e -> {
            pane.getChildren().remove(start);

            new AnimationTimer(){
                public void handle(long currentNanoTime){

                }
            }.start();
        });



        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simon");
        primaryStage.show();

    }
}
