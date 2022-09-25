package blackjackapplication.blackjack;

public class Card {
    private final String suite;
    private final String rank;
    private final int points;

    public Card (String suite, String rank, int points) {
        this.suite = suite;
        this.rank = rank;
        this.points = points;
    }

    /*public Card (Card c) {
        this.suite = c.suite;
        this.rank = c.rank;
        this.points = c.points;
    }*/

    /*public String getSuite () {
        return suite;
    }

    public String getRank () {
        return rank;
    }*/

    public int getPoints () {
        return points;
    }

    public boolean isAce () {
        return rank.equalsIgnoreCase ( "ace" );
    }

    public String display () {
        return this.rank + " of " + this.suite;
    }
}