package principal;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.ricm3.game.GameUI;
import ricm3.parser.Ast.AI_Definitions;
import ricm3.parser.AutomataParser;
import ricm3.parser.ParseException;

public class GameMain {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		Options.d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Options.timer_mort=Options.d.width / 2 - 175;
		tailleMap(Options.d);
		// construct the game elements: model, controller, and view.
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(model, controller);
		View view2 = new View(model,controller);
		new GameUI(model, view, view2,controller,Options.d);
		/*Grid2d map2d = new Grid2d(model.m_carte.cellules);
		int[] tab_j = model.m_perso.PosToCell();
		int[] tab_a = model.m_ennemi.PosToCell();
		System.out.println("Options.nb_cell_w" + Options.nb_cell_w);
		System.out.println("Options.nb_cell_h" + Options.nb_cell_h);
		List<Grid2d.MapNode> route = map2d.findPath(tab_a[0], tab_a[1],tab_j[0], tab_j[1]);	
		int cpt = 0;
		int max = route.size();
		while (cpt < max) {
			Grid2d.MapNode	element  = route.get(cpt);
			 int y = element.get_x();
			 int x = element.get_y();
			 System.out.println("x : "+x);
			 System.out.println("y : "+y);
				model.m_item[cpt]=new Item(1,x*Options.TAILLE_CELLULE,y*Options.TAILLE_CELLULE,model.m_itemSprite[1],model.m_itemSprite[5],model);
			 model.m_carte.cellules[y][x].entité = model.m_item[cpt];
			 cpt++;

		}*/


		//System.out.println(map2d.findPath(tab_j[0], tab_j[1], tab_a[0], tab_a[1]));

		// notice that the main thread will exit here,
		// but not your program... hence the hooking
		// of the window events to System.exit(0) when
		// the window is closed. See class WindowListener.

		/*
		 * *** WARNING *** WARNING *** WARNING *** WARNING *** If you do something here,
		 * on this "main" thread, you will have parallelism and thus race conditions.
		 * 
		 * ONLY FOR ADVANCED DEVELOPERS
		 * 
		 * *** WARNING *** WARNING *** WARNING *** WARNING ***
		 */
		return;
	}

	public static void tailleMap(Dimension d) {
		int m_h = d.height;
		int m_w = d.width;
		m_h -= 100;
		m_w -= 100;
		m_h = m_h / Options.TAILLE_CELLULE;
		m_w = m_w / Options.TAILLE_CELLULE;
		int m_panelD = d.width - m_w * Options.TAILLE_CELLULE;
		int m_panelB = d.height - m_h * Options.TAILLE_CELLULE;
		m_panelB = m_panelB / 2;
		int m_panelH = m_panelB;
		Options.nb_cell_h = m_h;
		Options.nb_cell_w = m_w;
		Options.nb_px_x_min = 0;
		Options.nb_px_x_max = d.width - m_panelD;
		Options.nb_px_y_min = m_panelH;
		Options.nb_px_y_max = d.height - m_panelB;

	}
}
