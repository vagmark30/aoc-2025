package com.vagmark.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day2/input.txt");
        /*
        Any ID which is made only of some sequence of digits repeated twice.
        So, 55 (5 twice), 6464 (64 twice), and 123123 (123 twice)
        11 22 99 1188511885 38593859

        Add all invalid and we get the code.
         */

        long sumOfInvalids = 0;
        List<String> list = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            String line = myReader.nextLine();
            list.addAll(Arrays.stream(line.split(",")).toList());
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (String s : list) {
            String[] rangeOfNums = s.split("-");
            int sizeOfLeft = rangeOfNums[0].length();
            long numberOfLeft = Long.parseLong(rangeOfNums[0]);
            int sizeOfRight = rangeOfNums[1].length();
            long numberOfRight = Long.parseLong(rangeOfNums[1]);

            // If both nums are of the same size and odd then skip.
            if (sizeOfLeft == sizeOfRight && sizeOfLeft % 2 == 1){
                continue;
            }
            // if both nums are of different size and odd ex 121 and 12321 we need to check from 1000 to 9999
            //if (sizeOfLeft != sizeOfRight && sizeOfLeft % 2 == 1){            }

            for (long numberToBeChecked = numberOfLeft; numberToBeChecked <= numberOfRight; numberToBeChecked++) {
                String stringToBeChecked = String.valueOf(numberToBeChecked);
                if (stringToBeChecked.length() % 2 == 1){
                    continue;
                }
                int half = stringToBeChecked.length() / 2;
                for (int i = 0; i < half ; i++){
                    if(stringToBeChecked.charAt(i) != stringToBeChecked.charAt(half + i)){
                        break;
                    }
                    if (i == half-1){
                        sumOfInvalids += numberToBeChecked;
                    }
                }
            }
        }

        System.out.println(sumOfInvalids);
        //Your puzzle answer was 30608905813.
    }
}
