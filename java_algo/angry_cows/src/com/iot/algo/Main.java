package com.iot.algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private int angryCows = 0;

    public static void main(String[] args) {



        Arrays.sort(sections);

        int leftIndex = 0;
        int rightIndex = sections[sections.length - 1];
        int midIndex;
        int result = 1;
        while (leftIndex <= rightIndex) {
            midIndex = (leftIndex + rightIndex) / 2;

            if (checkDistance(sections, midIndex, angryCows)) {
                result = midIndex;
                leftIndex = midIndex + 1;
            } else {
                rightIndex = midIndex - 1;
            }
        }
        System.out.println("min distance: " + result);
    }

    private static int[] inputValues() {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(",");
        int sectionSize = Integer.parseInt(line[0]);
        angryCows = Integer.parseInt(line[1]); //3
        int[] sections = new int[sectionSize];
        for(int i = 0; i < sectionSize; i++) {
            sections[i] = in.nextInt(); //1,2,8,4,9
        }
    }

    private static boolean checkDistance(int[] sections, int midIndex, int angryCows) {
        int lastPosition = sections[0];
        int cowPlaced = 1;
        for (int i = 1; i < sections.length; i++) {
            if (sections[i] - lastPosition >= midIndex) {
                cowPlaced++;
                lastPosition = sections[i];
                if (cowPlaced == angryCows) {
                    return true;
                }
            }
        }
        return false;
    }


}
