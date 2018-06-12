
package principal;

import edu.ricm3.game.GameModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Model extends GameModel {
    BufferedImage m_ennemiSpriteMort;
	boss m_boss;
	Personnage m_perso;
	Ennemi m_ennemi;
	Ennemi[] m_ennemis = new Ennemi[20];
	Spawn[] m_spawns;
	BufferedImage m_ennemiSprite;
	BufferedImage m_ennemiItemSprite;
	BufferedImage m_mort;
	BufferedImage m_fieldSprite;
	BufferedImage m_wallSprite;
	BufferedImage m_persoSprite;
	BufferedImage m_carre_inventaire;
	BufferedImage m_spawnSprite;
	BufferedImage m_bossSprite;
	Spell m_spell;
	BufferedImage m_spellSprite;
	BufferedImage[] m_itemSprite = new BufferedImage[12];
	Item[] m_item = new Item[10];
	Random rand = new Random();
	Map m_carte;
	int sx[] = new int[4];
	int sy[] = new int[4];
	int totalennemie = 0;

	public Model() {
		loadSprites();
		createMap();
		createItem();
		createSpawn();
		createPerso();
		createEnnemi();
		createboss();
		m_spell = new Spell(this, m_spellSprite, 0, 0);
	}

	@Override
	public void shutdown() {

	}

	/**
	 * Simulation step.
	 *
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {

	}

	private void loadSprites() {

		File imageFile = new File("src/sprites/hero.png");
		try {
			m_persoSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("src/sprites/phoenix.png");
		try {
			m_bossSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("src/sprites/mort.png");

		try {
			m_mort = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

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

        imageFile = new File("src/sprites/mortEnnemi.png");

        try {
            m_ennemiSpriteMort = ImageIO.read(imageFile);
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

		imageFile = new File("src/sprites/ennemi.png");

		try {
			m_ennemiSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// <----------------------------------------------------->
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

		imageFile = new File("src/sprites/bombe.png");
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
		int test = (int) (Math.random() * 5);
		m_itemSprite[6] = m_itemSprite[test];
		test = (int) (Math.random() * 5);
		m_itemSprite[7] = m_itemSprite[test];

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

		imageFile = new File("src/sprites/inv.png");
		try {
			m_carre_inventaire = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("src/sprites/spawn.png");
		try {
			m_spawnSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	public void createMap() {
		m_carte = new Map(Options.nb_cell_h, Options.nb_cell_w, Options.nb_px_y_max - Options.nb_px_y_min,
				Options.nb_px_x_max - Options.nb_px_x_min, m_wallSprite, m_fieldSprite);

	}

	public void createItem() {
		for (int i = 0; i < 2;) {
			int x = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
			int y = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;
			int type = (int) (Math.random() * 7);
			if (m_carte.cellules[y][x].libre && m_carte.cellules[y][x].entité == null) {
				m_item[i] = new Item(type, x * Options.TAILLE_CELLULE, y * Options.TAILLE_CELLULE, m_itemSprite[type],
						this);
				m_carte.cellules[y][x].entité = m_item[i];
				i++;
			}
		}
	}

	public void createPerso() {
		boolean test = true;
		for (int i = 0; i < 1;) {
			int x = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
			int y = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;

			for (int k = 0; k < 4; k++) {
				if (sx[k] == x && sy[k] == y)
					test = false;
			}

			if (m_carte.cellules[y][x].libre && m_carte.cellules[y][x].entité == null && test) {
				m_perso = new Personnage(this, m_persoSprite, x * Options.TAILLE_CELLULE, y * Options.TAILLE_CELLULE,
						1.3F);
				m_carte.cellules[y][x].entité = m_perso;
				i++;
			}
		}
	}

	public void createboss() {

		for (int i = 0; i < 1;) {
			int x = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
			int y = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;

			if (m_carte.cellules[y][x].entité == null && m_carte.cellules[y][x].libre) {
				m_boss = new boss(this, m_bossSprite, x * Options.TAILLE_CELLULE, y * Options.TAILLE_CELLULE, 0.85F);
				m_carte.cellules[y][x].entité = m_boss;
				i++;
			}
		}
	}

	public void createSpawn() {
		m_spawns = new Spawn[4];
		int i = 0;
		while (i < 4) {
			int x = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
			int y = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;
			if (m_carte.cellules[y][x].libre) {
				sx[i] = x;
				sy[i] = y;
				System.out.println(x + "et y :" + y);
				m_spawns[i] = new Spawn(x * Options.TAILLE_CELLULE, y * Options.TAILLE_CELLULE, m_spawnSprite, this);
				i++;
			}
		}
	}

	public void createEnnemi() {
		int i = 0;
		int k;

		if (Options.level == 1) {
			if (Options.vague == 1)
				k = 3;
			else
				k = 2;
		}
		if (Options.level == 2) {
			if (Options.vague == 1)
				k = 3;
			else
				k = 2;
		}
		if (Options.level == 2) {
			if (Options.vague == 1)
				k = 3;
			else
				k = 2;
		} else {
			if (Options.vague == 1)
				k = 3;
			else
				k = 2;
		}

		while (i < k) {
			m_ennemi = new Ennemi(this, m_ennemiSprite, sx[i] * Options.TAILLE_CELLULE + 4,
					sy[i] * Options.TAILLE_CELLULE + 13, 1.0F);
			m_ennemis[totalennemie] = m_ennemi;
			i++;
			totalennemie++;
		}
	}

}
