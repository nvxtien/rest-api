package com.nvt.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clothes {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int g = sc.nextInt();

        List<Integer> tn = new ArrayList<>();
        List<Integer> clothes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tn.add(sc.nextInt());
        }

        for (int j = 0; j < m; j++) {
            clothes.add(sc.nextInt());
        }

        Integer max = 0;
        for (int i = 1; i < tn.size(); i++) {
            if (tn.get(i) - tn.get(i - 1) > max) {
                max = tn.get(i) - tn.get(i - 1);
            }
        }

        Integer out = 0;

        for (int i = 0; i < clothes.size(); i++) {
            if (clothes.get(i) <= max) {
                out++;
            }
        }

        System.out.print(out);

    }
}
