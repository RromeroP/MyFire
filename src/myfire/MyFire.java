/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfire;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author dam
 */
public class MyFire {

    /**
     * @param args the command line arguments
     */
    private static JFrame frame;
    private static final String TITLE = "My Fire";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 400;
    private static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

    //Para pruebas
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO code application logic here
        frame = new JFrame(TITLE);
        frame.setPreferredSize(SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);

        Flame flame1 = new Flame(WIDTH, HEIGHT);
        Viewer viewer = new Viewer(SIZE, 500, flame1);

        frame.add(viewer.getCanvas());

        frame.pack();
        frame.setVisible(true);

        flame1.run();
        viewer.run();
    }

    public void setFlamePalette() {
    }

    public void setFlameRate() {
    }

    public void setViewerRate() {
    }

    public void setPause() {
    }

    public void setStop() {
    }

}
