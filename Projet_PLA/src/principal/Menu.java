package principal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {
    Model m_model;
    BufferedImage m_img;
    BufferedImage m_img2;

    public Menu(Model m,BufferedImage img, BufferedImage img2){
        m_model=m;
        m_img=img;
        m_img2=img2;
    }

    public void paint(Graphics g){

        g.drawImage(m_img, 0, 0, Options.d.width, Options.d.height, null);
        g.drawImage(m_img2, (Options.d.width / 2) - (((BufferedImage) m_img2).getWidth() / 2), (Options.d.height / 4) - (((BufferedImage) m_img2).getHeight() / 2), ((BufferedImage) m_img2).getWidth(), ((BufferedImage) m_img2).getHeight(), null);
        g.drawImage(m_img2, (Options.d.width / 2) - (((BufferedImage) m_img2).getWidth() / 2), (Options.d.height / 4) + ((BufferedImage) m_img2).getHeight(), ((BufferedImage) m_img2).getWidth(), ((BufferedImage) m_img2).getHeight(), null);
    }
}
