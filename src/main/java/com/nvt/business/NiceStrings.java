package com.nvt.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class NiceStrings {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine().trim());

        List<String> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(br.readLine().trim());
        }

        System.out.println(0);
        for (int i = 1; i < arr.size(); i++) {
            Integer out = 0;
            String x = arr.get(i);
            for (int j = i-1; j >=0 ; j--) {
                if (arr.get(j).compareTo(x) < 0) {
                    out++;
                }
            }
            System.out.println(out);
        }
    }
}
