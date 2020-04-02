/*
 * BackGroundBase.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月27日
 */
package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackGroundBase {
    public static final int BACKIMGDEFAULTX = 0;
    public static final int BACKIMGDEFAULTY = 0;
    Image back_img;

    public void BackGround() {

    }

    void drawDefault(Graphics g) {
        g.drawImage(back_img, BACKIMGDEFAULTX, BACKIMGDEFAULTY, null);
    }
}
