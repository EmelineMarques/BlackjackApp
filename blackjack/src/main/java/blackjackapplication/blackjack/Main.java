package blackjackapplication.blackjack;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        System.out.println ( "\nBLACKJACK ! \nBlackjack payout is 3:2 \n" );
        Scanner sc = new Scanner ( System.in );
        System.out.print ( "Starting money : $ ");
        double startingMoney = Double.parseDouble ( sc.nextLine () );
        NumberFormat currency = NumberFormat.getCurrencyInstance (Locale.CANADA);
        String choice = "y";

        while ( startingMoney < 5 || startingMoney > 10000 ) {
            System.out.println ( "Starting money must be between $5.00 and $10,000.00" );
            System.out.print ( "Starting money : " );
            startingMoney = Double.parseDouble ( sc.nextLine () );
        }

        while ( choice.equalsIgnoreCase ( "y" ) ) {
            System.out.print ( "\nYour bet is : $ " );
            double bet = Double.parseDouble ( sc.nextLine () );

            while ( bet < 5 || bet > 1001 ) {
                System.out.println ( "Please put a bet between $5.00 and $1,000.00 !" );
                System.out.print ( "\nYour bet is : $ " );
                bet = Double.parseDouble ( sc.nextLine () );
            }

            if ( bet > 6 || bet < 1001 && bet <= startingMoney ) {
                int result = (int)Math.round ( Math.random () * 100 );

                if ( result > 96 ) {
                    startingMoney = startingMoney + ( bet + ( bet / 2 ) );
                    String startingMoneyString = currency.format(startingMoney);
                    System.out.println ( "You got a BLACKJACK !" );
                    System.out.println ( "Total Money in Bank : " + startingMoneyString );
                } else if ( result < 95 && result > 59 ) {
                    startingMoney = startingMoney + bet;
                    String startingMoneyString = currency.format(startingMoney);
                    System.out.println ( "You won !" );
                    System.out.println ( "Total Money in Bank : " + startingMoneyString );
                } else if ( result < 58 && result > 50 ) {
                    String startingMoneyString = currency.format(startingMoney);
                    System.out.println ( "You pushed." );
                    System.out.println ( "Total Money in Bank : " + startingMoneyString );
                } else {
                    startingMoney = startingMoney - bet;
                    String startingMoneyString = currency.format(startingMoney);
                    if ( startingMoney < 5 ) {
                        System.out.println ( "You lost. \nDang it ! You have less than $5.00 in the bank, " +
                                "therefore you can't bet the minimum $5.00. You can't play anymore. " +
                                "\nTotal Money in Bank : " + startingMoneyString + "\n\nBye bye now !" );
                        break;
                    } else {
                        System.out.println ( "You lost." );
                        System.out.println ( "Total Money in Bank : " + startingMoneyString );
                    }
                }
            }

            System.out.print ( "\nContinue? (Press y (for yes) or n (for no) & Enter) : " );
            choice = sc.nextLine ();

            if ( choice.equalsIgnoreCase ( "n" ) ) {
                System.out.println ( "You have quit the game. \n \nBye bye now !" );

            }
        }
    }
}
