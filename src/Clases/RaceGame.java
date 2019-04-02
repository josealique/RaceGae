package Clases;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import java.util.*;

public class RaceGame extends BasicGame {
    private Input input;
    private Player player;
    private List<Obstacle> obstacleList = new ArrayList<>();
    private World world;
    private boolean pause = false;
    private Music music = new Music("music/Linkin Park - New Divide (8-Bit Version Full).wav");

    // Constructor
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
       // El juego se pausará siempre y cuando pulsemos la tecla
        // y el jugador no esté muerto
        if (input.isKeyPressed(Input.KEY_SPACE) && !player.isDead()) {
            pause = !pause;
        }
        // Controles de direcciones: izquierda y derecha
        if (input.isKeyDown(Input.KEY_LEFT)) {
            player.setDirection(-8);
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            player.setDirection(8);
        } else {
            player.setDirection(0);
        }
        // En esta condición nos encargamos de aumentar la puntuación
        // y la velocidad
        if (!player.isDead() && !pause) {
            if (player.getPoints() % 50 == 0 && player.getPoints() != 0) {
                world.updateSpeed();
                player.updateScore();
            }
            player.update();
            world.update();
            // Música del juego
            if (!music.playing()) {
                music.play();
                music.loop();
            }
        } else {
            music.stop();
        }
        // Si el jugador muere le restamos vidas, también hay que
        // comprobar si aún tenemos vidas
        if (player.isDead() && player.getLifes() >= 0) {
            player.setLifes(player.getLifes() - 1);
            player.setDead(player.getLifes() < 0);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        // Mientras el jugador no esté muerto y el juego no esté pausado
        // pintamos el jugador y el mundo
        if (!player.isDead() && !pause) {
            player.render(graphics);
            world.render(graphics);
        }
        // Juego en pausa
        else if (pause) {
            String mensaje = "Game Paused";
            graphics.drawString(mensaje, (gameContainer.getWidth() / 2) - mensaje.length() * 4, gameContainer.getHeight() / 2);
        }
        // El jugado ha muerto
        else {
            String mensaje = "You're dead, press enter to restart";
            graphics.drawString(mensaje, (gameContainer.getWidth() / 2) - mensaje.length() * 4, gameContainer.getHeight() / 2);
            restartGame();
        }
        // Pintamos en pantalla las vidas del jugador y su puntuación
        int lifes = player.getLifes() == -1 ? 0 : player.getLifes();
        graphics.drawString("Score: " + player.getScore(), 10, 10);
        graphics.drawString("Lifes: " + lifes, 550, 10);
    }

    // Método que se encarga de reiniciar el juego, si el jugador
    // muere, reiniciamos todos los valores
    public void restartGame() {
        if (input.isKeyDown(Input.KEY_ENTER)) {
            player.setPoints(0);
            world.setSpeed(1);
            world.init();
            player.setDead(false);
            player.setLifes(3);
        }
    }
}