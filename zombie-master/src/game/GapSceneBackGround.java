/*
 * GapSceneBackGround.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月29日
 */
package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GapSceneBackGround extends BackGroundBase {
    Image img;
    int x;
    int y;

    public GapSceneBackGround() {
        try {
            img = ImageIO.read(new File("images/background/gapbackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_img = img;
        x = this.BACKIMGDEFAULTX;
        y = this.BACKIMGDEFAULTY;
    }
}
