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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
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
    private int reqDir; //when ever we wish to move
    private int curDir;
    private int column, row;
    private int rows = 0;
    private int columns = 0;
    ArrayList<String> lines = new ArrayList<String>();

    public static void main(String[] args) {
        GameApplication.start(new PackMan());
    }

    public PackMan() {
        //load the lines
        try {
            Scanner s = new Scanner(new File("src/maze.txt"));
            int r = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                lines.add(line);
                if (line.contains("5")) {
                    column = line.indexOf('5');
                    row = r;
                }
                r++;
            }
            s.close();

            rows = lines.size();
            columns = lines.get(0).length();

            width = columns * STEP;
            height = rows * STEP;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PackMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        //load the lines. Second version
        /*
         * Scanner s = new Scanner(getClass().getResourceAsStream("/maze.txt"));
         * 
         */
        //to check path
        /*
         * File file = new File("highscore.txt");
         * System.out.println(file.getCanonicalPath());
         */
        title = "PackMan";
        //width = height = 400;
        frame = 0;
        try {
            //packman = ImageIO.read(new File("./packman.png"));
            packman = ImageIO.read(getClass().getResourceAsStream("/images/packman.png"));

        } catch (IOException ex) {
            Logger.getLogger(PackMan.class.getName()).log(Level.SEVERE, null, ex);
        }

        curDir = reqDir = KeyEvent.VK_LEFT;

        //column = 300;
        //row = 200;

        delay = 60;

        //init();
    }

    @Override
    public void draw(Graphics g) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //g.drawImage(packman, 0, 0, null);
        g.setColor(Color.red);
        g.drawRect(0, 0, width, height);
        //draw maze
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (charAt(r, c) != '0') {
                    //g.fillRect(c * STEP, r * STEP, STEP, STEP);
                    g.fillRect(c * STEP-14, r * STEP-14, 28, 28);
                }
            }
        }


        g.drawImage(packman.getSubimage((frame / 2) * 30, (curDir - 37) * 30, 28, 28), column * STEP - 14, row * STEP - 14, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //dir = e.getKeyCode();
        //dir = key;
        //Set filter on pressed keys
        if (37 <= key && key <= 40) {
            reqDir = key;
        }

    }

    @Override
    public void update() {
        frame++;
        if (frame > 5) { //to low speed
            frame = 0;
        }

        if (move(reqDir) == SUCCESS) {
            curDir = reqDir;
        } else {
            move(curDir);
        }
        // limit the movement area
        if (column < 0) {
            column = 0;
        }
        if (column > width - 28 - STEP) {
            column = width - 28 - STEP;
        }
        if (row < 0) {
            row = 0;
        }
        if (row > height - 28 - STEP) {
            row = height - 28 - STEP;
        }
    }
    static int SUCCESS = 1, FAIL = 0;

    private int move(int reqDir) {
        //current position of PackMan is (row,column)
        switch (reqDir) {
            case KeyEvent.VK_LEFT: // 37
                if (column > 0 && charAt(row, column - 1) != '0') {
                    column -= 1;
                    return SUCCESS;
                }
                break;
            case KeyEvent.VK_UP:   // 38
                if (row > 0 && charAt(row - 1, column) != '0') {
                    row -= 1;
                    return SUCCESS;
                }
                break;
            case KeyEvent.VK_RIGHT: // 39
                if (column < columns - 1 && charAt(row, column + 1) != '0') {
                    column += 1;
                    return SUCCESS;
                }
                break;
            case KeyEvent.VK_DOWN:  // 40
                if (row < rows - 1 && charAt(row + 1, column) != '0') {
                    row += 1;
                    return SUCCESS;
                }
                break;
        }
        return FAIL;
    }

    private char charAt(int row, int column) {
        return lines.get(row).charAt(column);
    }
}
