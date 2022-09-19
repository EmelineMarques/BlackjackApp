package blackjackapplication.blackjack;

import java.util.Scanner;

public class BlackjackApp {
    static BlackjackGame game;

    public static void main (String[] args) {
        System.out.println ( "BLACKJACK!" );
        System.out.println ( "Blackjack payout is 3:2 \n\n" );
        String playAgain = "y";
        game = new BlackjackGame ();
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
        System.out.println ( "\nBye!" );
    }

    private static boolean buyMoreChips () {
        String addMore = Console.getString ( "You are out of Money! \nWould you buy more? (Press y or n) : ", new String[]{"y", "n"} );
        if ( addMore.equalsIgnoreCase ( "y" ) ) {
            game.resetMoney ();
            return true;
        } else {
            return false;
        }
    }

    private static void getBetAmount () {
        double betAmount = Console.getDouble ( "Bet amount : ", game.getMinBet (), Math.min ( game.getMaxBet (), game.getTotalMoney () ) );
        game.setBet ( betAmount );
        System.out.println ();
    }

    private static String getHitOrStand () {
        System.out.println ();
        return Console.getString ( "Do you wish to Hit or Stand? (Press h or s) : ", new String[]{"h", "s"} );
    }

    private static void showHands () {
        showPlayerHand ();
        System.out.println ();
        showDealerShowCard ();
    }

    private static void showDealerShowCard () {
        System.out.println ( "SHOW DEALER'S CARD" );
        System.out.println ( game.getDealerShowCard ().display () );
        System.out.println ();
    }

    private static void showDealerHand () {
        System.out.println ( "DEALER'S CARDS" );
        for (Card card : game.getDealerHand ().getCards ()) {
            if ( card != null )
                System.out.println ( card.display () );
        }
    }

    private static void showPlayerHand () {
        System.out.println ( "YOUR CARDS" );
        for (Card card : game.getPlayerHand ().getCards ()) {
            if ( card != null )
                System.out.println ( card.display () );
        }
    }


    private static void showMoney () {
        System.out.println ( "Total Money is : " + game.getTotalMoney () );
        System.out.println ();
    }

    private static void showWinner () {
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
}