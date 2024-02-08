package com.example.barchartanimated;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {

    final static String itemA = "A";
    final static String itemB = "B";
    final static String itemC = "C";
    @Override
    public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
        bc.setTitle("Summary");
        bc.setAnimated(true);
        xAxis.setLabel("Value");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Item");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(2, itemA));
        series1.getData().add(new XYChart.Data(20, itemB));
        series1.getData().add(new XYChart.Data(10, itemC));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(50, itemA));
        series2.getData().add(new XYChart.Data(41, itemB));
        series2.getData().add(new XYChart.Data(45, itemC));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(45, itemA));
        series3.getData().add(new XYChart.Data(44, itemB));
        series3.getData().add(new XYChart.Data(18, itemC));

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(2000),
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        for (XYChart.Series<Number, String> series : bc.getData()) {
                            for (XYChart.Data<Number, String> data : series.getData()) {
                                //data.setXValue(Math.random() * 100);
                                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                                    Bounds b1 = data.getNode().getBoundsInLocal();
                                    double newX = (b1.getWidth()) / 2 + b1.getMinX();
                                    //double newY = (b1.getHeight()) / 2 + b1.getMinY();
                                    // Make sure pie wedge location is reset
                                    data.getNode().setTranslateX(0);
                                    data.getNode().setTranslateY(0);
                                    TranslateTransition tt = new TranslateTransition(
                                            Duration.millis(1500), data.getNode());
                                    tt.setByX(newX);
                                    //tt.setByY(newY);
                                    tt.setAutoReverse(true);
                                    tt.setCycleCount(2);
                                    tt.play();
                                });

                            }
                        }
                    }
                }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        bc.getData().addAll(series1, series2, series3);

        /*
        System.out.println("bc:"+bc.getData());
        for (XYChart.Series<Number, String> data : bc.getData()) {
            System.out.println("Node:"+data.getNode());

            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                System.out.println("hej");
                Bounds b1 = data.getNode().getBoundsInLocal();
                double newX = (b1.getWidth()) / 2 + b1.getMinX();
                double newY = (b1.getHeight()) / 2 + b1.getMinY();
                // Make sure pie wedge location is reset
                data.getNode().setTranslateX(0);
                data.getNode().setTranslateY(0);
                TranslateTransition tt = new TranslateTransition(
                        Duration.millis(1500), data.getNode());
                tt.setByX(newX);
                tt.setByY(newY);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();
            });
        }
*/
        Scene scene = new Scene(bc, 800, 600);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}