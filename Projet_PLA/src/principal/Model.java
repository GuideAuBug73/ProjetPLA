package principal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameView;


public class Model extends GameModel {
  BufferedImage m_harrySprite;
  BufferedImage m_explosionSprite;
  BufferedImage m_fieldSprite;
  BufferedImage m_wallSprite;
  BufferedImage m_spellSprite;
  Map m_carte;
  Random rand = new Random();
  spell m_spell;
  Personnage m_harry;


  public Model() {
    loadSprites();
    m_harry = new Personnage(this, m_harrySprite, 5 ,25 , 1.5F);
    m_spell= new spell(this,m_spellSprite,0,0);
  }
  
  @Override
  public void shutdown() {
    
  }

  
  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  @Override
  public void step(long now) {
    
    
  }

  private void loadSprites() {
	File    imageFile1 = new File("src/sprites/explosion01_set_64.png");
	File    imageFile2 = new File("src/sprites/field.png");
    File imageFile3 = new File("src/sprites/wall.png");
    File imageFile4 = new File("src/sprites/harry.png");
    File imageFile5 = new File("src/sprites/spell.png");

    try {
        m_explosionSprite = ImageIO.read(imageFile1);
        m_fieldSprite = ImageIO.read(imageFile2);
        m_wallSprite = ImageIO.read(imageFile3);
        m_harrySprite = ImageIO.read(imageFile4);
        m_spellSprite = ImageIO.read(imageFile5);
    } catch (IOException ex) {
      ex.printStackTrace();
      System.exit(-1);
    }

   
  }
  public void createMap(GameView v,int h, int w,int m){
    int sizeh=v.getHeight();
    int sizew=v.getWidth();
    m_carte= new Map(h, w, sizeh, sizew, m_wallSprite, m_fieldSprite,m);
  }

}