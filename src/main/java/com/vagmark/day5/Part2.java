package com.vagmark.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day5/input.txt");

        List<String> listOfRanges = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            boolean isRange = true;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.isEmpty()) {
                    isRange = false;
                }
                if (isRange) {
                    listOfRanges.add(data);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        long start = System.nanoTime();
        List<String> merged = extracted(listOfRanges);

        long sum = 0;
        for (String range : merged) {
            long left = Long.parseLong(range.split("-")[0]);
            long right = Long.parseLong(range.split("-")[1]);
            sum += (right - left + 1);
        }

        System.out.println("Total fresh IDs: " + sum + " Time elapsed: " + (System.nanoTime() - start) + "ns ");
        //353507173555373 Correct in 0.01868621

    }

    private static List<String> extracted(List<String> ranges) {

        List<String> listOfRanges = new ArrayList<>(ranges);
        boolean somethingChanged;

        do {
            somethingChanged = false;
            List<String> listOfCleanedRanges = new ArrayList<>();

            for (String range : listOfRanges) {
                long left = Long.parseLong(range.split("-")[0]);
                long right = Long.parseLong(range.split("-")[1]);
                boolean somethingMerged = false;

                List<String> tempList = new ArrayList<>();

                for (String cleaned : listOfCleanedRanges) {
                    long cleanedLeft = Long.parseLong(cleaned.split("-")[0]);
                    long cleanedRight = Long.parseLong(cleaned.split("-")[1]);

                    if (cleanedLeft <= right && left <= cleanedRight) {
                        long newLeft = Math.min(cleanedLeft, left);
                        long newRight = Math.max(cleanedRight, right);

                        tempList.add(newLeft + "-" + newRight);
                        listOfCleanedRanges.remove(cleaned);
                        listOfCleanedRanges.addAll(tempList);

                        somethingChanged = true;
                        somethingMerged = true;
                        break;
                    }
                }

                if (!somethingMerged) {
                    listOfCleanedRanges.add(range);
                }
            }

            listOfRanges = listOfCleanedRanges;

        } while (somethingChanged);

        return listOfRanges;
    }

}



