/*
 * SuccessSceneBackGround.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月30日
 */
package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SuccessSceneBackGround extends BackGroundBase {
    Image img;
    int x;
    int y;

    public SuccessSceneBackGround() {
        try {
            img = ImageIO.read(new File("images/background/successbackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_img = img;
        x = this.BACKIMGDEFAULTX;
        y = this.BACKIMGDEFAULTY;
    }
}
