/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.packman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author bob
 */
public class MazeCreator {

    public static void main(String[] args) throws IOException {
        for (int m = 0; m < 4; m++) {
            // load in the maze strings
            ArrayList<String> lines = new ArrayList<String>();
            try {
                 File file = new File("src/mazes/" + m );
          //System.out.println(file.getCanonicalPath());
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    lines.add(scanner.nextLine());
                }
                scanner.close();

                int rows = lines.size();
                int columns = lines.get(0).length();
                int width = columns * 2;
                int hight = rows * 2;

                // draw maze on an image
                BufferedImage image = new BufferedImage(width, hight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = image.createGraphics();
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        if (lines.get(r).charAt(c) != '0') {
                            g.fillRect(c*2-14, r*2-14, 28, 28);
                        }
                    }
                }
                g.dispose();
                try {
                    // save the image
                    ImageIO.write(image,"png",new File("src/images/"+m+".png"));
                } catch (IOException ex) {
                    Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
                }


            } catch (FileNotFoundException ex) {
                Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
