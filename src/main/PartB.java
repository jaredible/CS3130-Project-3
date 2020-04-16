package main;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * @filename PartB.java
 * @author Jared Diehl
 * @date 2020-04-18
 * @course CMP SCI 3130
 * @title Project 3
 * @purpose To benchmark finding a tree's height.
 * @notes
 */
public class PartB {

  private static int[] getRandomizedIntegerArray(int size) {
    int[] array = new int[size];
    Random rand = new Random();

    for (int i = 0; i < size; i++) {
      array[i] = rand.nextInt(500) + 1;
    }

    for (int i = 0; i < size; i++) {
      int n = rand.nextInt(size), temp = array[n];
      array[n] = array[i];
      array[i] = temp;
    }

    return array;
  }

  public static void main(String[] args) {
    try {
      int[] tArray = new int[] {5, 10, 15}, NArray = new int[] {100, 500, 1000};

      for (int N : NArray) {
        for (int t : tArray) {
          int heightSum = 0;
          String filename = String.format("N%dt%d_output.csv", N, t);
          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));

          System.out.println("N: " + N + ", t: " + t);
          writer.write("n,N\n"); // Write the column headers

          for (int i = 0; i < t; i++) {
            BST bst = new BST(getRandomizedIntegerArray(N));

            int height = bst.getHeight();

            System.out.println("Tree: " + (i + 1) + ", Height: " + height);
            writer.write(String.format("%d,%d\n", i + 1, height));

            heightSum += height;
          }

          double heightAverage = (double) heightSum / t;

          System.out.println("HeightAverage: " + heightAverage + "\n");
          System.out.flush();
          writer.close();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
