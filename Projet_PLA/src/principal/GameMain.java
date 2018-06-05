package principal;

import java.awt.Dimension;
import edu.ricm3.game.GameUI;

public class GameMain {

  public static void main(String[] args) {
    Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    tailleMap(d);
    // construct the game elements: model, controller, and view.
    Model model = new Model();
    Controller controller = new Controller(model);
    View view = new View(model,controller);
    new GameUI(model,view,controller,d);
    
    // notice that the main thread will exit here,
    // but not your program... hence the hooking
    // of the window events to System.exit(0) when
    // the window is closed. See class WindowListener.

    /*
     * *** WARNING *** WARNING *** WARNING *** WARNING ***
     * If you do something here, on this "main" thread,
     * you will have parallelism and thus race conditions.
     * 
     *           ONLY FOR ADVANCED DEVELOPERS
     *           
     * *** WARNING *** WARNING *** WARNING *** WARNING ***
     */
    return;
  }
  public static void tailleMap(Dimension d){
    int m_h=d.height;
    int m_w=d.width;
    m_h-=100;
    m_w-=100;
    m_h=m_h/Options.TAILLE_CELLULE;
    m_w=m_w/Options.TAILLE_CELLULE;
    int m_panelD=d.width-m_w*Options.TAILLE_CELLULE;
    int m_panelB=d.height-m_h*Options.TAILLE_CELLULE;
    m_panelB=m_panelB/2;
    int m_panelH=m_panelB;
    Options.nb_cell_h=m_h;
    Options.nb_cell_w=m_w;
    Options.nb_px_x_min=0;
    Options.nb_px_x_max=d.width-m_panelD;
    Options.nb_px_y_min=m_panelH;
    Options.nb_px_y_max=d.height-m_panelB;

  }
}
