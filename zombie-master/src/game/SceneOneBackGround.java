/*
 * SceneOneBackGround.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月28日
 */
package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SceneOneBackGround extends BackGroundBase {
    Image img;
    int x;
    int y;

    public SceneOneBackGround() {
        try {
            img = ImageIO.read(new File("images/background/sceneonebackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_img = img;
        x = this.BACKIMGDEFAULTX;
        y = this.BACKIMGDEFAULTY;
    }

}
