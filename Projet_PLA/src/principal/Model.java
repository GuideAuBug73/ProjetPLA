package principal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import basic.Position;
import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	Personnage perso;
	BufferedImage m_cowboySprite;
	BufferedImage m_explosionSprite;
	BufferedImage m_fieldSprite;
	BufferedImage m_wallSprite;
	BufferedImage squelette_sprite;
	Map m_carte;
	Ennemi m_ennemi[] = new Ennemi[10];
	Position p; // test
	boolean boss; // test

	Random rand = new Random();

	public Model() {
		loadSprites();
		m_carte = new Map(10, 10, 700, 1000, m_wallSprite, m_fieldSprite);

		for (int i = 0; i < 10; i++) {
			boss = false;
			p = new Position(i*100+25,i*70+15);
			m_ennemi[i] = new Ennemi(this,10, 10, 700, 1000,boss, p, squelette_sprite, 3F);
		}
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
		/*
		 * Cowboy with rifle, western style; png; 48x48 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */
		File imageFile = new File("src/sprites/winchester.png");
		try {
			m_cowboySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Long explosion set; png file; 64x64 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */
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

		imageFile = new File("src/sprites/squelette.png");
		try {
			squelette_sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

}