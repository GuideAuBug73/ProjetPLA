
package principal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Spell {
    BufferedImage m_sprite;
    int m_y;
    int m_x;
    Model m_model;
    boolean casted = false;
    int istenilenx, istenileny;

    public Spell(Model model, BufferedImage sprite) {
        m_model = model;
        m_sprite = sprite;
       


    }

    public void setcast(int harry_x, int harry_y, int x, int y) {
        casted = true;
        istenilenx = x +25;
        istenileny = y +25;
        m_x = harry_x + 0;
        m_y = harry_y + 0;

    }

    public void cast() {

        if (m_x < istenilenx)
            m_x = m_x + 20;
        if (m_y < istenileny)
            m_y = m_y + 20;
        if (m_x > istenilenx)
            m_x = m_x - 20;
        if (m_y > istenileny)
            m_y = m_y - 20;

        if (Math.abs(m_x - istenilenx) < 20 && Math.abs(m_y - istenileny) < 20)
            casted = false;


    }

    public void paint(Graphics g) {
        if (casted == true)
            g.drawImage(m_sprite, m_x - 50, m_y - 50, 100, 100, null);
    }

}