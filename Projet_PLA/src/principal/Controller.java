/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package principal;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


import edu.ricm3.game.GameController;


public class Controller extends GameController implements ActionListener {

  Model m_model;
  Joueur c;
  spell s;
  Ennemi E ;
  
  public Controller(Model m) {
    m_model = m;
    c = m.m_perso;
    E = m.m_ennemi ;
    s=m.m_spell;
  }

  /**
   * Simulation step. Warning: the model has already executed its step.
   *
   * @param now
   *          is the current time in milliseconds.
   */
  @Override
  public void step(long now) {
  }

  @Override
  public void keyTyped(KeyEvent e) {

    if (e.getKeyChar() == 'd') {
    	c.droite();
    }  else if (e.getKeyChar() == 's') {
    	c.bas();
    }
    else if (e.getKeyChar() == 'z') {
    	c.haut();
    }
    else if (e.getKeyChar() == 'q') {
    	c.gauche();
    }
    if (e.getKeyChar() == 'l') {
    	E.droite();
    }  else if (e.getKeyChar() == 'k') {
    	E.bas();
    }
    else if (e.getKeyChar() == 'i') {
    	E.haut();
    }
    else if (e.getKeyChar() == 'j') {
    	E.gauche();
    }
    else if (e.getKeyChar() == 'm') {
   
    if(c.orientation==0)
        s.setcast(c.x,c.y,c.x,c.y+400);
      if(c.orientation==1)
          s.setcast(c.x,c.y,c.x+400,c.y);
      if(c.orientation==2)
          s.setcast(c.x,c.y,c.x-400,c.y);
      if(c.orientation==3)
          s.setcast(c.x,c.y,c.x,c.y-400);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (Options.ECHO_KEYBOARD)
      System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (Options.ECHO_KEYBOARD)
      System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (Options.ECHO_MOUSE)
      System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
    
    
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (Options.ECHO_MOUSE)
      System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (Options.ECHO_MOUSE)
      System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
  }

  public void notifyVisible() {


  



  }

  @Override
  public void actionPerformed(ActionEvent e) {

 
  }

}