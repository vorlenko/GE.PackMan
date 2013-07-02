/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.packman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bob
 */
class Maze {

    public int column, row;
    public int rows = 0;
    public int columns = 0;
    public int width, height;
    public ArrayList<String> lines = new ArrayList<String>();

    public Maze(int m) {
        //load the lines
        try {

            Scanner s = new Scanner(new File("src/mazes/" + m));
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

            width = columns * 2;
            height = rows * 2;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PackMan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public char charAt(int row, int column) {
        return lines.get(row).charAt(column);
    }

    char[][] getCells() {
        char[][] cells = new char[rows][columns];
        for (int r = 0;r<rows;r++){
            System.arraycopy(lines.get(r).toCharArray(), 0, cells[r], 0, columns);
            //for(int c;c<columns;c++){
            //    
            //}
        }
        return cells;
    }
}
