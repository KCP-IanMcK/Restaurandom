package com.example.restaurandomfx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fx extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        List<String> cuisines = new ArrayList<>();
        String[] array = {"Chinese", "Italian", "Fastfood", "Indian"}; //Cuisines dürfen keine Abstände haben, sonst funktioniert API nicht
        Collections.addAll(cuisines, array);
        String location = LocationGetter.getGeoLocation();


        BorderPane root = new BorderPane();
        HBox hBox = new HBox();
        Scene scene = new Scene(root, 1000, 500);
        Button btn1 = new Button("Get Random Restaurant");
        Button resetBtn = new Button("Reset");
        HBox hBox2 = new HBox(scene.getWidth() / 50);
        HBox hBoxImage = new HBox();
        List<CheckBox> checkBoxesCuisines = new ArrayList<>();
        btn1.setStyle("-fx-background-color:#B0BEC5; -fx-font-weight:bold; -fx-border-radius: 3px; -fx-font-size: 18px");


        //Cuisine Component ----------------------------------------------------
        VBox wrapCuisines = new VBox();
        wrapCuisines.setAlignment(Pos.CENTER);
        VBox vboxCuisines = new VBox();
        CheckBox checkBox1 = new CheckBox("Select all");
        vboxCuisines.getChildren().add(checkBox1);

        checkBoxesCuisines.add(checkBox1);

        for (String cuisine : cuisines) {
            CheckBox checkBox = new CheckBox(cuisine);
            vboxCuisines.getChildren().add(checkBox);
            checkBoxesCuisines.add(checkBox);
        }
        checkBoxesCuisines.getFirst().setSelected(true);

        ScrollPane scrollPaneCuisines = new ScrollPane(vboxCuisines);
        scrollPaneCuisines.setMinWidth(scene.getWidth() / 10);
        scrollPaneCuisines.setMaxHeight(scene.getHeight() / 5);
        Label cuisineLabel = new Label("Cuisine:");
        cuisineLabel.setTextFill(Color.WHITE);
        cuisineLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, scene.getWidth() / 50));
        wrapCuisines.getChildren().add(cuisineLabel);
        wrapCuisines.getChildren().add(scrollPaneCuisines);


        //Titel --------------------------------------------------------------------
        Label label = new Label("Restaurandom");
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, scene.getWidth() / 20)); //Groesse sollte dem Fenster angepasst sein
        label.setTextFill(Color.web("#FFFFFF")); //White
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20, 0, 0, 0));

        //Adding all middle Components ----------------------------------------------
        hBox2.getChildren().add(btn1);
        hBox2.getChildren().add(wrapCuisines);
        hBox2.setAlignment(Pos.CENTER);

        root.setTop(hBox);
        root.setCenter(hBox2);
        root.setStyle("-fx-background-color: #1E1E1E"); //Dark-Gray

        primaryStage.setScene(scene);
        primaryStage.setTitle("Restaurandom Application");
        primaryStage.show();

        ImageView imageView = new ImageView();

        //Reroll-Button Logic --------------------------------------------------------
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<String> desiredCuisines = new ArrayList<>();

                for (CheckBox checkBox : checkBoxesCuisines) {
                    if (checkBox.isSelected()) {
                        desiredCuisines.add(checkBox.getText());
                    }
                }

                if(Main.firstTime) { //Muss nur das erste mal eine Request senden, nachher kannn es das Json auslesen
                    GoogleAPIRequest.googleAPIRequest(desiredCuisines, location, array);
                }

                Restaurant restaurant = Main.chooseOne(Main.readRestaurants(), desiredCuisines);
                String photoReference = restaurant.getPhotoReference();
                if(photoReference != null) {
                    PhotoRequest.requestPhoto(photoReference);
                }
                Label randomRestaurant = new Label();

                if (!restaurant.getName().equals("noRestaurant")) {
                    randomRestaurant.setText(restaurant.toString());
                    btn1.setText("Reroll another Restaurant");
                    hBox2.getChildren().clear();
                    hBox2.getChildren().add(btn1);
                } else {                                                //Wenn es keine weiteren Restaurants hat
                    randomRestaurant.setText(restaurant.toString2());
                    hBox2.getChildren().clear();
                    hBox2.getChildren().add(resetBtn);
                }
                hBox2.setAlignment(Pos.CENTER);
                hBox2.setPadding(new Insets(0, 0, 20, 0));
                randomRestaurant.setFont(Font.font("Arial", FontWeight.BOLD, scene.getWidth() / 30));
                randomRestaurant.setLayoutX(80);
                randomRestaurant.setLayoutY(scene.getHeight() / 2 - 80);
                randomRestaurant.setTextFill(Color.WHITE);
                root.setCenter(randomRestaurant);


                //Image Component --------------------------------------------------------
                Label loadingImage = new Label("Loading Image");
                loadingImage.setMinHeight(scene.getHeight() * 0.3);
                loadingImage.setTextFill(Color.WHITE);
                loadingImage.setAlignment(Pos.CENTER);
                hBoxImage.getChildren().clear();
                hBoxImage.getChildren().add(loadingImage);
                hBoxImage.setAlignment(Pos.CENTER);

                root.setBottom(hBox2);
                root.setTop(hBoxImage);


                    Task<Image> loadImageTask = new Task<>() {
                        @Override
                        protected Image call() {
                            if(photoReference != null) {
                                return new Image("file:photo.jpg");
                            } else {
                                return new Image("file:noPhoto.jpg");
                            }
                        }
                    };


                loadImageTask.setOnSucceeded(event -> {imageView.setImage(loadImageTask.getValue());
                    imageView.setPreserveRatio(true);
                    imageView.setFitHeight(scene.getHeight() * 0.3);
                    hBoxImage.getChildren().clear();
                    hBoxImage.getChildren().add(imageView);
                    hBoxImage.setAlignment(Pos.CENTER);
                    root.setTop(hBoxImage);
                });

                new Thread(loadImageTask).start();


                primaryStage.show();
            }
        });

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.firstTime = true;
                try {
                    start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Button und Checkbox Styling---------------------------------------------------------------------------
        btn1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn1.setStyle("-fx-background-color:#7B1FA2; -fx-font-weight:bold; -fx-border-radius: 3px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px");
            }
        });
        btn1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn1.setStyle("-fx-background-color:#B0BEC5; -fx-font-weight:bold; -fx-border-radius: 3px; -fx-font-size: 18px");
            }
        });
        btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn1.setStyle("-fx-background-color:#7B1FA2; -fx-font-weight:bold; -fx-border-radius: 3px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px");
            }
        });

        checkBox1.setTextFill(Color.WHITE);
        checkBox1.setStyle("-fx-background-color: #7B1FA2");

        for (CheckBox checkBoxesCuisine : checkBoxesCuisines) {
            checkBoxesCuisine.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (CheckBox boxesCuisine : checkBoxesCuisines) {
                        if(boxesCuisine.isSelected()){
                            boxesCuisine.setTextFill(Color.WHITE);
                            boxesCuisine.setStyle("-fx-background-color: #7B1FA2");
                        } else {
                            boxesCuisine.setTextFill(Color.BLACK);
                            boxesCuisine.setStyle("-fx-background-color: #FFFFFF");
                        }
                    }
                }
            });
        }
    }
}
