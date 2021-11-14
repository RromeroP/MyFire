/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfire;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam
 */
public class Flame extends Thread {

    int WIDTH;
    int HEIGHT;
    int[][] temperature;
    BufferedImage flame_i;
    byte[] buffer;
    Random rand = new Random();

    public Flame(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        this.temperature = new int[WIDTH][HEIGHT];
        this.flame_i = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        this.buffer = ((DataBufferByte) this.flame_i.getRaster().getDataBuffer()).getData();
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int[][] getTemperature() {
        return temperature;
    }

    public void setTemperature(int[][] temperature) {
        this.temperature = temperature;
    }

    public BufferedImage getFlame_i() {
        return flame_i;
    }

    public void setFlame_i(BufferedImage flame_i) {
        this.flame_i = flame_i;
    }

    public void setRate() {
    }

    public void setPalette() {
    }

    public void flameEvolve() {
        calcular(this.temperature);

        final int pixelLength = 4;
        for (int pixel = 0, row = 0, col = 0;
                pixel + 3 < this.buffer.length; pixel += pixelLength) {

            //Aqui iria la paleta de colores
            buffer[pixel] = (byte) 255; // alpha
            buffer[pixel + 1] = (byte) 0; // blue
            buffer[pixel + 2] = (byte) 0; // green
            buffer[pixel + 3] = (byte) temperature[col][row]; // red

            //Usamos esto para encontrar las colummnas y lines en el array
            col++;
            if (col == this.WIDTH) {

                col = 0;
                row++;
            }
        }
    }

    public void createCoal() {
    }

    public void createSparks() {

        final int pixelLength = 4;
        for (int pixel = 0, row = 0, col = 0;
                pixel + 3 < this.buffer.length; pixel += pixelLength) {

            //El -40 es necesario o nos salimos de la ventana
            if (row == this.HEIGHT - 39) {

                int random = rand.nextInt(100);

                if (random < 50) {
                    temperature[col][row] = 255;

                    //Aqui iria la paleta de colores
                    buffer[pixel] = (byte) 255; // alpha
                    buffer[pixel + 1] = (byte) 0; // blue
                    buffer[pixel + 2] = (byte) 0; // green
                    buffer[pixel + 3] = (byte) 255; // red
                }
            }

            //Usamos esto para encontrar las colummnas y lines en el array
            col++;
            if (col == this.WIDTH) {

                col = 0;
                row++;
            }
        }
    }

    private void calcular(int[][] temperature) {

        int up;
        int left;
        int right;
        int down;
        int c_rand = 2;
        int[][] temp = new int[temperature.length][temperature[0].length];

        for (int i = 1; i < temperature.length - 1; i++) {
            for (int j = 1; j < temperature[0].length - 1; j++) {
                up = temperature[i - 1][j];
                left = temperature[i][j - 1];
                right = temperature[i][j + 1];
                down = temperature[i + 1][j];

                int avg = (up + left + right + down) / 4;

                
                int cooling = rand.nextInt(c_rand);
                
                avg -= cooling;

                if (avg < 0) {
                    avg = 0;
                }

                temp[i][j-1] = avg;

            }
        }

        System.arraycopy(temp, 0, temperature, 0, temp.length);

    }

    private void createFlameImage() {
    }

    @Override
    public void run() {

        while (true) {
            createSparks();
            flameEvolve();

            try {
                sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
