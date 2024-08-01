package org.example.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task1 {
    Scanner scanner = new Scanner(System.in);

    public Object[] path(int[] arg) {
        int n = arg[0];
        int m = arg[1];
        int i = 1;
        int j = 0;
        int finish = 1;
        List<Integer> finishList = new ArrayList<>();

        int[] mArray = new int[m];
        for(int k = 0; k < m; k++) {
            mArray[k] = k+1;
        }

        finishList.add(1);

        while(mArray[m-1] != finish) {
            int[] temp = new int[m];
            i = mArray[m-1];
            while(j < m) {
                if(i <= n) {
                    temp[j] = i;
                    j++;
                    i++;
                } else {
                    i = 1;
                    temp[j] = i;
                    j++;
                    i++;
                }
            }
            if(temp[m-1] == finish) {
                finishList.add(temp[0]);
                return finishList.toArray();
            }
            mArray[m-1] = temp[m-1];
            finishList.add(temp[0]);
            j = 0;
        }

        return finishList.toArray();
    }

    public int[] arguments() {
        int[] arg = new int[2];
        System.out.print("Введите размерность кругового массива: ");
        String nString = scanner.nextLine();
        System.out.print("Введите размерность длины массива: ");
        String mString = scanner.nextLine();
        int n = Integer.parseInt(nString);
        int m = Integer.parseInt(mString);

        arg[0] = n;
        arg[1] = m;
        return arg;
    }

    public static void main(String[] args) {
        task1 s = new task1();
        int[] arg = s.arguments();
        Object[] res = s.path(arg);
        System.out.print("Длина пути равна: ");
        for(Object i : res) {
            System.out.print(i);
        }
    }
}
