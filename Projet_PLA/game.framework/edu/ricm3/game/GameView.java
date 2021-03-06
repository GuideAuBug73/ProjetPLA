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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is your game view, a canvas where the game is displayed.
 * <p>
 * It extends the AWT canvas, a widget that is essentially an area of pixels,
 * suited to draw on it. You can draw pixels or shapes such as lines, polygones,
 * or circles. Look for these capabilitis in the class java.awt.Graphics.
 * You can also draw images and therefore you can do animations.
 * <p>
 * We extended the AWT canvas to have an efficient double buffering.
 * You can read https://en.wikipedia.org/wiki/Multiple_buffering
 * as an introduction.
 * <p>
 * Note that you can turn double buffering off in Options.USE_DOUBLE_BUFFERING.
 * We suggest that you leave it on.
 *
 * @author Pr. Olivier Gruber
 */
public abstract class GameView extends Canvas {

    private static final long serialVersionUID = 1L;

    protected GameUI m_game;
    protected Color m_background = Color.BLACK;

    private Image m_buffer1, m_buffer2;
    private Image m_renderBuffer;
    private Image m_drawBuffer;
    private int m_width, m_height;
    private boolean m_swap;

    private void initDoubleBuffering(int width, int height) {

        if (width != m_width || height != m_height) {
            m_width = width;
            m_height = height;
            m_buffer1 = new BufferedImage(m_width, m_height, BufferedImage.TYPE_INT_RGB);
            if (Options.USE_DOUBLE_BUFFERING)
                m_buffer2 = new BufferedImage(m_width, m_height, BufferedImage.TYPE_INT_RGB);
            else
                m_buffer2 = m_buffer1;

            Graphics gc = m_buffer1.getGraphics();
            gc.setColor(Color.BLACK);
            gc.fillRect(0, 0, m_width, m_height);
            gc = m_buffer2.getGraphics();
            gc.setColor(Color.BLACK);
            gc.fillRect(0, 0, m_width, m_height);
            m_renderBuffer = m_buffer2;
            m_drawBuffer = m_buffer1;
        }
    }

    private void swap() {
        if (m_renderBuffer == m_buffer1) {
            m_renderBuffer = m_buffer2;
            m_drawBuffer = m_buffer1;
        } else {
            m_renderBuffer = m_buffer1;
            m_drawBuffer = m_buffer2;
        }
    }

    protected GameView() {
    }

    public GameUI getGameUI() {
        return m_game;
    }

    public int getWidth() {
        return m_width;
    }

    public int getHeight() {
        return m_height;
    }

    public void setBounds(int x, int y, int width, int height) {
        initDoubleBuffering(width, height);
        super.setBounds(x, y, width, height);
    }

    public GameModel getModel() {
        return m_game.getModel();
    }

    public GameController getController() {
        return m_game.getController();
    }

    public final void paint() {
        if (principal.Options.game.frame == 0) {
            Graphics g = principal.Options.game.m_view2.getGraphics();
            _paint_menu(g);
        } else if (principal.Options.game.frame == 1) {
            Graphics g = m_drawBuffer.getGraphics();
            _paint(g);
            Graphics g2 = m_game.m_frame.getGraphics();
            JLayeredPane pane = m_game.m_frame.getLayeredPane();
            _paint_inventaire(g2);
            _paint_player(g2);
            Graphics g3 = principal.Options.panelinfo.getGraphics();
            _paint_level(g3);
            m_swap = true;
            repaint();
        }
    }

    @Override
    public final void paint(Graphics g) {
        if (m_swap) {
            swap();
            m_swap = false;
        }
        g.drawImage(m_renderBuffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public final void update(Graphics g) {
        paint(g);
    }

    protected abstract void _paint(Graphics g);

    protected abstract void _paint_inventaire(Graphics g);

    protected abstract void _paint_player(Graphics g);

    protected abstract void _paint_level(Graphics g);

    protected abstract void _paint_menu(Graphics g);
}
