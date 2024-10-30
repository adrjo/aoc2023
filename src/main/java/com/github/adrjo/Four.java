package com.github.adrjo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Four {
    static Map<Integer, Integer> cardToAmtMap = new HashMap<>();
    static Map<Integer, Long> totalScoreForIndex = new HashMap<>();
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("C:\\Users\\Adrian\\Desktop\\e\\Code\\Java\\aoc2023\\src\\main\\java\\com\\github\\adrjo\\4").toPath());

//        partOne(lines);
        long now = System.currentTimeMillis();
        partTwo(lines);
        System.out.printf("Ran in %dms\n", System.currentTimeMillis() - now);


    }

    private static void partTwo(List<String> lines) {
        for (int i = 0; i < lines.size(); ++i) {
            //get score for each line
            String line = lines.get(i);
            String[] split = line.split(": ")[1].split(" \\| ");

            int score = getScore(split) + 1;
            cardToAmtMap.put(i, score);
        }

        long tot = 0;
        for (int i = lines.size() - 1; i >= 0 ; --i) {
            long score = getScoreForIndex(i);
            totalScoreForIndex.put(i, score);
//            System.out.println(i + "=" + score);
            tot += score;
            // add current card
            tot += 1;
        }

        System.out.println(tot);
    }

    static long getScoreForIndex(int index) {
        if (totalScoreForIndex.get(index) != null) {
            return totalScoreForIndex.get(index);
        }
        if (index > cardToAmtMap.size() - 1) return 0;
        long score = cardToAmtMap.get(index);
        long totScore = score;
        for (int i = index + 1; i < index + score + 1; ++i) {
            totScore += getScoreForIndex(i);
        }
        return totScore;
    }

    private static void getWins(List<String> lines, int from, int to) {
//        System.out.println(index + "=" + wins);
        cardToAmtMap.put(from, cardToAmtMap.getOrDefault(from, 0) + 1);
        for (int i = from; i < to; ++i) {
            if (i > lines.size() - 1) continue;
            String line = lines.get(i);
            String[] split = line.split(": ")[1].split(" \\| ");

            int score = getScore(split) + 1;
            if (score != 0) {
                getWins(lines, from + 1, from + score);
            }
        }
    }

    private static void partOne(List<String> lines) {
        int tot = 0;
        for (String line : lines) {
            String[] split = line.split(": ")[1].split(" \\| ");

            int score = getScore(split);

            if (score != -1) {
                tot += (int) Math.pow(2, score);
            }
        }
        System.out.println(tot);
    }

    private static int getScore(String[] split) {
        Set<Integer> winningNumbers = new HashSet<>();
        for (String win : split[0].split(" ")) {
            if (win.trim().isEmpty()) continue;
            int num = Integer.parseInt(win.trim());
            winningNumbers.add(num);
        }

        int score = -1;
        for (String our : split[1].split(" ")) {
            if (our.trim().isEmpty()) continue;
            int num = Integer.parseInt(our.trim());
            if (winningNumbers.contains(num)) {
                score++;
            }
        }
        return score;
    }
}
