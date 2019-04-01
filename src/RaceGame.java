import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import java.util.*;

public class RaceGame extends BasicGame {
    private Input input;
    private Player player;
    private List<Obstacle> obstacleList = new ArrayList<>();
    private World world;
    Music music = new Music("music/Linkin Park - New Divide (8-Bit Version Full).wav");

    public RaceGame(String title) throws SlickException {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        input = gameContainer.getInput();
        int x = gameContainer.getWidth() / 2;
        int y = gameContainer.getHeight() - 50;
        player = new Player(new Circle(x, y, 15), x, y, gameContainer);
        world = new World(obstacleList, player, gameContainer.getWidth(), gameContainer.getHeight());
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (input.isKeyDown(Input.KEY_LEFT)) {
            player.setDirection(-5);
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            player.setDirection(5);
        } else {
            player.setDirection(0);
        }
        if (!player.dead) {
            if (player.points % 50 == 0 && player.points != 0){
                world.updateSpeed();
                player.updateScore();
            }
            player.update();
            world.update();
            if (!music.playing()) {
                music.play();
                music.loop();
            }
        } else {
            music.stop();
        }
        if (player.dead && player.lifes >= 0){
            player.lifes--;
            player.dead = player.lifes < 0;
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (!player.dead) {
            player.render(graphics);
            world.render(graphics);
        } else {
            String mensaje = "You're dead, press enter to restart";
            graphics.drawString(mensaje, (gameContainer.getWidth() / 2) - mensaje.length() * 4, gameContainer.getHeight() / 2);
            restartGame();
        }
        int lifes = player.lifes == -1 ? 0 : player.lifes;
        graphics.drawString("Score: " + player.getScore(), 10, 10);
        graphics.drawString("Lifes: " + lifes, 550, 10);
    }

    public void restartGame() {
        if (input.isKeyDown(Input.KEY_ENTER)) {
            player.points = 0;
            world.speed = 1;
            world.init();
            player.dead = false;
            player.lifes = 3;
        }
    }
}