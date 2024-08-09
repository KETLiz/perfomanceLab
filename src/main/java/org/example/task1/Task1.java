package org.example.task1;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public List<Integer> solution(String[] inputArr) {
        if(inputArr.length != 2) {
            throw new ArrayIndexOutOfBoundsException("Введите именно 2 числа!");
        }
        int i = 0;
        int j = i+1;
        int n = Integer.parseInt(inputArr[0]);
        int m = Integer.parseInt(inputArr[1]);
        int finish = 1;
        int[] mArr = new int[m];
        List<Integer> res = new ArrayList<>();

        while(mArr[m-1] != finish) {
            while(i < mArr.length) {
                if(j <= n) {
                    mArr[i] = j;
                    i++;
                    j++;
                } else {
                    j = 1;
                }
            }
            res.add(mArr[0]);
            if(mArr[m-1] == finish) {
                return res;
            }
            mArr[0] = mArr[m-1];
            i = 1;
            j = mArr[0]+1;
        }
        return res;
    }

    public static void main(String[] args) {
        Task1 t = new Task1();
        List<Integer> r = t.solution(args);
        System.out.print("Полученный путь: ");
        for(int i = 0; i < r.size(); i++) {
            System.out.print(r.get(i));
        }
    }
}

