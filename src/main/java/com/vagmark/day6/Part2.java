package com.vagmark.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) {
        File myObj = new File("inputs/day6/input.txt");

        /*
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
         */
        List<String> listOfLines = new ArrayList<>();
        char[] signs = new char[1000];
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith("*") || data.startsWith("+")) {
                    signs = data.replace(" ", "").toCharArray();
                } else {
                    listOfLines.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /*
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
         */
        long beforeTime = System.nanoTime();

        long sumOfSums = 0;
        int signPosition = 0;

        long productOfCol = 1;
        long sumOfCol = 0;

        String line = listOfLines.getFirst();

        for (int k = 0; k < line.length(); k++) {
            int numOfEmptyCols = 0;
            StringBuilder num = new StringBuilder();

            for (String lineToBeChecked : listOfLines) {
                char charToBeChecked = lineToBeChecked.charAt(k);

                if (charToBeChecked == ' ') {
                    numOfEmptyCols++;
                } else {
                    num.append(charToBeChecked);
                }
            }

            if (numOfEmptyCols >= 4) { // change this to 3 for sample
                if (signs[signPosition] == '*') {
                    sumOfSums += productOfCol;
                } else {
                    sumOfSums += sumOfCol;
                }
                sumOfCol = 0;
                productOfCol = 1;
                signPosition++;
                continue;
            }

            long value = Long.parseLong(num.toString().trim());
            if (signs[signPosition] == '*') {
                productOfCol *= value;
            } else if (signs[signPosition] == '+') {
                sumOfCol += value;
            }
        }
        if (signs[signPosition] == '*') {
            sumOfSums = sumOfSums + productOfCol;
        } else {
            sumOfSums = sumOfSums + sumOfCol;
        }
        System.out.println(" sum " + sumOfSums + " Time elapsed:" + (System.nanoTime() - beforeTime) + "ns.");
        //sum 9640641878593 Time elapsed:2363375ns.

    }
}
