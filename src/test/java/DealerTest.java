import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("카드를 한 장 받는다.")
    @Test
    void hitOneCard() {
        Dealer dealer = new Dealer(new ArrayList<>());
        Card newCard = new Card(CardNumber.FIVE, CardShape.CLOVER);
        dealer.hit(newCard);

        assertThat(dealer.getCards()).contains(newCard);
    }

    @DisplayName("점수가 16 이하인지 알려준다.")
    @Test
    void checkCanDealerHit() {
        Dealer dealer = new Dealer(List.of(
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.THREE, CardShape.HEART)));
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("점수가 21 이하인지 알려준다.")
    @Test
    void checkUnderBustThreshold() {
        Dealer dealer = new Dealer(List.of(
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.THREE, CardShape.HEART)));
        assertThat(dealer.isNotBust()).isTrue();
    }

    @DisplayName("점수가 21 초과인지 알려준다.")
    @Test
    void checkOverBustThreshold() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardNumber.TWO, CardShape.CLOVER));
        cards.add(new Card(CardNumber.THREE, CardShape.HEART));

        Dealer dealer = new Dealer(cards);
        dealer.hit(new Card(CardNumber.K, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.K, CardShape.SPADE));

        assertThat(dealer.isNotBust()).isFalse();
    }
}