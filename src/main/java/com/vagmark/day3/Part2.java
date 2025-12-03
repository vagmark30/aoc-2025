package com.vagmark.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day3/input.txt");

        long totalOutputJoltage = 0L;
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
            int remainingDigits = 12;
            for (int i = l.length() - 13; i >= 0; i--) {
                int a = Character.getNumericValue(l.charAt(i));
                if (a >= maxDecadiko) {
                    maxDecadiko = a;
                    positionOfDecadic = i;
                }
            }
            remainingDigits--;

            StringBuilder sb = new StringBuilder();
            sb.append(l.charAt(positionOfDecadic));

            int whereToLook;

            while (remainingDigits > 0) {
                whereToLook = l.length() - remainingDigits;
                positionOfDecadic = findMaxPositionInRange(positionOfDecadic + 1, whereToLook, l);
                sb.append(l.charAt(positionOfDecadic));
                remainingDigits--;
            }
            //System.out.println(sb);
            totalOutputJoltage += Long.parseLong(sb.toString());
        }
        System.out.println("Joltage: " + totalOutputJoltage + ", Time elapsed: " + (System.nanoTime() - beforeTime) + "ms.");//170147128753455
    }

    static int findMaxPositionInRange(int startingpoint, int endingpoint, String some) {
        int max = 0;
        int position = startingpoint;
        for (int i = startingpoint; i <= endingpoint; i++) {
            int a = Character.getNumericValue(some.charAt(i));

            if (a > max) {
                max = a;
                position = i;
            }
        }
        return position;
    }
}
