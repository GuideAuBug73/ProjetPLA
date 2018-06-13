
package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class fire {
    BufferedImage m_sprite;
    BufferedImage m_sprites[];
    int m_y;
    int m_x;
    Model m_model;
    boolean casted = false;
    int istenilenx, istenileny;
    int m_w,m_h;
    int m_idx=5;
    
    public fire(Model model, BufferedImage sprite) {
        m_model = model;
        m_sprite = sprite;
		splitSprite();

    }
    
    public void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[8 * 6];
		m_w = width / 8;
		m_h = height / 6;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * 6) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}


    public void setcastfire() {
    	
    	if(casted==false) {
        	
        int x=m_model.m_boss.x;
        int y=m_model.m_boss.y;
        casted = true;
        m_idx=1;
        if (m_model.m_boss.orientation == 0) {istenilenx=x+25;istenileny=y+425;}
		if (m_model.m_boss.orientation == 1) {istenilenx=x+425;istenileny=y+25;}
		if (m_model.m_boss.orientation == 2) {istenilenx=x-425;istenileny=y+25;}
		if (m_model.m_boss.orientation == 3) {istenilenx=x+25;istenileny=y-425;}
			
    
        m_x = x;
        m_y = y;
    	}
    	else {
    	castfire();
    	}
    }

    public void castfire() {
m_idx=m_idx+2;
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
    	Image img = m_sprites[m_idx];
        if (casted == true)
            g.drawImage(img, m_x - 20, m_y - 20, 80, 80, null);
    }

}