package com.vagmark.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day1/input.txt");

        int currentPosition = 50;
        int timesStopedZero = 0;

        long beforeTime = System.currentTimeMillis();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                char order =  line.charAt(0);
                int stepsToMove = Integer.parseInt(line.substring(1));

                if (order == 'L'){
                    currentPosition -= stepsToMove;
                } else {
                    currentPosition += stepsToMove;
                }

                if (currentPosition == 0 || Math.abs(currentPosition) % 100 == 0) {
                    timesStopedZero++;
                }
                //System.out.println("Order:" + order + ", Quantity:" + stepsToMove+ ", Current position:" +  currentPosition + ", Times from zero: " + timesStopedZero );
                //Order:R, Quantity:48, Current position:-10641, Times from zero: 1081 -- Correct answer
            }
            System.out.println("Answer:" + timesStopedZero + ", Time elapsed: " + (System.currentTimeMillis() - beforeTime) + "ms.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
