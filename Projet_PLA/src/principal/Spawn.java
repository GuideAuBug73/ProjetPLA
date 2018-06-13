package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;

public class Spawn extends IA {
	boolean presence = false;
	BufferedImage[] m_sprites;
	int m_w, m_h;
	Model m_model;
	int x,y;
	int m_idx;
	int m_cpt;
	boolean m_spawnEnnemi;

	public Spawn(Cellule c, BufferedImage image, Model model) {
		m_idx = 2;
		img = image;
		m_model = model;
		x = c.x;
		y = c.y;
		m_cpt = 0;
		m_spawnEnnemi = false;
		splitSprite();
	}

	public Spawn(int x, int y, BufferedImage image, Model model) {
		m_idx = 2;
		m_model = model;
		img = image;
		this.x = x;
		this.y = y;
		m_cpt = 0;
		m_spawnEnnemi = false;
		splitSprite();
	}
	
	public void splitSprite() {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        m_sprites = new BufferedImage[4 * 4];
        m_w = width / 3;
        m_h = height / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                int x = j * m_w;
                int y = i * m_h;
                m_sprites[(i * 3) + j] = img.getSubimage(x, y, m_w, m_h);
            }
        }
    }

	public void paint(Graphics g) {
		m_cpt++;
		if(m_cpt%10 == 0 && m_idx != 11) {
			m_idx=(m_idx+3)%12;
			m_spawnEnnemi = false;
		}
		g.drawImage(m_sprites[m_idx], x, y, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
	}
}
