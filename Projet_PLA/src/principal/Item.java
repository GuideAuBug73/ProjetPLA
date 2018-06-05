package principal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {
    public int type;
    public int w;
    public int h;
    BufferedImage

    public Item(int type) {
        this.type = type;
    }
    public Item(int type,int w, int h){
        this.type=type;
        this.w=w;
        this.h=h;
    }


    public void paint(Graphics g) {
        g.drawImage(img, cellules[i][j].x, cellules[i][j].y + map_start, m_sizew / m_w, m_sizeh / m_h, null);

    }

}
