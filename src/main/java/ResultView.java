import java.util.ArrayList;
import java.util.List;

public class ResultView {

    public void printPlayerCards(Player player) {
        System.out.printf("%s카드: ", player.getPlayerName());
        System.out.println(resolvePlayerCards(player));
    }

    private String resolvePlayerCards(Player player) {
        List<String> cards = new ArrayList<>();
        for (Card card : player.getCards()) {
            CardNumber cardNumber = card.getCardNumber();
            CardShape cardShape = card.getCardShape();
            cards.add(resolveCardNumber(cardNumber) + cardShape.getShape());
        }
        return String.join(", ", cards);
    }

    private String resolveCardNumber(CardNumber cardNumber) {
        if (cardNumber.equals(CardNumber.A) || cardNumber.equals(CardNumber.J)
                || cardNumber.equals(CardNumber.Q) || cardNumber.equals(CardNumber.K)) {
            return cardNumber.name();
        }
        return String.valueOf(cardNumber.getScore());
    }
}