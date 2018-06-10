
package principal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import automate._Automate;
import automate._Move;
import automate._State;
import automate._Transition;
import edu.ricm3.game.GameModel;


public class Model extends GameModel {
    Joueur m_perso;
    Ennemi m_ennemi ;
    BufferedImage m_ennemiSprite;

    BufferedImage m_fieldSprite;
    BufferedImage m_wallSprite;
    BufferedImage m_persoSprite;
    spell m_spell;
    BufferedImage m_spellSprite;
    BufferedImage[] m_itemSprite=new BufferedImage[12];
    Item[] m_item = new Item[10];
    Random rand = new Random();
    Map m_carte;
    _Automate a;

    public Model() {
        loadSprites();
        createMap();
        createItem();
        createPerso();
        createEnnemi();
       // createAutomate();
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
    	//a.trans[0].act.execute(m_ennemi);
    }


    private void loadSprites() {
        /*
         * Cowboy with rifle, western style; png; 48x48 px sprite size
         * Krasi Wasilev ( http://freegameassets.blogspot.com)
         */
        File imageFile = new File("src/sprites/hero.png");
        try {
            m_persoSprite = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
         imageFile = new File("src/sprites/Enemi.png");

        try {
            m_ennemiSprite = ImageIO.read(imageFile);
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
        imageFile = new File("src/sprites/shuriken.png");
        try {
            m_itemSprite[0] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/beer.png");
        try {
            m_itemSprite[1] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/piano.png");
        try {
            m_itemSprite[2] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/frigo.png");
        try {
            m_itemSprite[3] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/laser.png");
        try {
            m_itemSprite[4] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/lance.png");
        try {
            m_itemSprite[5] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        int test=(int)(Math.random()*5);
        m_itemSprite[5] =m_itemSprite[test];
        test=(int)(Math.random()*5);
        m_itemSprite[6] =m_itemSprite[test];

        imageFile = new File("src/sprites/vie.png");
        try {
            m_itemSprite[8] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/vitesse.png");
        try {
            m_itemSprite[9] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/vitesse.png");
        try {
            m_itemSprite[10] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("src/sprites/invincible.png");
        try {
            m_itemSprite[11] = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public void createMap() {
        m_carte = new Map(Options.nb_cell_h, Options.nb_cell_w, Options.nb_px_y_max - Options.nb_px_y_min, Options.nb_px_x_max - Options.nb_px_x_min, m_wallSprite, m_fieldSprite);

    }

    private void createItem() {
        for (int i = 0; i < 2; ) {
            int x = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
            int y = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;
            int type=(int)(Math.random()*7);
            if(m_carte.cellules[y][x].libre && m_carte.cellules[y][x].entité==null) {
                m_item[i] = new Item(type, x * Options.TAILLE_CELLULE, y * Options.TAILLE_CELLULE, m_itemSprite[type],this);
                m_carte.cellules[y][x].entité=m_item[i];
                i++;
            }
        }
    }
    private void createPerso() {
        for (int i = 0; i < 1; ) {
            int x = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
            int y = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;
            if (m_carte.cellules[y][x].libre && m_carte.cellules[y][x].entité==null) {
                m_perso = new Joueur(this, m_persoSprite, x*Options.TAILLE_CELLULE, y*Options.TAILLE_CELLULE, 1.3F);
                m_carte.cellules[y][x].entité=m_perso;
                i++;
            }
        }
    }
    
    private void createEnnemi() {
        for (int i = 0; i < 1; ) {
        	int x = 0;//(int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
            int y = 0;//(int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;
            if (m_carte.cellules[y][x].libre) {
                System.out.println(x+"et y :"+y);
                m_ennemi = new Ennemi(this, m_ennemiSprite, 0, 0, 1.3F);
                i++;
            }
        }
    }
    
   /* private void createAutomate() {
    	_Move m = new _Move();
    	_State state1 = new _State("0");
    	_Transition t = new _Transition(state1,state1,true,m);
    	_Transition t1[] = {t};
    	a = new _Automate(state1,t1);
    }*/
}
