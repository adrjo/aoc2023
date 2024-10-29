package com.github.adrjo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Two {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("C:\\Users\\Adrian\\Desktop\\e\\Code\\Java\\aoc2023\\src\\main\\java\\com\\github\\adrjo\\2").toPath());

//        partOne(lines);

        AtomicInteger tot = new AtomicInteger();
        lines.forEach(line -> {
            String[] sets = line.split(": ")[1].split("; ");

            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;


            for (String set : sets) {
                String[] balls = set.split(", ");

                for (String ball : balls) {
                    String[] split = ball.split(" ");
                    int amt = Integer.parseInt(split[0]);
                    String color = split[1];
                    if (color.equals("green")){
                        if (amt > maxGreen) {
                            maxGreen = amt;
                        }
                    } else if (color.equals("red")){
                        if (amt > maxRed){
                            maxRed = amt;
                        }
                    } else if (color.equals("blue")){
                        if (amt > maxBlue){
                            maxBlue = amt;
                        }
                    }
                }
            }

            int power = maxRed * maxGreen * maxBlue;
            tot.addAndGet(power);
        });

        System.out.println(tot.get());
    }

    private static void partOne(List<String> lines) {
        AtomicInteger tot = new AtomicInteger();
        lines.forEach(line -> {
            int id = Integer.parseInt(line.split("Game ")[1].split(":")[0]);

            String[] sets = line.split(": ")[1].split("; ");

            for (String set : sets) {
                String[] balls = set.split(", ");

                for (String ball : balls) {
                    String[] split = ball.split(" ");
                    int amt = Integer.parseInt(split[0]);
                    String color = split[1];
                    switch (color){
                        case "green":
                            if (amt > 13) {
                                return;
                            }
                            break;
                        case "red":
                            if (amt > 12) {
                                return;
                            }
                            break;
                        case "blue":
                            if (amt > 14) {
                                return;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            tot.addAndGet(id);
            System.out.println(id + " " + Arrays.toString(sets));
        });

        System.out.println(tot.get());
    }
}
