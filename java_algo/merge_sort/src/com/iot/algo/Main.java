package com.iot.algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static void selectionSortByWeight(List<Ladder> ladders) {
        int comparisonCount = 0;
        int swapCount = 0;
        Ladder tmp = null;
        for (int step = 0; step < ladders.size(); step++) {
            int maxIndex = step;
            Ladder maxWeightObj = ladders.get(step);
            for (int j = step + 1; j < ladders.size(); j++) {
                comparisonCount++;
                if (maxWeightObj.getWeight() > ladders.get(j).getWeight()) {

                    maxWeightObj = ladders.get(j);
                    maxIndex = j;
                }
            }

            if(step != maxIndex) {
                swapCount++;
                Collections.swap(ladders, step, maxIndex);
            }

        }
        System.out.println("Comparison count: " + comparisonCount);
        System.out.println("Swap count: " + swapCount);
    }

    private static List<Ladder> mergeSortByLength(List<Ladder> ladders) {
        if(ladders.size() <= 1) {
            return ladders;
        }

        int mid = ladders.size() / 2;

        List<Ladder> leftList = ladders.subList(0, mid);
        List<Ladder> rightList =  ladders.subList(mid, ladders.size());

        leftList = mergeSortByLength(leftList);
        rightList = mergeSortByLength(rightList);

        List<Ladder> resultList;
        resultList = merge(leftList, rightList);

        return resultList;
    }


    public static int comparisonCount = 0;

    public static List<Ladder> merge (List<Ladder> leftList, List<Ladder> rightList) {
           List<Ladder> resultList = new ArrayList<>();
           int
                   leftIndex = 0,
                   rightIndex = 0;
           while (leftIndex < leftList.size() || rightIndex < rightList.size()) {

               if(leftIndex < leftList.size() && rightIndex < rightList.size()) {
                   comparisonCount++;
                   if(leftList.get(leftIndex).getLength() > rightList.get(rightIndex).getLength()) {

                       resultList.add(leftList.get(leftIndex++));
                   } else {
                       resultList.add(rightList.get(rightIndex++));
                   }
               } else if (leftIndex < leftList.size()) {
                   resultList.add(leftList.get(leftIndex++));
               } else if (rightIndex < rightList.size()) {
                   resultList.add(rightList.get(rightIndex++));
               }
           }
           return resultList;
     }

    public static void main(String[] args) {
	    String path = "C:\\Users\\taras\\IdeaProjects\\lab1\\ladder.csv";
	    List<Ladder> ladders = new ArrayList<>();
	    try(BufferedReader br = new BufferedReader(new FileReader(path))) {
	        String line = br.readLine();
            while ((line =  br.readLine()) != null) {
                String[] items = line.split(",");
                Ladder ladder = new Ladder();
                ladder.setBrand(items[0]);
                ladder.setLength(Integer.parseInt(items[1]));
                ladder.setWeight(Integer.parseInt(items[2]));
                ladder.setMaterial(items[3]);
                ladders.add(ladder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nUnsorted list:");
        ladders.forEach(System.out::println);

        System.out.println("\nSelection sort by weight:");
        System.out.println("Time complexity: O(n^2)");
        selectionSortByWeight(ladders);
        ladders.forEach(System.out::println);

        System.out.println("\nMerge sort by length: ");
        System.out.println("Time complexity: O(n log n)");

        List<Ladder> mergeSortResult = mergeSortByLength(ladders);
        System.out.println("Comparison count: " + comparisonCount);
        mergeSortResult.forEach(System.out::println);
    }
}
