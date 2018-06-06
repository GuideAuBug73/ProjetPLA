
package principal;

import edu.ricm3.game.GameModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Model extends GameModel {
    Personnage m_perso;
    BufferedImage m_fieldSprite;
    BufferedImage m_wallSprite;
    BufferedImage m_persoSprite;
    spell m_spell;
    BufferedImage m_spellSprite;
    BufferedImage m_itemBeer;
    BufferedImage m_itemPepsi;
    BufferedImage m_itemCake;
    BufferedImage m_itemPizza;
    Map m_carte;
    Item[] m_item = new Item[10];
    Random rand = new Random();

    public Model() {
        loadSprites();
        createMap();
        createItem();
        createPerso();
        m_spell = new spell(this, m_spellSprite, 0, 0);
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
        /*
         * Cowboy with rifle, western style; png; 48x48 px sprite size
         * Krasi Wasilev ( http://freegameassets.blogspot.com)
         */
        File imageFile = new File("src/sprites/winchester.png");
        try {
            m_persoSprite = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        /*
         * Long explosion set; png file; 64x64 px sprite size
         * Krasi Wasilev ( http://freegameassets.blogspot.com)
         */
        imageFile = new File("src/sprites/explosion01_set_64.png");
        try {
            m_spellSprite = ImageIO.read(imageFile);
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

        //  <----------------------------------------------------->
        imageFile = new File("src/sprites/beer.png");
        try {
            m_itemBeer = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/pizza.png");
        try {
            m_itemPizza = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/cake.png");
        try {
            m_itemCake = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/pepsi.png");
        try {
            m_itemPepsi = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public void createMap() {
        m_carte = new Map(Options.nb_cell_h, Options.nb_cell_w, Options.nb_px_y_max - Options.nb_px_y_min, Options.nb_px_x_max - Options.nb_px_x_min, m_wallSprite, m_fieldSprite);

    }

    public void createItem() {
        for (int i = 0; i < 2; ) {
            int x = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
            int y = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;
            if(m_carte.cellules[y][x].libre) {
                m_item[i] = new Item(1, x * Options.TAILLE_CELLULE, y * Options.TAILLE_CELLULE, m_itemPepsi);
                i++;
            }
        }
    }
    public void createPerso() {
        for (int i = 0; i < 1; ) {
            int x = (int) (Math.random() * 29);
            int y = (int) (Math.random() * 15);
            if (m_carte.cellules[y][x].libre) {
                System.out.println(x+"et y :"+y);
                m_perso = new Personnage(this, m_persoSprite, x*Options.TAILLE_CELLULE, y*Options.TAILLE_CELLULE, 1.3F);
                i++;
            }
        }
    }
}