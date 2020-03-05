package com.nvt.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Monitor {

    public static void main(String[] args) throws Exception {

        List<String> iList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());

        for (int k = 0; k < T; k++) {
            String input = br.readLine().trim();
            iList.add(input);
        }

        for (int k = 0; k < iList.size(); k++) {

            List<Integer> arr = Arrays.asList(iList.get(k).split(" "))
                    .stream()
                    .map(x -> Integer.parseInt(x)).collect(Collectors.toList());
            List<Long> simplify = arr.stream()
                    .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                    .values().stream().collect(Collectors.toList());

            Long max = simplify.get(0);
            Long min = max;
            for (int i = 1; i < simplify.size(); i++) {
                Long z = simplify.get(i);
                if (z > max) {
                    max = z;
                }
                if (z < min) {
                    min = z;
                }
            }
            if (max - min > 0) {
                System.out.println(max - min);
            } else {
                System.out.println(-1);
            }
        }
    }
}
