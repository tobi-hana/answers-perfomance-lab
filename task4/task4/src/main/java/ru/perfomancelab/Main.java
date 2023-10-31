package ru.perfomancelab;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line = reader.readLine();
            while (line != null) {
                nums.add(Integer.parseInt(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double avg = 0;
        for(int i : nums) {
            avg += i;
        }
        if(!nums.isEmpty()) {
            avg = Math.round(avg/nums.size());
        }

        int numberOfSteps = 0;
        for(int i : nums) {
            numberOfSteps += Math.abs(i - avg);
        }
        System.out.println(numberOfSteps);
    }
}