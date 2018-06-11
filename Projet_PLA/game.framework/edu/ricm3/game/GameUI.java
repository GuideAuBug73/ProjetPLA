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
import java.awt.image.BufferedImage;
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

    JFrame m_frame;
    GameView m_view;
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
    int frame = 0; //0 frame menu 1 frame jeu 2 frame pause
    Image img;
    Image img2;
    int m_h;
    int m_w;
    int m_panelD;
    int m_panelB;
    int m_panelH;

    public GameUI(GameModel m, GameView v, GameController c, Dimension d) {
        m_model = m;
        m_model.m_game = this;
        m_view = v;
        m_view.m_game = this;
        m_controller = c;
        m_controller.m_game = this;

        System.out.println(license);

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

        File file;
        JPanel panel=new JPanel(new BorderLayout());
        panel.setBounds(0,0,d.width,d.height);
        panel.add(m_view,BorderLayout.CENTER);
        m_frame.add(panel);
        Graphics g = m_view.getGraphics();
        file= new File("src/sprites/menu.png");
        try {
            img = ImageIO.read(file);
            g.drawImage(img, 0, 0, Options.d.width, Options.d.height, null);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        file= new File("src/sprites/bp.png");
        try {
            img2 = ImageIO.read(file);
            Options.taille_bp_h=((BufferedImage) img2).getHeight();
            Options.taille_bp_w=((BufferedImage) img2).getWidth();
            g.drawImage(img2, (d.width/2)-(((BufferedImage) img2).getWidth()/2), (d.height/2)-(((BufferedImage) img2).getHeight()/2), ((BufferedImage) img2).getWidth(), ((BufferedImage) img2).getHeight(), null);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);

        GameController ctr = getController();
        m_view.addKeyListener(ctr);
        m_view.addMouseListener(ctr);
        m_view.addMouseMotionListener(ctr);
        m_view.setFocusable(true);
        m_view.requestFocusInWindow();
        m_controller.notifyVisible();
    }

    public void createWindowGame(Dimension d, GameModel m) {
        m_frame.dispose();
        frame=1;
        m_frame = new JFrame();
        m_frame.setTitle("Game");
        m_frame.setSize(d);
        //m_frame.doLayout();
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
        if (frame == 0) {
            Graphics g = m_view.getGraphics();
            g.drawImage(img, 0, 0, Options.d.width+10, Options.d.height+30, null);
            g.drawImage(img2, (Options.d.width/2)-(((BufferedImage) img2).getWidth()/2), (Options.d.height/2)-(((BufferedImage) img2).getHeight()/2), ((BufferedImage) img2).getWidth(), ((BufferedImage) img2).getHeight(), null);
        } else if (frame == 1) {
            if (Options.itemlance != null) {
                Options.itemlance.lanceItem();
            }
            m_text2.setText("Level : " + Options.level + "     Vague : " + Options.vague);
            m_text2.repaint();
            m_view.paint();
            m_lastRepaint = now;
        }
    }

    public void setFPS(int fps, String msg) {
        m_fps = fps;
        m_msg = msg;
    }


}
