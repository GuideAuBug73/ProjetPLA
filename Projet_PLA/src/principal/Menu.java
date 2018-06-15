package principal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {
    Model m_model;
    BufferedImage m_img[];

    public Menu(Model m, BufferedImage img[]) {
        m_model = m;
        m_img = img;
    }

    public void paint(Graphics g) {
        int x = Options.d.width -m_img[0].getWidth();
        int y =Options.d.height- m_img[0].getHeight();
        g.drawImage(m_img[0], x, y, Options.d.width - x, Options.d.height-y, null);
        g.drawImage(m_img[1], (Options.d.width / 2) - (((BufferedImage) m_img[1]).getWidth() / 2), (Options.d.height / 4) - (((BufferedImage) m_img[1]).getHeight() / 2), ((BufferedImage) m_img[1]).getWidth(), ((BufferedImage) m_img[1]).getHeight(), null);
        g.drawImage(m_img[7], (Options.d.width / 2) - (((BufferedImage) m_img[1]).getWidth() / 2), (Options.d.height / 4) + ((BufferedImage) m_img[1]).getHeight(), ((BufferedImage) m_img[1]).getWidth(), ((BufferedImage) m_img[1]).getHeight(), null);
        g.drawImage(m_img[2],Options.d.width/2-125,Options.d.height/2+100,100,25,null);
        g.drawImage(m_img[3],Options.d.width/2-125,Options.d.height/2+200,100,25,null);
        g.drawImage(m_img[4],Options.d.width/2-125,Options.d.height/2+300,60,25,null);
        g.drawImage(m_img[5],Options.d.width/2-150,Options.d.height/2+50,300,25,null);
        g.drawImage(m_img[6],50,50,600,200,null);

    }
}
