package com.vagmark.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day5/input.txt");

        List<String> listOfRanges = new ArrayList<>();
        List<String> listOfNums = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            boolean isRange = true;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.isEmpty()) {
                    isRange = false;
                }
                if (isRange) {
                    listOfRanges.add(data);
                } else {
                    listOfNums.add(data);
                }
            }
            listOfNums.removeFirst();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Number of ranges: " + listOfRanges.size());
        System.out.println("Number of nums: " + listOfNums.size());
        long beforeTime = System.nanoTime();
        int freshCounter = 0;
        for (String num : listOfNums) {
            long numToBeChecked = Long.parseLong(num);

            for (String range : listOfRanges) {
                long left = Long.parseLong(range.split("-")[0]);
                long right = Long.parseLong(range.split("-")[1]);
                if (numToBeChecked >= left && numToBeChecked <= right) {
                    freshCounter++;
                    break;
                }
            }
        }
        System.out.println("Fresh counter: " + freshCounter + ", Time elapsed: " + (System.nanoTime() - beforeTime) + "ns.");
        //Fresh counter: 623, Time elapsed: 41788375ns.
    }
}
