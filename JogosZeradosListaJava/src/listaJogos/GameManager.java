package listaJogos;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Game> gameList;

    public GameManager() {
        gameList = new ArrayList<>();
    }

    public void addGame(Game game) {
        gameList.add(game);
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void removeGame(Game game) {
        gameList.remove(game);
    }

    public void editGame(int index, Game newGame) {
        gameList.set(index, newGame);
    }
}
