package com.nvt.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Clothes {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);

        List<Integer> nmg = Arrays.asList(br.readLine().trim().split(" ")).stream()
                .map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        List<Integer> tn = Arrays.asList(br.readLine().trim().split(" ")).stream()
                .map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        List<Integer> clothes = Arrays.asList(br.readLine().trim().split(" ")).stream()
                .map(x -> Integer.parseInt(x)).collect(Collectors.toList());


        List<Integer> iList = new ArrayList<>();
        for (int i = 1; i < tn.size(); i++) {
            iList.add(tn.get(i) - tn.get(i-1));
        }

        iList = iList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        Map<String, Integer> tnclothes = new HashMap<>();
//        for (int i = 0; i < iList.size(); i++) {
        Integer i = 0;
            Iterator<Integer> iterator = clothes.iterator();
            tnclothes.put("t" + i, 0);
            while (iterator.hasNext()) {
                Integer x = iterator.next();
                if (x <= iList.get(i)) {
                    tnclothes.put("t" + i, tnclothes.get("t" + i) + 1);
                    iterator.remove();
                }
            }
//        }

        Integer G = nmg.get(2);
        Integer res = 0;
        List<Integer> vList = tnclothes.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        /*for (int i = 0; i < vList.size(); i++) {
            if (i + 1 > G) {
                break;
            }
            res = res + vList.get(i);
        }*/
//        System.out.print(res);
          System.out.print(vList.get(0));

    }
}
