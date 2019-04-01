import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

public class Main extends AppGameContainer {

    public Main(Game game, int width, int height, boolean fullscreen) throws SlickException {
        super(game, width, height, fullscreen);
    }

    public static void main(String[] args) {
        try {
            RaceGame raceGame = new RaceGame("Race Game");
            Main game = new Main(raceGame, 640, 480, false);
            game.setShowFPS(false);
            game.start();
            raceGame.init(game);
            while (game.running) {
                raceGame.update(game, game.getDelta());
                raceGame.render(game, game.getGraphics());
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}