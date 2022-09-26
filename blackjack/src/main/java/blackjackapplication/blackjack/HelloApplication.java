package blackjackapplication.blackjack;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    static BlackjackApp bjackApp;
    private TextField moneyField;
    private TextField betField;
    private TextField cardsField;
    private TextField pointField;
    private TextField resultField;


    @Override
    public void start (Stage stage){

        bjackApp = new BlackjackApp ();

        stage.setTitle ("Blackjack");
        GridPane grid = new GridPane();
        grid.setAlignment (Pos.TOP_LEFT);
        grid.setPadding (new Insets (25,25,25,25));
        grid.setHgap (10);
        grid.setVgap (10);

        Scene scene = new Scene (grid, 400,700);

        /* Fonction getmoney, l'argent de la banque */

        grid.add(new Label ("Money :"), 0,0);
        moneyField = new TextField ();
        moneyField.setText("100");
        moneyField.setEditable (false);
        grid.add(moneyField,1,0);

        grid.add(new Label ("Bet :"), 0,1);
        betField = new TextField ();
        grid.add(betField,1,1);

        grid.add(new Label ("DEALER"), 0,2);

        /*grid.add(new Label ("Cards :"),0,3);
        cardsField = new TextField ();
        grid.add(cardsField,1,3);*/

        grid.add(new Label ("Cards :"),0,3);
        ListView listCardDealer = new ListView ();
        listCardDealer.getItems().add("Item 1");
        listCardDealer.getItems().add("Item 2");
        listCardDealer.getItems().add("Item 3");

        HBox dealerBox = new HBox(listCardDealer);

        grid.add(listCardDealer,1,3);

        grid.add(new Label ("Points :"),0,4);
        pointField = new TextField ();
        grid.add(pointField,1,4);

        grid.add(new Label ("YOU"), 0,5);

        /*grid.add(new Label ("Cards :"),0,6);
        cardsField = new TextField ();
        grid.add(cardsField,1,6);*/

        grid.add(new Label ("Cards :"),0,6);
        ListView listCardPlayer = new ListView ();
        listCardPlayer.getItems().add("Item 1");
        listCardPlayer.getItems().add("Item 2");
        listCardPlayer.getItems().add("Item 3");
        HBox playerBox = new HBox(listCardPlayer);
        grid.add(listCardPlayer,1,6);

        grid.add(new Label ("Points :"),0,7);
        pointField = new TextField ();
        grid.add(pointField,1,7);

        Button hitButton = new Button ("Hit");
        hitButton.setOnAction (event -> hitButtonClicked());

        Button standButton = new Button ("Stand");
        standButton.setOnAction (event -> standButtonClicked());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren ().add (hitButton);
        buttonBox.getChildren ().add (standButton);
        grid.add (buttonBox,0,8,2,1);

        grid.add(new Label ("RESULT :"),0,9);
        resultField = new TextField ();
        grid.add(resultField,1,9);

        Button playButton = new Button ("Play");
        playButton.setOnAction (event -> playButtonClicked());

        Button exitButton = new Button ("Exit");
        exitButton.setOnAction (event -> exitButtonClicked());

        HBox buttonBox2 = new HBox(10);
        buttonBox2.getChildren ().add (playButton);
        buttonBox2.getChildren ().add (exitButton);
        grid.add (buttonBox2,0,10,2,1);

        Scene scene2 = new Scene (dealerBox,200,100);
        Scene scene3 = new Scene (playerBox,200,100);
        stage.setScene (scene2);
        stage.setScene (scene3);
        stage.setScene (scene);
        stage.show ();
    }

    private void hitButtonClicked(){
        resultField.setText ( "you've pressed Hit!" );
    }
    private void standButtonClicked(){
        resultField.setText ( "you've pressed Stand!" );
    }
    private void playButtonClicked(){
        resultField.setText ( "you've pressed Play!" );
    }
    private void exitButtonClicked(){
        resultField.setText ( "you've pressed Exit! Beubye Now!" );
    }
    public static void main (String[] args) {
        launch (args);
    }
}