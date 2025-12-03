package com.vagmark.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day1/input.txt");
        // New password method 0x434C49434B

        int currentPosition = 50;
        int timesClickedZero = 0;

        long beforeTime = System.currentTimeMillis();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                char order = line.charAt(0);
                int stepsToMove = Integer.parseInt(line.substring(1));

                for (int i = 0; i < stepsToMove; i++) {
                    if (order == 'L') {
                        currentPosition = currentPosition - 1;
                        if (currentPosition < 0) {
                            currentPosition = 99;
                        }
                    } else {
                        currentPosition = currentPosition + 1;
                        if (currentPosition > 99) {
                            currentPosition = 0;
                        }
                    }
                    if (currentPosition == 0) {
                        timesClickedZero++;
                    }
                }
            }

            System.out.println("Answer:" + timesClickedZero + ", Time elapsed: " + (System.currentTimeMillis() - beforeTime) + "ms.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
