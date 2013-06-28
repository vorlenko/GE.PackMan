/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.packman;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.game.engine.*;

/**
 *
 * @author VladimirO
 */
public class PackMan extends Game {
    BufferedImage packman;
    
    public static void main(String[] args){
        GameApplication.start(new PackMan()); 
    }

    public PackMan() {
        title = "PackMan";
        width = height = 400;
        init();
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void init() {
        try {
            packman = ImageIO.read(new File("images/packman.png"));
        } catch (IOException ex) {
            Logger.getLogger(PackMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
