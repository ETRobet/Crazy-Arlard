/*
 * GameInfoPanel.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月30日
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

public class GameInfoPanel extends JPanel implements MouseMotionListener, MouseListener {
    boolean loop;
    int exitStatus;
    GameInfoBackGround gameInfoBackGround;
    AudioClip bgm;

    public GameInfoPanel() {
        this.gameInfoBackGround = new GameInfoBackGround();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.loop = true;
        try {
            bgm = Applet.newAudioClip(new File("sounds/bgm/gameinfobgm.wav").toURI().toURL());
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
        gameInfoBackGround.drawDefault(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() > GameInfoPanelAttributes.BACK_BUTTON_X && e.getX() < GameInfoPanelAttributes.BACK_BUTTON_X + GameInfoPanelAttributes.BACK_BUTTON_WIDTH
                && e.getY() > GameInfoPanelAttributes.BACK_BUTTON_Y && e.getY() < GameInfoPanelAttributes.BACK_BUTTON_Y + GameInfoPanelAttributes.BACK_BUTTON_HEIGHT) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > GameInfoPanelAttributes.BACK_BUTTON_X && e.getX() < GameInfoPanelAttributes.BACK_BUTTON_X + GameInfoPanelAttributes.BACK_BUTTON_WIDTH
                && e.getY() > GameInfoPanelAttributes.BACK_BUTTON_Y && e.getY() < GameInfoPanelAttributes.BACK_BUTTON_Y + GameInfoPanelAttributes.BACK_BUTTON_HEIGHT) {
            this.exitStatus = GameStatus.GAME_INDEX;
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
