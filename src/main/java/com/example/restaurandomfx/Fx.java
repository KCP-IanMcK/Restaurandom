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

import java.io.File;
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
        List<RadioButton> radioButtonsLocations = new ArrayList<>();
        ToggleGroup toggleGroup = new ToggleGroup();
        TextField textField = new TextField();
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

        //Location Component ----------------------------------------------------
        VBox wrapLocations = new VBox();
        wrapCuisines.setAlignment(Pos.CENTER);
        VBox vboxLocations = new VBox();


        RadioButton rBAuto = new RadioButton("Auto");
        RadioButton rBManual = new RadioButton("Manual");
        vboxLocations.getChildren().add(rBAuto);
        vboxLocations.getChildren().add(rBManual);
        radioButtonsLocations.add(rBAuto);
        radioButtonsLocations.add(rBManual);
        rBAuto.setToggleGroup(toggleGroup);
        rBManual.setToggleGroup(toggleGroup);

        radioButtonsLocations.getFirst().setSelected(true);
        radioButtonsLocations.getFirst().setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: #FFFFFF");

        ScrollPane scrollPaneLocations = new ScrollPane(vboxLocations);
        scrollPaneLocations.setMinWidth(scene.getWidth() / 10);
        scrollPaneLocations.setMaxHeight(scene.getHeight() / 5);
        Label locationLabel = new Label("Find coordinates:");
        locationLabel.setTextFill(Color.WHITE);
        locationLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, scene.getWidth() / 50));
        wrapLocations.getChildren().add(locationLabel);
        wrapLocations.getChildren().add(scrollPaneLocations);
        wrapLocations.setAlignment(Pos.CENTER);

        //Pricing Component ----------------------------------------------------
        VBox wrapPricing = new VBox();
        wrapPricing.setAlignment(Pos.CENTER);
        VBox vboxPricing = new VBox();
        ToggleGroup toggleGroupPricing = new ToggleGroup();
        List<RadioButton> radioButtonsPricing = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            RadioButton radioButton = new RadioButton(String.valueOf(i));
            vboxPricing.getChildren().add(radioButton);
            toggleGroupPricing.getToggles().add(radioButton);
            radioButtonsPricing.add(radioButton);
        }
        toggleGroupPricing.getToggles().getLast().setSelected(true);
        radioButtonsPricing.getFirst().setText("1 (cheap)");
        radioButtonsPricing.getLast().setText("4 (expensive)");

        ScrollPane scrollPanePricing = new ScrollPane(vboxPricing);
        scrollPanePricing.setMinWidth(scene.getWidth() / 10);
        scrollPanePricing.setMaxHeight(scene.getHeight() / 5);
        scrollPanePricing.setStyle("-fx-background-color:#FFFFFF;");
        Label pricingLabel = new Label("Max Pricing:");
        pricingLabel.setTextFill(Color.WHITE);
        pricingLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, scene.getWidth() / 50));
        wrapPricing.getChildren().add(pricingLabel);
        wrapPricing.getChildren().add(scrollPanePricing);

        //Distance Component ----------------------------------------------------
        VBox wrapRadius = new VBox();
        wrapRadius.setAlignment(Pos.CENTER);
        TextField radiusTextField = new TextField();
        Label radiusLabel = new Label("Max radius in m:");
        radiusLabel.setTextFill(Color.WHITE);
        radiusLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, scene.getWidth() / 50));
        radiusTextField.setPromptText("Default = 2000");
        wrapRadius.getChildren().add(radiusLabel);
        wrapRadius.getChildren().add(radiusTextField);


        //Titel --------------------------------------------------------------------
        File file = new File("src/main/resources/RestaurandomLogo.png");
        Image logo = new Image(file.toURI().toString());
        ImageView imageViewLogo = new ImageView(logo);
        imageViewLogo.setPreserveRatio(true);
        imageViewLogo.setFitWidth(scene.getWidth());
        hBox.getChildren().add(imageViewLogo); //label
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20, 0, 0, 0));

        //Adding all middle Components ----------------------------------------------
        hBox2.getChildren().add(wrapRadius);
        hBox2.getChildren().add(wrapLocations);
        hBox2.getChildren().add(btn1);
        hBox2.getChildren().add(wrapCuisines);
        hBox2.getChildren().add(wrapPricing);
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
                String priceLevel = "";
                String radius = "2000";

                for (CheckBox checkBox : checkBoxesCuisines) {
                    if (checkBox.isSelected()) {
                        desiredCuisines.add(checkBox.getText());
                    }
                }
                for (RadioButton radioButton : radioButtonsPricing) {
                    if (radioButton.isSelected()) {
                        priceLevel = radioButton.getText();
                    }
                }

                for (RadioButton radioButton : radioButtonsPricing) {
                    if (radioButton.isSelected()) {
                        priceLevel = radioButton.getText();
                    }
                }

                if (!radiusTextField.getText().isEmpty()){
                    radius = radiusTextField.getText();
                }

                if(Main.firstTime && rBAuto.isSelected()) { //Muss nur das erste mal eine Request senden, nachher kannn es das Json auslesen
                    GoogleAPIRequest.googleAPIRequest(desiredCuisines, location, radius, priceLevel);
                } else if (Main.firstTime && rBManual.isSelected()) {
                    String manualLocation = textField.getText();
                    manualLocation = manualLocation.replace(" ", "");
                    GoogleAPIRequest.googleAPIRequest(desiredCuisines, manualLocation, radius, priceLevel);
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
                                return new Image("file:src/main/resources/photo.jpg");
                            } else {
                                return new Image("file:src/main/resources/noPhoto.jpg");
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

        for (RadioButton radioButton : radioButtonsLocations) {
            radioButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (RadioButton radioButton : radioButtonsLocations) {
                        radioButton.setTextFill(Color.BLACK);
                        radioButton.setStyle("-fx-background-color: #FFFFFF");
                    }
                    for (RadioButton radioButton : radioButtonsLocations) {
                        if(radioButton.isSelected()) {
                            radioButton.setTextFill(Color.WHITE);
                            radioButton.setStyle("-fx-background-color: #7B1FA2");
                        }
                    }
                    textField.setPromptText("Latitude, Longitude");
                    if(radioButton.equals(rBManual)){
                        try {
                            wrapLocations.getChildren().add(textField);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if (radioButton.equals(rBAuto)){
                        wrapLocations.getChildren().removeLast();
                    }
                }
            });
        }

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
        radioButtonsPricing.getLast().setTextFill(Color.WHITE);
        radioButtonsPricing.getLast().setStyle("-fx-background-color: #7B1FA2");

        for (RadioButton radioButton : radioButtonsPricing) {
            radioButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (RadioButton radioButton1 : radioButtonsPricing) {
                        if(radioButton1.isSelected()){
                            radioButton1.setTextFill(Color.WHITE);
                            radioButton1.setStyle("-fx-background-color: #7B1FA2");
                        } else {
                            radioButton1.setTextFill(Color.BLACK);
                            radioButton1.setStyle("-fx-background-color: #FFFFFF");
                        }
                    }
                }
            });
        }

        radiusTextField.setStyle("-fx-focus-color: #7B1FA2;");
        textField.setStyle("-fx-focus-color: #7B1FA2;");
    }
}
