package principal;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.gray;
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
		int i;
		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());
		Map carte = m_model.m_carte;
		carte.paint(g);
		Ennemi[] ennemi = m_model.m_ennemi;
		for(i=0;i<10;i++) {
			ennemi[i].paint(g);
		}
		
		
		// Paint our model, grabbing the elements,
		// in our case, the squares.
	}
}
