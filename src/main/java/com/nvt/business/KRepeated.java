package com.nvt.business;

import java.util.*;
import java.util.stream.Collectors;

public class KRepeated {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<Integer> input = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input.add(sc.nextInt());
        }

        int K = sc.nextInt();

        Integer min = Integer.MAX_VALUE;

        Map<Integer, Long> map = input.parallelStream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

//        Map<Integer, Long> map = new HashMap<>();

        /*for (int i = 0; i < N; i++) {
            int k = input.get(i);
            int v = 0;
            for (int j = 0; j < N; j++) {

                if (k == input.get(j) && k < min) {
                    v++;
                }
                if (v > K) {
                    break;
                }
            }
            if (v == K && k < min) {
                min = k;
            }
        }*/

        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Long value = entry.getValue();
            if (value == K && key < min) {
                min = key;
            }
        }

        System.out.print(min);

    }
}
