package com.vagmark.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2Incorrect {
    public static void main(String[] args) {

        File myObj = new File("inputs/day1/input.txt");

        //In this approach tried a cheeky way so i won't need to handle negative values and just calculate the the times clicked zero.
        // Started from 10k and also thought there was no need to reset current position counter
        int currentPosition = 10050;
        int timesClickedZero = 0;

        long beforeTime = System.currentTimeMillis();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                char order = line.charAt(0);
                int stepsToMove = Integer.parseInt(line.substring(1));
                int lastPosition = currentPosition;

                if (order == 'L') {
                    currentPosition -= stepsToMove;
                } else {
                    currentPosition += stepsToMove;
                }

                int hundredsLast = lastPosition / 100;
                int hundredsCurrent = currentPosition / 100;
                if (hundredsLast != hundredsCurrent) {
                    int diff = Math.abs(hundredsLast - hundredsCurrent);

                    if (currentPosition % 100 == 0 || lastPosition % 100 == 0) {
                        diff -= 1;
                    }
                    timesClickedZero += diff;
                }

                if (currentPosition == 0 || Math.abs(currentPosition) % 100 == 0) {
                    timesClickedZero++;
                }

            }
            System.out.println("Answer:" + timesClickedZero + ", Time elapsed in ms:" + (System.currentTimeMillis() - beforeTime));
            //Times from zero: 6434 -- Wrong answer way too low.
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
