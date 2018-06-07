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
        for (int i = 0; i < item.length; i++) {
            if (item[i] != null)
                item[i].paint(g);
        }
        m_model.m_spell.cast();
        Personnage h = m_model.m_perso;
        h.paint(g);
        Ennemi k = m_model.m_ennemi;
        k.paint(g);
        spell ss = m_model.m_spell;
        ss.paint(g);
    }

    @Override
    protected void _paint_inventaire(Graphics g) {
        g.setColor(m_background);
        g.fillRect(Options.d.width-Options.nb_px_x_max, 0, Options.d.width, Options.d.height);
        int y = 100;
        int x = (Options.d.width - Options.nb_px_x_max - 60) / 2 + Options.nb_px_x_max;
        Inventaire inv = m_model.m_perso.inventaire;
        Inventaire.Iterator iter=inv.iterator();
        while (iter.hasNext()) {
            x = (Options.d.width - Options.nb_px_x_max - 60) / 2 + Options.nb_px_x_max;
            for(int i=0;i<iter.courante.number;i++) {
                iter.courante.elem.paint(g, x, y);
                x+=10;
            }
                y += 60;
                iter.next();
            }

        }
}
