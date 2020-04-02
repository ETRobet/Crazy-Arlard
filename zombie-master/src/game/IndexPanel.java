/*
 * IndexPanel.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月27日
 */
package game;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.MalformedURLException;

public class IndexPanel extends JPanel implements MouseMotionListener, MouseListener {
    boolean loop;
    int exitStatus;
    IndexBackGround indexBackGround;
    AudioClip bgm;
    
    public IndexPanel() {
        this.indexBackGround = new IndexBackGround();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.loop = true;
        try {
            bgm = Applet.newAudioClip(new File("sounds/bgm/indexbgm.wav").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int work() {
        bgm.loop();
        while (loop) {
            this.repaint(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bgm.stop();
        return this.exitStatus;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        indexBackGround.drawDefault(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if ((e.getX() > IndexPanelAttributes.START_BUTTON_X && e.getX() < IndexPanelAttributes.START_BUTTON_X + IndexPanelAttributes.START_BUTTON_WIDTH
                    && e.getY() > IndexPanelAttributes.START_BUTTON_Y && e.getY() < IndexPanelAttributes.START_BUTTON_Y + IndexPanelAttributes.START_BUTTON_HEIGHT) ||
                (e.getX() > IndexPanelAttributes.INFO_BUTTON_X && e.getX() < IndexPanelAttributes.INFO_BUTTON_X + IndexPanelAttributes.INFO_BUTTON_WIDTH
                        && e.getY() > IndexPanelAttributes.INFO_BUTTON_Y && e.getY() < IndexPanelAttributes.INFO_BUTTON_Y + IndexPanelAttributes.INFO_BUTTON_HEIGHT) ||
                (e.getX() > IndexPanelAttributes.EXIT_BUTTON_X && e.getX() < IndexPanelAttributes.EXIT_BUTTON_X + IndexPanelAttributes.EXIT_BUTTON_WIDTH
                        && e.getY() > IndexPanelAttributes.EXIT_BUTTON_Y && e.getY() < IndexPanelAttributes.EXIT_BUTTON_Y + IndexPanelAttributes.EXIT_BUTTON_HEIGHT)) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > IndexPanelAttributes.START_BUTTON_X && e.getX() < IndexPanelAttributes.START_BUTTON_X + IndexPanelAttributes.START_BUTTON_WIDTH
                && e.getY() > IndexPanelAttributes.START_BUTTON_Y && e.getY() < IndexPanelAttributes.START_BUTTON_Y + IndexPanelAttributes.START_BUTTON_HEIGHT) {
            this.exitStatus = GameStatus.GAME_RUNNING;
            this.loop = false;
        } else if (e.getX() > IndexPanelAttributes.INFO_BUTTON_X && e.getX() < IndexPanelAttributes.INFO_BUTTON_X + IndexPanelAttributes.INFO_BUTTON_WIDTH
                && e.getY() > IndexPanelAttributes.INFO_BUTTON_Y && e.getY() < IndexPanelAttributes.INFO_BUTTON_Y + IndexPanelAttributes.INFO_BUTTON_HEIGHT) {
            this.exitStatus = GameStatus.GAME_INFO;
            this.loop = false;
        } else if (e.getX() > IndexPanelAttributes.EXIT_BUTTON_X && e.getX() < IndexPanelAttributes.EXIT_BUTTON_X + IndexPanelAttributes.EXIT_BUTTON_WIDTH
                && e.getY() > IndexPanelAttributes.EXIT_BUTTON_Y && e.getY() < IndexPanelAttributes.EXIT_BUTTON_Y + IndexPanelAttributes.EXIT_BUTTON_HEIGHT) {
            this.exitStatus = GameStatus.GAME_EXIT;
            this.loop = false;
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
