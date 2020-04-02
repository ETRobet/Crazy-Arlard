package test;

import game.IndexBackGround;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IndexPanel extends JPanel {
    Image img;
    public IndexPanel() {
        try {
            img = ImageIO.read(new File("images/indexbackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void work() {
        while (true) {
            this.repaint(0);
            System.out.println("in work");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,0,0,null);
        System.out.println("indexdraw");
    }
}
