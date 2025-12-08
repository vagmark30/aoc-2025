package com.vagmark.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day6/input.txt");

        /*
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
         */
        List<int[]> listNums = new ArrayList<>();
        char[] signs = new char[1000];
        try (Scanner myReader = new Scanner(myObj)) {

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith("*") || data.startsWith("+")) {
                    signs = data.replace(" ", "").toCharArray();
                } else {
                    int[] nums = Arrays.stream(data.replaceAll("\\s+", " ").trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                    listNums.add(nums);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Total lines of digits: " + listNums.size());

        long beforeTime = System.nanoTime();
        long sumOfSums = 0;
        for (int i = 0; i < signs.length; i++) {
            long sumOfEachLine = 0;
            long productOfEachLine = 1;
            for (int j = 0; j < listNums.size(); j++) {
                if (signs[i] == '+') {
                    sumOfEachLine += listNums.get(j)[i];
                } else {
                    productOfEachLine *= listNums.get(j)[i];
                }
            }
            if (signs[i] == '+') {
                sumOfSums += sumOfEachLine;
            } else {
                sumOfSums += productOfEachLine;
            }
        }
        System.out.println("Sum of sums" + sumOfSums + ", Time elapsed:" + (System.nanoTime() - beforeTime) + "ns.");
        //Your puzzle answer was 6503327062445. 0.000292208 s

    }
}
