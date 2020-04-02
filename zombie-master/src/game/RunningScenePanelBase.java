/*
 * RunningScenePanelBase.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月29日
 */
package game;

import javax.swing.*;
import java.applet.AudioClip;

public class RunningScenePanelBase extends JPanel {
    boolean loop;
    boolean passStatus;
    AudioClip bgm;

    RunningScenePanelBase() {
        this.passStatus = RunningAttributes.SCENE_PASS;
        this.loop = true;
        this.setFocusable(true);
    }
}
