/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.packman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.game.engine.*;

/**
 *
 * @author VladimirO
 */
public class PackMan extends Game {

    BufferedImage packman;
    private final int STEP = 2;
    private int frame;
    private int dir;
    private int x, y;

    public static void main(String[] args) {
        GameApplication.start(new PackMan());
    }

    public PackMan() {
        title = "PackMan";
        width = height = 400;
        frame = 0;
        try {
            //packman = ImageIO.read(new File("./packman.png"));
            packman = ImageIO.read(getClass().getResourceAsStream("/images/packman.png"));

        } catch (IOException ex) {
            Logger.getLogger(PackMan.class.getName()).log(Level.SEVERE, null, ex);
        }

        dir = KeyEvent.VK_LEFT;

        x = 300;
        y = 200;
        
        delay = 60;
        
        //init();
    }

    @Override
    public void draw(Graphics g) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //g.drawImage(packman, 0, 0, null);
        g.setColor(Color.red);
        g.drawRect(0, 0, width, height);
        g.drawImage(packman.getSubimage((frame/2) * 30, (dir-37)*30, 28, 28), x, y, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //dir = e.getKeyCode();
        dir = key;
        
    }

    @Override
    public void update() {
        frame++;
        if (frame > 5) { //to low speed
            frame = 0;
        }

        switch (dir) {
            case KeyEvent.VK_LEFT: // 37
                x -= STEP;
                break;
            case KeyEvent.VK_UP:   // 38
                y -= STEP;
                break;
            case KeyEvent.VK_RIGHT: // 39
                x += STEP;
                break;
            case KeyEvent.VK_DOWN:  // 40
                y += STEP;
                break;
        }
// limit the movement area
        if (x < 0) {
            x = 0;
        }
        if (x > width - 28 - STEP) {
            x = width - 28 - STEP;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > height - 28 - STEP) {
            y = height - 28 - STEP;
        }
    }

    
}
