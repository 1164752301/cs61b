import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class SeamCarver {
    private Picture picture;
    private  double[][] energy;
    public SeamCarver(Picture picture) {
        this.picture = picture;
        energy = new double[height()][width()];
        for (int x = 0; x < width(); x++) {
            for(int y = 0; y < height(); y++) {
                Color left = picture.get((x-1) % width(), y);
                Color right = picture.get((x+1) % width(), y);
                Color up = picture.get(x, (y + 1) % height());
                Color down = picture.get(x, (y - 1) % height());
                double redDifferenceX = Math.abs(left.getRed() - right.getRed());
                double blueDifferenceX = Math.abs(left.getBlue() - right.getBlue());
                double greenDifferenceX = Math.abs(left.getGreen() - right.getGreen());
                double redDifferenceY = Math.abs(up.getRed() - down.getRed());
                double blueDifferenceY = Math.abs(up.getBlue() - down.getBlue());
                double greenDifferenceY = Math.abs(up.getGreen() - down.getGreen());
                double energyX = Math.pow(redDifferenceX, 2) + Math.pow(blueDifferenceX, 2)
                        + Math.pow(greenDifferenceX, 2);
                double energyY = Math.pow(redDifferenceY, 2) + Math.pow(blueDifferenceY, 2)
                        + Math.pow(greenDifferenceY, 2);
                energy[x][y] = energyX + energyY;
            }
        }
    }

    public Picture picture() {
        return picture;
    }                      // current picture
    public     int width() {
        return picture.width();
    }                         // width of current picture
    public     int height() {
        return picture.height();
    }                       // height of current picture
    public  double energy(int x, int y) {
        return energy[x][y];
    }           // energy of pixel at column x and row y
    public   int[] findHorizontalSeam()            // sequence of indices for horizontal seam
    public   int[] findVerticalSeam(){
        for (int x = 0; x < width(); x++) {
            int[] shortestPath = new int[height()];
            int minimumDistance = 0;
            int i = x;
            
        }
    }              // sequence of indices for vertical seam

    private int findMin(int x, int y) {
        if (x == 0) {
            return (energy(x, y - 1) < energy(x + 1, y - 1)) ? x : x + 1;
        } else if (x == width() - 1) {
            return (energy(x - 1, y - 1) < energy(x, y - 1)) ? x - 1 : x;
        } else {
            if (energy(x - 1, y) < Math.min(energy(x, y), energy(x + 1, y))) return x - 1;
            else return (energy(x - 1, y - 1) < energy(x + 1, y - 1)) ? x - 1 : x + 1;
        }
    }


    private int E(int x, int y) {

    }

    private int xyTo1d(int x, int y) {
        return x + y * width();
    }
    public    void removeHorizontalSeam(int[] seam)   // remove horizontal seam from picture
    public    void removeVerticalSeam(int[] seam)     // remove vertical seam from picture
}
