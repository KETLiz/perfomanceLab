package org.example.task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task4 {

    public int[] readFromFile(String fileName) {
        List<Integer> nums = new ArrayList<>();

        String fullFileName = "src/main/java/org/example/task4/" + fileName;
        File file = new File(fullFileName);

        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextInt()) {
                nums.add(sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла " + fileName);
        }

        int[] resArr = new int[nums.size()];
        for(int i = 0; i < resArr.length; i++) {
            resArr[i] = nums.get(i);
        }

        return resArr;
    }

    public int minCountSteps(int[] arr) {
        int average = 0;
        int i = 0;
        int count = 0;

        Arrays.sort(arr);
        average = arr[arr.length/2];

        while(i < arr.length) {
            if(arr[i] == average) {
                if(i == arr.length-1) {
                    return count;
                }
                i++;
            }
            while(arr[i] != average) {
                if(arr[i] < average) {
                    arr[i]++;
                    count++;
                } else if(arr[i] == average) {
                    i++;
                } else {
                    arr[i]--;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task4 task4 = new Task4();

        System.out.print("Введите имя файла: ");
        String fileName = sc.nextLine();
        int[] arr = task4.readFromFile(fileName);
        int resultSteps = task4.minCountSteps(arr);
        System.out.println("Минимальное количество шагов для приведения элементов" +
                " к одному числу равно: " + resultSteps);
    }
}
