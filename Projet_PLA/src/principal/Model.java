package principal;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Model extends GameModel {
    Personnage perso;
    BufferedImage m_cowboySprite;
    BufferedImage m_explosionSprite;
    BufferedImage m_fieldSprite;
    BufferedImage m_wallSprite;
    Map m_carte;
    Random rand = new Random();

    public Model() {
        loadSprites();
    }

    @Override
    public void shutdown() {

    }


    /**
     * Simulation step.
     *
     * @param now is the current time in milliseconds.
     */
    @Override
    public void step(long now) {


    }

    private void loadSprites() {
        File imageFile = new File("src/sprites/winchester.png");
        try {
            m_cowboySprite = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/explosion01_set_64.png");
        try {
            m_explosionSprite = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/field.png");
        try {
            m_fieldSprite = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/wall.png");
        try {
            m_wallSprite = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public void createMap(GameView v, int h, int w) {
        int sizeh = v.getHeight();
        int sizew = v.getWidth();
        m_carte = new Map(h, w, sizeh, sizew, m_wallSprite, m_fieldSprite);
    }

}