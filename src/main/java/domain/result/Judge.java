package domain.result;

import domain.WinState;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import java.util.Map;

public class Judge {

    private final PlayersResult playersResult;
    private final DealerResult dealerResult;

    public Judge() {
        this.playersResult = new PlayersResult();
        this.dealerResult = new DealerResult();
    }

    public void decideResult(Gamers gamers) {
        decidePlayersResult(gamers.getPlayers(), gamers.getDealer());
        decideDealerResult();
    }

    private void decidePlayersResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            decidePlayerResult(player, dealer);
        }
    }

    private void decidePlayerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            playersResult.addResult(player, WinState.LOSE);
            return;
        }
        if (dealer.isBust()) {
            playersResult.addResult(player, WinState.WIN);
            return;
        }
        WinState playerWinState = decidePlayerWinState(player, dealer);
        playersResult.addResult(player, playerWinState);
    }

    private WinState decidePlayerWinState(Player player, Dealer dealer) {
        int playerScore = player.finalizeCardsScore();
        int dealerScore = dealer.finalizeCardsScore();
        if (playerScore > dealerScore) {
            return WinState.WIN;
        }
        if (playerScore < dealerScore) {
            return WinState.LOSE;
        }
        return WinState.DRAW;
    }

    private void decideDealerResult() {
        dealerResult.addResult(WinState.WIN, playersResult.countWinState(WinState.LOSE));
        dealerResult.addResult(WinState.DRAW, playersResult.countWinState(WinState.DRAW));
        dealerResult.addResult(WinState.LOSE, playersResult.countWinState(WinState.WIN));
    }

    public Map<Player, WinState> getPlayersResult() {
        return playersResult.getResult();
    }

    public Map<WinState, Integer> getDealerResult() {
        return dealerResult.getResult();
    }
}