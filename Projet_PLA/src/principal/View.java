package principal;

import edu.ricm3.game.GameView;

import java.awt.*;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.black;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	Controller m_ctr;

	public View(Model m, Controller c) {
		m_model = m;
		m_ctr = c;
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();

		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());
		Map carte = m_model.m_carte;
		carte.paint(g);
		Item[] item = m_model.m_item;
		Bonus[] bonus = m_model.m_bonus;
		for (int i = 0; i < item.length; i++) {
			if (item[i] != null) {
				m_model.m_item[i].splitSprite();
				item[i].paint(g);
			}
		}
		for (int i = 0; i < bonus.length; i++) {
			if (bonus[i] != null) {
				bonus[i].paint(g);
			}
		}
		m_model.m_perso.projectile.paint(g);
		m_model.m_perso.animation();
		Personnage h = m_model.m_perso;
		h.paint(g);
		Ennemi k;
		Spawn spawn;
		for (int i = 0; i < 4; i++) {
			spawn = m_model.m_spawns[i];
			spawn.paint(g);
		}

		for (int i = 0; i < m_model.totalennemie; i++) {
			k = m_model.m_ennemis[i];
			if (k != null)
				k.paint(g);
		}

		h.paint(g);
		if (Options.vague == 5) {
			m_ctr.b = m_model.m_boss;
			Boss boss = m_model.m_boss;
			boss.paint(g);
			boss.projectile.paint(g);
			boss.animation();
		}
		if (Options.level == 4 || m_model.m_perso.p_vie == 0) {
			g.drawImage(m_model.m_fieldSprite4, 0, 0, 1400, 700, null);
		}
	}

	protected void _paint_inventaire(Graphics g) {
		if (Options.level == 4 || m_model.m_perso.p_vie == 0) {
			g.setColor(m_background);
			g.fillRect(0, 0, 10000, 10000);
		} else {
			g.setColor(m_background);
			g.fillRect(Options.d.width - Options.nb_px_x_max, 0, Options.d.width, Options.d.height);
			int y = Options.d.height / 2 - (Options.TAILLE_CELLULE + 20) * 4;
			int x = (Options.d.width - Options.nb_px_x_max - Options.TAILLE_CELLULE) / 2 + Options.nb_px_x_max - 20;
			for (int i = 0; i < 8; i++) {
				g.drawImage(m_model.m_carre_inventaire, x, y, 100, 100, null);
				y += 80;
			}
			Inventaire inv = m_model.m_perso.inventaire;
			Inventaire.Iterator iter = inv.iterator();
			y = Options.d.height / 2 - (Options.TAILLE_CELLULE + 20) * 4 + 30;
			while (iter.hasNext()) {
				if (iter.courante.number % 2 == 0) {
					x = (Options.d.width - Options.nb_px_x_max - Options.TAILLE_CELLULE) / 2 + Options.nb_px_x_max + 6
							- 8 * (iter.courante.number / 2 - 1);
				} else {
					x = (Options.d.width - Options.nb_px_x_max - Options.TAILLE_CELLULE) / 2 + Options.nb_px_x_max + 10
							- 8 * (iter.courante.number / 2);
				}

				for (int i = 0; i < iter.courante.number; i++) {
					iter.courante.elem.paint(g, x, y);
					x += 8;
				}
				y += 80;
				iter.next();
			}

		}
	}

	@Override
	protected void _paint_player(Graphics g) {
		g.setColor(m_background);
		g.fillRect(0, 0, Options.d.width, Options.nb_px_y_min);
		int pdv = m_model.m_perso.p_vie;
		int x = 50;
		for (int i = 0; i < pdv; i++) {
			g.drawImage(m_model.m_itemSprite[8], x, Options.nb_px_y_min / 2 + 30 - (Options.TAILLE_CELLULE - 20) / 2,
					Options.TAILLE_CELLULE - 20, Options.TAILLE_CELLULE - 20, null);
			x += 50;
		}
	}

	@Override
	protected void _paint_level(Graphics g) {
		if (Options.level == 4 || m_model.m_perso.p_vie == 0) {
			g.setColor(m_background);
			g.fillRect(0, 0, 10000, 10000);
		} else {
			g.setColor(m_background);
			g.fillRect(0, Options.nb_px_y_max, Options.d.width, Options.d.height);
			g.setColor(Color.RED);
			g.fillRect(20, (Options.d.height - Options.nb_px_y_max) / 2 - 12, Options.d.width / 2 - 171, 24);
			g.setColor(m_background);
			g.fillRect(22, (Options.d.height - Options.nb_px_y_max) / 2 - 10, Options.d.width / 2 - 175, 20);
			g.setColor(Color.GRAY);
			g.fillRect(22, (Options.d.height - Options.nb_px_y_max) / 2 - 10, (int) Options.time_vague, 20);
			Options.time_vague += 5;
			if (m_model.m_perso.m_mort && m_model.m_perso.p_vie != 0
					&& Options.timer_mort == Options.d.width / 2 - 175) {
				Options.timer_mort = Options.time_vague;
			}
			if ((Options.time_vague == Options.timer_mort + 30 || Options.time_vague == Options.d.width / 2 - 175)
					&& Options.timer_mort != Options.d.width / 2 - 175) {
				Options.time_vague = 0;
				m_model.m_perso.m_mort = false;
				m_model.m_ennemis = new Ennemi[70];
				m_model.createEnnemi();
				Options.timer_mort = Options.d.width / 2 - 175;
			}
			if (Options.time_vague == Options.d.width / 2 - 175) {
				Options.time_vague = 0;
				Options.vague++;
				m_model.createItem();
				m_model.createBonus();
				m_model.createEnnemi();
				if (Options.vague == 6) {
					Options.level++;
					Options.vague = 1;
					m_model.newlevel();

				}
				if (Options.vague == 5) {
					m_model.totalennemie = 0;
					m_model.createboss();
					m_ctr.b = m_model.m_boss;
				}
			}

			if (Options.vague == 5) {
				m_model.totalennemie = 0;
				m_model.createboss();
				m_ctr.b = m_model.m_boss;
			}
		}
	}

	@Override
	protected void _paint_menu(Graphics g) {
		m_model.m_menu.paint(g);
	}
}
