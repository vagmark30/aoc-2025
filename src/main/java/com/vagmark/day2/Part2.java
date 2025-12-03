package com.vagmark.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day2/input.txt");

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
            long numberOfLeft = Long.parseLong(rangeOfNums[0]);
            long numberOfRight = Long.parseLong(rangeOfNums[1]);


            for (long numberToBeChecked = numberOfLeft; numberToBeChecked <= numberOfRight; numberToBeChecked++) {
                String stringToBeChecked = String.valueOf(numberToBeChecked);
                boolean invalid = false;
                int sizeOfStringToBeChecked = stringToBeChecked.length();
                int half = stringToBeChecked.length() / 2;

                for (int len = 1; len <= half; len++) {
                    if (sizeOfStringToBeChecked % len == 0) {
                        String pattern = stringToBeChecked.substring(0, len);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < sizeOfStringToBeChecked / len; i++) {
                            sb.append(pattern);
                        }
                        if (sb.toString().equals(stringToBeChecked)) {
                            invalid = true;
                            System.out.println(pattern);
                            break;
                        }
                    }
                }
                if (invalid) {
                    sumOfInvalids += numberToBeChecked;
                }
            }
        }
        System.out.println(sumOfInvalids);
        //Your puzzle answer was 31898925685.
    }
}








