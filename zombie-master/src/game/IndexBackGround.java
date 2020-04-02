/*
 * IndexBackGroundBase.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月27日
 */
package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IndexBackGround extends BackGroundBase {
    Image img;
    public IndexBackGround() {
        try {
            img = ImageIO.read(new File("images/background/indexbackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_img = img;
    }

}
