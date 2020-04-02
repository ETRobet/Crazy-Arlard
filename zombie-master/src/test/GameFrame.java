/*
 * GameFrame.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月27日
 */
package test;

import javax.swing.*;

public class GameFrame extends JFrame {

    public static final int FRAME_WIDTH = 1440;
    public static final int FRAME_HEIGHT = 900;
    public static final int GAME_INDEX = 0;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_INFO = 2;
    public static final int GAME_EXIT = 3;
    int status;
    IndexPanel indexPanel;

    public GameFrame() {
        this.setTitle("——待定——");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
    }

    public void startProcess() {
        status = GAME_INDEX;
        indexPanel = new IndexPanel();
        this.add(indexPanel);
        this.setVisible(true);
        indexPanel.work();
//        while (true) {
//            this.add(indexPanel);
//            indexPanel.work();
//            if (status == GAME_RUNNING) {
//
//            } else if (status == GAME_INFO) {
//
//            } else if (status == GAME_EXIT){
//                System.exit(0);
//            }
//        }
    }

}
