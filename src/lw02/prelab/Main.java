package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // 1. LinkedList untuk menyimpan semua transaksi
        LinkedList<String[]> transactions = new LinkedList<>();
        // 2. LinkedList untuk data customer: {name, balance}
        LinkedList<String[]> customers = new LinkedList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+"); // {NAME, TYPE, AMOUNT}
            transactions.add(parts);

            // tambahkan customer hanya saat pertama kali muncul
            if (findCustomer(customers, parts[0]) == null) {
                customers.add(new String[]{parts[0], "0"});
            }
        }
        sc.close();

        // 3. Pindahkan transaksi ke Queue (FIFO)
        Queue<String[]> queue = new LinkedList<>();
        for (String[] t : transactions) {
            queue.offer(t);
        }

        // 4. Stack untuk WITHDRAW yang gagal
        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String[] customer = findCustomer(customers, t[0]);
            int balance = Integer.parseInt(customer[1]);
            int amount = Integer.parseInt(t[2]);

            if (t[1].equals("DEPOSIT")) {
                customer[1] = String.valueOf(balance + amount);
            } else if (t[1].equals("WITHDRAW")) {
                if (amount > balance) {
                    failed.push(t); // saldo tidak cukup
                } else {
                    customer[1] = String.valueOf(balance - amount);
                }
            }
        }

        // 5. Tampilkan hasil
        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] t = failed.pop(); // urutan LIFO
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }

    // Mencari customer berdasarkan nama, null jika belum ada
    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] c : customers) {
            if (c[0].equals(name)) return c;
        }
        return null;
    }
}