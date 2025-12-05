package com.vagmark.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File myObj = new File("inputs/day4/input.txt");

        List<String> list = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                list.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int n = list.size();
        int m = list.get(0).length();
        char[][] array = new char[n][m];

        for (int i = 0; i < n; i++) {
            var s = list.get(i);
            for (int j = 0; j < m; j++) {
                array[i][j] = s.charAt(j);
                //System.out.print(array[i][j] + " ");
            }
            //System.out.println(i);
        }

        long beforeTime = System.nanoTime();
        int rollsThatCanBeAccessed = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                char currentChar = array[i][j];
                if (currentChar == '.') {
                    continue;
                }

                //System.out.println("Examining character " + currentChar + ", at position " + i + ":" + j);

                int countNearby = 0;
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        if (i + k < 0 || i + k == n) {
                            continue;
                        }
                        if (j + l < 0 || j + l == m) {
                            continue;
                        }
                        char currentCharToBeExamined = array[i + k][j + l];
                        if (currentCharToBeExamined == '@') {
                            countNearby++;
                        }
                    }
                }
                if (countNearby < 4) {
                    //System.out.println(i + "," + j + "," + currentChar);
                    rollsThatCanBeAccessed++;
                }
            }
        }
        System.out.print(rollsThatCanBeAccessed + ", Time elapsed: " + (System.nanoTime() - beforeTime) + "ns."); //Your puzzle answer was 1457.


        //n-1,j-1      n-1,j          n-1, j+1
        //
        //n,j-1        arr[5][5]      n, j+1
        //
        //n+1,j-1      n+1,j          n+1,j+1

    }


}
