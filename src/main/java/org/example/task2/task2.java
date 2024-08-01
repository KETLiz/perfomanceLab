package org.example.task2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task2 {
    List<Integer> curcleData = new ArrayList<>();
    List<Integer> pointData = new ArrayList<>();
    List<Integer> result = new ArrayList<>();

    public List<Integer> readFromFile(File file) {
        List<Integer> result = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(file))) {
            while (s.hasNext()) {
                result.add(s.nextInt());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Integer> calculation(File circle, File point) {
        int i = 0;
        int j = i+1;
        int pointX = 0;
        int pointY = 0;
        double dist = 0;

        curcleData = readFromFile(circle);
        pointData = readFromFile(point);

        int curcleX = curcleData.get(0);
        int curcleY = curcleData.get(1);
        int curcleR = curcleData.get(2);
        double curcleRSquare = Math.pow(curcleR, 2);

        while(i < pointData.size() && j < pointData.size()) {
            pointX = pointData.get(i);
            pointY = pointData.get(j);
            dist = Math.pow((curcleX-pointX), 2) + Math.pow((curcleY-pointY), 2);
            if(dist == curcleRSquare) {
                result.add(0);
            } else if(dist < curcleRSquare) {
                result.add(1);
            } else {
                result.add(2);
            }
            i = i + 2;
            j = i + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        task2 t = new task2();
        List<Integer> res = t.calculation(new File("src/main/java/org/example/task2/1.txt"),
                new File("src/main/java/org/example/task2/2.txt"));
        for(Integer i : res) {
            System.out.println(i);
        }
    }
}
