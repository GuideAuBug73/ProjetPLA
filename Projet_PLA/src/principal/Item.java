package principal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {
    public int type;
    public int x;
    public int y;
    BufferedImage

    public Item(int type) {
        this.type = type;
    }
    public Item(int type,int x, int y){
        this.type=type;
        this.x=x;
        this.y=y;
    }


    public void paint(Graphics g) {
        g.drawImage(img, cellules[i][j].x, cellules[i][j].y + map_start, m_sizew / m_w, m_sizeh / m_h, null);

    }

}
