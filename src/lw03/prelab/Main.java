package lw03.prelab;

import java.util.*;

public class Main {

    static void problem1() {
        List<String> playlist = new ArrayList<>();
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;           
            }
            if (line.startsWith("ADD ")) {
                String song = line.split(" ", 2)[1];
                playlist.add(song);                
            } else if (line.startsWith("INSERT ")) {
                String[] parts = line.split(" ", 3);
                int index = Integer.parseInt(parts[1]);
                String song = parts[2];
                playlist.add(index, song);          
            } else if (line.startsWith("REMOVE ")) {
                String song = line.split(" ", 2)[1];
                playlist.remove(song);              
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;
        Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                continue;
            }

            if (!participants.add(name)) {          
                duplicates++;
            }
        }
        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int no = 1;
        for (String name : participants) {
            System.out.println(no + ". " + name);
            no++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    static void problem3() {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");       
            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + qty);
                } else {
                    stock.put(product, qty);
                }
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= qty) {
                    stock.put(product, stock.get(product) - qty);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

}
