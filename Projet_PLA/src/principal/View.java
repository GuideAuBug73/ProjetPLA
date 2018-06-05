package principal;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;


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

		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		Map carte = m_model.m_carte;
		carte.paint(g);

	    m_model.m_spell.cast();
	    
	      Personnage h = m_model.m_harry;
	      h.paint(g);
	        
	      spell ss = m_model.m_spell;
	      ss.paint(g);
	
	}
}
