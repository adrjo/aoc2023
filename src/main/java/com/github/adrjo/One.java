package com.github.adrjo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class One {

    private static Map<String, Integer> stringToNumMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        stringToNumMap.put("One", 1);
        stringToNumMap.put("Two", 2);
        stringToNumMap.put("Three", 3);
        stringToNumMap.put("Four", 4);
        stringToNumMap.put("Five", 5);
        stringToNumMap.put("Six", 6);
        stringToNumMap.put("Seven", 7);
        stringToNumMap.put("Eight", 8);
        stringToNumMap.put("Nine", 9);

        List<String> lines = Files.readAllLines(new File("C:\\Users\\Adrian\\Desktop\\e\\Code\\Java\\aoc2023\\src\\main\\java\\com\\github\\adrjo\\1").toPath());
//        partOne(lines);

        AtomicInteger sum = new AtomicInteger();
        lines.forEach(line -> {
            int num = Integer.parseInt(findNumberForwards(line) + findNumberBackWards(line));
            System.out.println(num);
            sum.addAndGet(num);
        });
        System.out.println(sum.get());
    }

    private static String findNumberBackWards(String line){
        StringBuilder builder = new StringBuilder();
        for (int i = line.length()-1; 0 <= i; i--){
            if (Character.isDigit(line.charAt(i))) {
                return String.valueOf(line.charAt(i));
            }
            builder.append(line.charAt(i));
            String backwards = isNumber(builder.toString(), true);
            if (backwards != null) {
                return backwards;
            }
        }
        return "null";
    }

    private static String findNumberForwards(String line){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length(); i++){
            if (Character.isDigit(line.charAt(i))) {
                return String.valueOf(line.charAt(i));
            }
            builder.append(line.charAt(i));
            String forwards = isNumber(builder.toString(), false);
            if (forwards != null) {
                return forwards;
            }
        }
        return "null";
    }

    private static String isNumber(String line, boolean backwards){
        String word = "";
        if (backwards) {
            char ch;
            word = "";
            for (int i = line.length() - 1; 0 <= i; i--) {
                ch = line.charAt(i);
                word = word + ch;
            }
        } else {
            word = line;
        }

        for (Map.Entry<String, Integer> entry : stringToNumMap.entrySet()) {
            if (word.toLowerCase().contains(entry.getKey().toLowerCase())) {
                return String.valueOf(entry.getValue());
            }
        }
        return null;
    }



    private static void partOne(List<String> lines) {
        AtomicInteger tot = new AtomicInteger();
        lines.stream().forEach(line -> {
            String first = forwards(line);
            String last = backwards(line);
            int val = Integer.parseInt(first + last);
            tot.addAndGet(val);
        });

        System.out.println(tot.get());
    }

    private static String forwards(String line) {
        for (int i = 0; i < line.length(); ++i) {
            if (Character.isDigit(line.charAt(i))) {
                return String.valueOf(line.charAt(i));
            }
        }
        return null;
    }

    private static String backwards(String line){
        for (int i = line.length() - 1; 0 <= i; i--){
            if (Character.isDigit(line.charAt(i))){
                return String.valueOf(line.charAt(i));
            }
        }
        return null;
    }
}
