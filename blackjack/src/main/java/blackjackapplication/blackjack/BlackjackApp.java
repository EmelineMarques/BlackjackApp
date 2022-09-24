package blackjackapplication.blackjack;

import javafx.application.Application;
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

public class BlackjackApp extends Application {
    static BlackjackGame game;
    private TextField moneyField;
    private TextField betField;
    private TextField pointField;
    private TextField resultField;
    private ListView listCardDealer;
    private ListView listCardPlayer;
    private Button hitButton;

    public static void main (String[] args) {

        System.out.println ( "BLACKJACK!" );
        System.out.println ( "Blackjack payout is 3:2 \n" );
        System.out.println ( "**********************************" );
        System.out.println ( "***** Pop-up window is open. *****" );
        System.out.println ( "**********************************" );

        //String playAgain = "y";
        game = new BlackjackGame ();

        // Launches the app
        launch (args);

        /* Was useful only when we played from the Terminal
        while ( playAgain.equalsIgnoreCase ( "y" ) ) {
            showMoney ();
            if ( game.isOutOfMoney () ) {
                if ( !buyMoreChips () )
                    break;
            }
            getBetAmount ();
            game.deal ();
            showHands ();
            System.out.printf ( "Your points: %d%n", game.getPlayerHand ().getPoints () );
            if ( game.isBlackjackOrBust () ) {
                showWinner ();
            }
            while ( getHitOrStand ().equalsIgnoreCase ( "h" ) ) {
                game.hit ();
                if ( game.isBlackjackOrBust () )
                    break;
                System.out.println ();
                showPlayerHand ();
                System.out.println ();
                System.out.printf ( "Your points: %d%n", game.getPlayerHand ().getPoints () );
            }
            game.stand ();
            System.out.println ();
            showWinner ();
            playAgain = Console.getString ( "Continue? (y/n): ", new String[]{"y", "n"} );
            System.out.println ();
            game.resetHands ();
            if ( playAgain.equalsIgnoreCase ( "n" ) ) {
                break;
            }
        }
        System.out.println ( "\nBye!" );*/
    }

    private boolean buyMoreChips () {
        String addMore = Console.getString ( "You are out of Money! \nWould you buy more? (Press y or n) : ", new String[]{"y", "n"} );
        if ( addMore.equalsIgnoreCase ( "y" ) ) {
            game.resetMoney ();
            return true;
        } else {
            return false;
        }
    }

    /*private void getBetAmount () {
        double betAmount = Console.getDouble ( "Bet amount : ", game.getMinBet (), Math.min ( game.getMaxBet (), game.getTotalMoney () ) );
        game.setBet ( betAmount );
        System.out.println ();
    }*/
    private boolean getFxBetAmount () {
        //double betAmount = Console.getDouble ( "Bet amount : ", game.getMinBet (), Math.min ( game.getMaxBet (), game.getTotalMoney () ) );

        String betText = betField.getText ();
        if(betText == null || betText.isEmpty ()){
            // Qu'estce que tu veux faire si ya rien dans la text-box
            return false;
        }

        try{
            double bet = Double.parseDouble ( betText );

            if( bet < game.getMinBet () || bet > ( Math.min (game.getMaxBet (),game.getTotalMoney ()))){
                // Qu'estce que tu veux faire si la bet est trop basse ou trop haute?
                return false;
            } else {
                game.setBet ( bet );
                return true;
            }

        }catch( NumberFormatException nfe ){
            // Qu'estce que tu veux faire si c'est pas un chiffre?
            return false;
        }
    }
    /*private String getHitOrStand () {
        System.out.println ();
        return Console.getString ( "Do you wish to Hit or Stand? (Press h or s) : ", new String[]{"h", "s"} );
    }*/
    private void showHands () {
        showPlayerHand ();
        System.out.println ("In showHands");
        showDealerShowCard ();
       // showDealerHand();
    }
    private void showDealerShowCard () {
        System.out.println ( "SHOW DEALER'S CARD" );
        System.out.println ( game.getDealerShowCard ().display () );
        System.out.println ();

        listCardDealer.getItems ().clear ();
        listCardDealer.getItems ().add ( game.getDealerShowCard ().display () );

    }
    private void showDealerHand () {
        System.out.println ( "DEALER'S CARDS" );
        for (Card card : game.getDealerHand ().getCards ()) {
            if ( card != null )
                System.out.println ( card.display () );
        }
    }
    private void showPlayerHand () {
        listCardPlayer.getItems ().clear ();
        System.out.println ( "Showing YOUR CARDS in UI" );
        for (Card card : game.getPlayerHand ().getCards ()) {
            if ( card != null ){
                System.out.println ( card.display () );
                listCardPlayer.getItems ().add ( card.display () );
            }

        }
    }
    private void showMoney () {
        System.out.println ( "Total Money is : " + game.getTotalMoney () );
        System.out.println ();
    }
    private void showWinner () {
        showPlayerHand ();
        System.out.println ();
        System.out.printf ( "YOUR POINTS: %d%n", game.getPlayerHand ().getPoints () );
        System.out.println ();
        showDealerHand ();
        System.out.println ();
        System.out.printf ( "DEALER'S POINTS: %d%n%n", game.getDealerHand ().getPoints () );
        if ( game.isPush () ) {
            System.out.println ( "Push!" );
        } else if ( game.getPlayerHand ().isBlackjack () ) {
            System.out.println ( "BLACKJACK! You win!" );
            game.addBlackjackToTotal ();
        } else if ( game.playerWins () ) {
            System.out.println ( "You win!" );
            game.addBetToTotal ();
        } else {
            System.out.println ( "Sorry, you lose." );
            game.subtractBetFromTotal ();
        }
        showMoney ();
        game.saveMoney ();
    }
    @Override
    public void start (Stage stage){

        stage.setTitle ("Blackjack");
        GridPane grid = new GridPane();
        grid.setAlignment ( Pos.TOP_LEFT);
        grid.setPadding (new Insets (25,25,25,25));
        grid.setHgap (10);
        grid.setVgap (10);

        Scene scene = new Scene (grid, 400,700);

        grid.add(new Label ("Money :"), 0,0);
        moneyField = new TextField ();
        moneyField.setText("$" + game.getTotalMoney ()); //va falloir faire une conversion de Double à String
        moneyField.setEditable (false);
        grid.add(moneyField,1,0);

        grid.add(new Label ("Bet :"), 0,1);
        betField = new TextField ( "");
        grid.add(betField,1,1);

        grid.add(new Label ("DEALER"), 0,2);

        grid.add(new Label ("Cards :"),0,3);
        listCardDealer = new ListView ();
        //listCardDealer.getItems().add(game.getDealerHand ());
        HBox dealerBox = new HBox( listCardDealer );
        grid.add( listCardDealer,1,3);
        //showDealerShowCard ();

        grid.add(new Label ("Points :"),0,4);
        pointField = new TextField ();
        grid.add(pointField,1,4);

        grid.add(new Label ("YOU"), 0,5);

        grid.add(new Label ("Cards :"),0,6);
        listCardPlayer = new ListView ();
        //listCardPlayer.getItems().add(game.getPlayerHand());
        HBox playerBox = new HBox( listCardPlayer );
        grid.add( listCardPlayer,1,6);

        //showPlayerHand ();

        grid.add(new Label ("Points :"),0,7);
        pointField = new TextField ( );
        grid.add(pointField,1,7);

        hitButton = new Button ("Hit");
        hitButton.setOnAction ( event -> hitButtonClicked());

        hitButton.setDisable ( true );

        Button standButton = new Button ("Stand");
        standButton.setOnAction (event -> standButtonClicked());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren ().add ( hitButton );
        buttonBox.getChildren ().add (standButton);
        grid.add (buttonBox,0,8,2,1);

        grid.add(new Label ("RESULT :"),0,9);
        resultField = new TextField ("");
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
        System.out.println ( "you've pressed Hit!" );
        game.hit ();
        game.isBlackjackOrBust ();
        showHands();
    }
    private void standButtonClicked(){
        resultField.setText ( "you've pressed Stand!" );
        System.out.println ( "you've pressed Stand!" );
        game.stand ();
        game.isBlackjackOrBust ();
        game.isPush ();
        game.playerWins ();
        showWinner ();
    }
    private void playButtonClicked(){
        resultField.setText ( "You've pressed Play! Awesome! Let's Play!" );
        System.out.println ( "You've pressed Play!\nAwesome!\nLet's Play! \n\nFirst card:" );

        boolean isBetOk = getFxBetAmount();
        if(isBetOk){

            game.resetHands ();
            game.deal ();
            showHands();
            hitButton.setDisable ( false );

        }else{
            resultField.setText ( " Aye le cave, tu bets like shit " );
        }

    }
    private void exitButtonClicked(){
        System.exit ( 0 );
    }
}