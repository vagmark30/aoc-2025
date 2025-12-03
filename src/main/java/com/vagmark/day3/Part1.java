package com.vagmark.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day3/input.txt");

        long totalOutputJoltage = 0;
        List<String> list = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                list.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //find max that is not on the last position and grab the position and then find the max on that substring

        long beforeTime = System.nanoTime();
        for (String l : list) {
            int positionOfDecadic = 0;
            int maxDecadiko = 0;
            int maxArithmos = 0;
            StringBuilder builder = new StringBuilder();
            for (int i = l.length() - 2; i >= 0; i--) {
                int a = Character.getNumericValue(l.charAt(i));
                if (a >= maxDecadiko) {
                    maxDecadiko = a;
                    positionOfDecadic = i;
                    //System.out.println(maxDecadiko + " at position" + positionOfDecadic);
                }
            }
            for (int i = l.length() - 1; i > positionOfDecadic; i--) {
                int a = Character.getNumericValue(l.charAt(i));
                if (a > maxArithmos) {
                    maxArithmos = a;
                }
            }
            //System.out.println(maxDecadiko + "" + maxArithmos);
            int aa = maxDecadiko * 10 + maxArithmos;
            totalOutputJoltage += aa;
        }
        System.out.println("Joltage: " + totalOutputJoltage + ", Time elapsed: " + (System.nanoTime() - beforeTime) + "ms.");//170147128753455

        //Your puzzle answer was 17092.
    }
}
