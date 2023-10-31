package ru.perfomancelab.task1;

public class MainApp1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = i + 1;
        }
        System.out.print(arr[0]);
        int j = 1;
        int position = ((m - 1) * j) % n;

        while(position != 0){
            System.out.print(arr[position]);
            j++;
            position = ((m - 1) * j) % n;
        }

    }
}
