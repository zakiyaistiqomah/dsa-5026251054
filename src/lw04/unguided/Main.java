package lw04.unguided;

import java.util.*;

public class Main {

    static void bubbleSort(int[] arr, int from, int to) {
        int n = arr.length;
        for (int i = from; i < to && i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static int binarySearch(int[] arr, int target, int[] steps) {
        int low = 0;
        int high = arr.length - 1;
        steps[0] = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps[0]++;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                high = mid - 1;   
            } else {
                low = mid + 1;    
            }
        }
        return -1;
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = sc.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        sc.close();

        bubbleSort(parcels, 0, 2);

        int[] afterTwoPasses = new int[n];
        for (int i = 0; i < n; i++) {
            afterTwoPasses[i] = parcels[i];
        }

        bubbleSort(parcels, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);

        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        System.out.println("==== Search Results ====");
        int[] steps = new int[1];
        for (int t : targets) {
            int idx = binarySearch(parcels, t, steps);
            if (idx != -1) {
                System.out.println(t + ": FOUND at index " + idx + " (steps: " + steps[0] + ")");
            } else {
                System.out.println(t + ": NOT FOUND (steps: " + steps[0] + ")");
            }
        }
    }
}