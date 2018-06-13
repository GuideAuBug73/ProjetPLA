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
package edu.ricm3.game;


import principal.Options;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameUI {

    static String license = "Copyright (C) 2017  Pr. Olivier Gruber " + "This program comes with ABSOLUTELY NO WARRANTY. "
            + "This is free software, and you are welcome to redistribute it "
            + "under certain conditions; type `show c' for details.";

    static GameUI game;

//  public static void main(String[] args) {
//
//    game = new Game();
//
//    // notice that the main thread will exit here,
//    // but not your program... hence the hooking
//    // of the window events to System.exit(0) when
//    // the window is closed. See class WindowListener.
//
//    /*
//     * *** WARNING *** WARNING *** WARNING *** WARNING ***
//     * If you do something here, on this "main" thread,
//     * you will have parallelism and thus race conditions.
//     * 
//     *           ONLY FOR ADVANCED DEVELOPERS
//     *           
//     * *** WARNING *** WARNING *** WARNING *** WARNING ***
//     */
//  }

    public JFrame m_frame;
    GameView m_view;
    GameView m_view2;
    Timer m_timer;
    GameModel m_model;
    GameController m_controller;
    JLabel m_text2;
    int m_fps;
    String m_msg;
    long m_start;
    long m_elapsed;
    long m_lastRepaint;
    long m_lastTick;
    int m_nTicks;
    File fichieraut;
    public int frame = 0;

    public GameUI(GameModel m, GameView v, GameView v2, GameController c, Dimension d) {
        m_model = m;
        m_model.m_game = this;
        m_view = v;
        m_view2 = v2;
        m_view.m_game = this;
        m_controller = c;
        m_controller.m_game = this;

        System.out.println(license);
        Options.game = this;

        // create the main window and the periodic timer
        // to drive the overall clock of the simulation.
        createWindow(d, m);
        createTimer();
    }

    public GameModel getModel() {
        return m_model;
    }

    public GameView getView() {
        return m_view;
    }

    public GameController getController() {
        return m_controller;
    }

    public void addNorth(Component c) {
        m_frame.add(c, BorderLayout.NORTH);
    }

    public void addSouth(Component c) {
        m_frame.add(c, BorderLayout.SOUTH);
    }

    public void addWest(Component c) {
        m_frame.add(c, BorderLayout.WEST);
    }

    public void addEast(Component c) {
        m_frame.add(c, BorderLayout.EAST);
    }

    public void addCenter(Component c) {
        m_frame.add(c, BorderLayout.CENTER);
    }

    private void createWindow(Dimension d, GameModel m) {
        m_frame = new JFrame();
        m_frame.setTitle("Game");
        m_frame.setSize(d);
        //m_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        GraphicsDevice gra = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gra.setFullScreenWindow(m_frame);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, d.width, d.height);
        panel.add(m_view2, BorderLayout.CENTER);

        String[] liste_automate={"ennemi","obstacle","item","spawn"};


        JComboBox choix_ennemi = new JComboBox();
        for(int i=0;i<liste_automate.length;i++) {
            choix_ennemi.addItem(liste_automate[i]);
        }
        choix_ennemi.setBounds(Options.d.width/2,Options.d.height/2+100,150,25);
        m_frame.add(choix_ennemi);

        JComboBox choix_obstacle = new JComboBox();
        for(int i=0;i<liste_automate.length;i++) {
            choix_obstacle.addItem(liste_automate[i]);
        }
        choix_obstacle.setBounds(Options.d.width/2,Options.d.height/2+200,150,25);
        m_frame.add(choix_obstacle);

        JComboBox choix_item = new JComboBox();
        for(int i=0;i<liste_automate.length;i++) {
            choix_item.addItem(liste_automate[i]);
        }
        choix_item.setBounds(Options.d.width/2,Options.d.height/2+300,150,25);
        m_frame.add(choix_item);


        GameController ctr = getController();
        m_view2.addKeyListener(ctr);
        m_view2.addMouseListener(ctr);
        m_view2.addMouseMotionListener(ctr);
        m_view2.setFocusable(true);
        m_view2.requestFocusInWindow();
        m_controller.notifyVisible();

        m_frame.add(panel);
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);
    }

    public void createWindowGame(Dimension d, GameModel m) {
        m_frame.dispose();
        Options.game.frame = 1;
        m_frame = new JFrame();
        m_frame.setTitle("Game");
        m_frame.setSize(d);
        m_frame.doLayout();
        m_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        GraphicsDevice gra = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gra.setFullScreenWindow(m_frame);
        m_frame.setVisible(true);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panelinventaire = new JPanel();
        panelinventaire.setBounds(Options.nb_px_x_max, 0, d.width, d.height);
        panelinventaire.setBackground(Color.BLACK);
        JPanel panelplayer = new JPanel(new BorderLayout());
        panelplayer.setBounds(0, 0, d.width, Options.nb_px_y_min);
        panelplayer.setBackground(Color.BLACK);
        JPanel panelinfo = new JPanel();
        panelinfo.setBounds(0, Options.nb_px_y_max, d.width, d.height);
        panelinfo.setBackground(Color.BLACK);
        JPanel panelgrid = new JPanel(new BorderLayout());
        panelgrid.setBounds(Options.nb_px_x_min, Options.nb_px_y_min, Options.nb_px_x_max, Options.nb_px_y_max);
        m_text2 = new JLabel();
        m_text2.setText("Starting up ...");
        Font font = new Font("Arial", Font.BOLD, 20);
        m_text2.setFont(font);
        m_text2.setForeground(Color.white);
        panelinfo.add(m_text2);
        JLayeredPane pane = new JLayeredPane();
        pane.add(panelinfo);
        pane.add(panelinventaire);
        pane.add(panelplayer);
        panelgrid.add(m_view, BorderLayout.CENTER);
        pane.add(panelgrid);
        m_frame.add(pane);
        m_model.m_game.m_frame.addWindowListener(new WindowListener(m_model));
        m_frame.pack();
        m_frame.setLocationRelativeTo(null);
        GameController ctr = getController();
        m_view.addKeyListener(ctr);
        m_view.addMouseListener(ctr);
        m_view.addMouseMotionListener(ctr);
        m_view.setFocusable(true);
        m_view.requestFocusInWindow();
        m_controller.notifyVisible();

        Options.panelinfo = panelinfo;
    }

    /*
     * Let's create a timer, it is the heart of the simulation,
     * ticking periodically so that we can simulate the passing of time.
     */
    private void createTimer() {
        int tick = 1; // one millisecond
        m_start = System.currentTimeMillis();
        m_lastTick = m_start;
        m_timer = new Timer(tick, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tick();
            }
        });
        m_timer.start();
    }

    /*
     * This is the period tick callback.
     * We compute the elapsed time since the last tick.
     * We inform the model of the current time.
     */
    private void tick() {
        long now = System.currentTimeMillis() - m_start;
        long elapsed = (now - m_lastTick);
        m_elapsed += elapsed;
        m_lastTick = now;
        m_nTicks++;
        m_model.step(now);
        m_controller.step(now);
        if (Options.itemlance != null) {
            Options.itemlance.lanceItem();
        }
        if(Options.projectileBossLance!=null){
            Options.projectileBossLance.lanceItem();
        }
        elapsed = now - m_lastRepaint;
        if (elapsed > edu.ricm3.game.Options.REPAINT_DELAY) {
            if (frame == 0) {
                m_view2.paint();
            } else if (Options.game.frame == 1) {
                m_text2.setText("Level : " + Options.level + "     Vague : " + Options.vague);
                m_text2.repaint();
                m_view.paint();
                m_lastRepaint = now;
            }
        }
    }

    public void setFPS(int fps, String msg) {
        m_fps = fps;
        m_msg = msg;
    }

    public void ask_File() {
        JFileChooser dialogue = new JFileChooser(new File("."));
        if (dialogue.showOpenDialog(null) ==
                JFileChooser.APPROVE_OPTION) {
            fichieraut = dialogue.getSelectedFile();
        }

    }

}
