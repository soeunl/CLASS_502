package test0501;

public class Card {
    private Card cardNumber = new Card();
    private static int number = 1000;

    private Card () {
        number++;
    };

    public Card getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Card cardNumber) {
        this.cardNumber = cardNumber;
    }
}
