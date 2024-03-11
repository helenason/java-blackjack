package domain.cards;

import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    public Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public boolean isNotAce() {
        return !cardNumber.equals(CardNumber.ACE);
    }

    public int score() {
        return cardNumber.getScore();
    }

    //  TODO: ace 점수 결정은 누구 책임? PlayerCards or Card(Number)
    public int decideAceScore(int totalScore) {
        return cardNumber.decideAceScore(totalScore);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardNumber == card.cardNumber && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardShape);
    }
}
