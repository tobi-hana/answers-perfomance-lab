package ru.perfomancelab;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float[] circle = new float[3];
        try (FileInputStream in = new FileInputStream(args[0])) {
            Scanner scanner = new Scanner(in);
            for (int i = 0; i < 3; i++) {
                circle[i] = scanner.nextFloat();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<float[]> dots = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[1]))) {
            String line = reader.readLine();
            while(line != null) {
                Scanner scanner = new Scanner(line);
                dots.add(new float[]{scanner.nextFloat(), scanner.nextFloat()});
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(float[] dot: dots) {
            double distanceSquare = Math.pow(dot[0] - circle[0], 2) + Math.pow(dot[1] - circle[1], 2);
            if (distanceSquare == Math.pow(circle[2], 2)){
                System.out.println(0);
            } else if (distanceSquare < Math.pow(circle[2], 2)) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }

    }
}