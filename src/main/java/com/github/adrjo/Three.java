package com.github.adrjo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Three {
    // 1. find numbers and length
    // 2. check around it for chars that aren't a number or a dot

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("C:\\Users\\Adrian\\Desktop\\e\\Code\\Java\\aoc2023\\src\\main\\java\\com\\github\\adrjo\\3").toPath());

//        partOne(lines);

        // get all numbers that have a "*" by them
        // if map.contains(starPosition) -> positionsScores.add(starPosition,1)
        // map.add(starPosition, number)
        Map<PositionData, Integer> nums = new HashMap<>();
        Map<PositionData, Integer> scores = new HashMap<>();


        for (int i = 0; i < lines.size(); ++i) {

            String line = lines.get(i);
            for (int l = 0; l < line.length(); ++l) {
                char c = line.charAt(l);

                if (Character.isDigit(c)) {
                    // look forward until non-digit and combine into single number
                    String num = findDigits("", line, l);
                    int val = Integer.parseInt(num);
                    // look around
                    PositionData part = hasGearPart(lines, num, i, l);
                    if (part.exists()) {
                        nums.put(part, nums.getOrDefault(part, 0) + 1);
                        scores.put(part, scores.getOrDefault(part, 1) * val);
                        System.out.println(num);
                    }

                    // move index forward by the amount at the end
                    l += num.length();
                }
            }
        }

        AtomicInteger tot = new AtomicInteger();
        nums.entrySet().stream().
                filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .forEach(pos -> {
                    tot.addAndGet(scores.get(pos));
                });
        System.out.println(tot.get());
    }

    private static void partOne(List<String> lines) {
        int tot = 0;
        for (int i = 0; i < lines.size(); ++i) {

            String line = lines.get(i);
            for (int l = 0; l < line.length(); ++l) {
                char c = line.charAt(l);

                if (Character.isDigit(c)) {
                    // look forward until non-digit and combine into single number
                    String num = findDigits("", line, l);

                    // look around
                    if (isPart(lines, num, i, l)) {
                        tot += Integer.parseInt(num);
                        System.out.println(num);
                    }

                    // move index forward by the amount at the end
                    l += num.length();
                }
            }
        }
        System.out.println(tot);
    }


    private static PositionData hasGearPart(List<String> lines, String num, int lineIndex, int charIndex) {
        for (int l = -1; l < 2; l++) {
            int lineToSearch = lineIndex + l;
            if (lineToSearch < 0 || lineToSearch > lines.size() - 1) {
                continue;
            }

            String line = lines.get(lineToSearch);
            for (int i = charIndex - 1; i < charIndex + num.length() + 1; ++i) {
                if (i < 0 || i > line.length() - 1) {
                    continue;
                }

                char c = line.charAt(i);
                if (c == '*') {
                    return new PositionData(lineToSearch, i);
                }
            }
        }
        return PositionData.INVALID;
    }

    private static boolean isPart(List<String> lines, String num, int lineIndex, int charIndex) {
        // check -1 lineindex, -1 charIndex
        // go until charIndex + num.length

        // check -1 charIndex and charIndex+num.length

        // check +1 lineIndex, -1 charIndex
        // go until charIndex + num.length

        for (int l = -1; l < 2; l++) {
            int lineToSearch = lineIndex + l;
            if (lineToSearch < 0 || lineToSearch > lines.size() - 1) {
                continue;
            }

            String line = lines.get(lineToSearch);
            for (int i = charIndex - 1; i < charIndex + num.length() + 1; ++i) {
                if (i < 0 || i > line.length() - 1) {
                    continue;
                }

                char c = line.charAt(i);
                if (!(Character.isDigit(c) || c == '.')) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String findDigits(String ans, String line, int l) {
        if (line.length() - 1 < l) return ans;
        char c = line.charAt(l);

        if (!Character.isDigit(c)) {
            return ans;
        }

        return findDigits(ans + c, line, l + 1);
    }
}
