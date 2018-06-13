package principal;

import edu.ricm3.game.GameUI;

import javax.swing.*;
import java.awt.*;

public class Options {
    public static final boolean USE_DOUBLE_BUFFERING = true;

    public static int SHOW_NENNEMI = 1;
    public static int MAX_NENNEMI = 15;
    public static int SHOW_NITEM = 3;
    public static int MAX_NITEM = 8;

    public static final boolean ECHO_MOUSE = true;
    public static final boolean ECHO_MOUSE_MOTION = true;
    public static final boolean ECHO_KEYBOARD = true;


    public static int TAILLE_CELLULE = 60;
    public static int nb_cell_w;
    public static int nb_cell_h;
    public static int nb_px_x_min;
    public static int nb_px_x_max;
    public static int nb_px_y_min;
    public static int nb_px_y_max;
    public static Dimension d;

    public static int vague = 1;
    public static int level = 1;

    public static JPanel panelinfo;
    public static float time_vague=0;
    public static int point_de_vie=3;
	public static Item itemlance=null;

	public static int taille_bp_h;
	public static int taille_bp_w;
	public static GameUI game;
}
