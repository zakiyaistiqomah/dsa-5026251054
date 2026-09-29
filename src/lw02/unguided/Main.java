package lw02.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        while (scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();
            orders.add(order);
        }
        scanner.close();

        LinkedList<String[]> foodStok = new LinkedList<>();
        foodStok.add(new String[]{"Bakso", "2"});
        foodStok.add(new String[]{"Sate", "1"});
        foodStok.add(new String[]{"Soto", "2"});

        LinkedList<String[]> drinkStok = new LinkedList<>();
        drinkStok.add(new String[]{"EsTeh", "4"});
        drinkStok.add(new String[]{"EsJeruk", "2"});

        LinkedList<String[]> successOrders = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(orders);

        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];

            String[] foodItem = null;
            String[] drinkItem = null;
            boolean available = true;

            if (!food.equals("-")) {
                for (String[] item : foodStok) {
                    if (item[0].equals(food)) {
                        foodItem = item;
                        break;
                    }
                }
                if (foodItem == null || Integer.parseInt(foodItem[1]) < 1) {
                    available = false;
                }
            }

            if (!drink.equals("-")) {
                for (String[] item : drinkStok) {
                    if (item[0].equals(drink)) {
                        drinkItem = item;
                        break;
                    }
                }
                if (drinkItem == null || Integer.parseInt(drinkItem[1]) < 1) {
                    available = false;
                }
            }

            if (available) {
                if (foodItem != null) {
                    int stock = Integer.parseInt(foodItem[1]);
                    foodItem[1] = String.valueOf(stock - 1);
                }
                if (drinkItem != null) {
                    int stock = Integer.parseInt(drinkItem[1]);
                    drinkItem[1] = String.valueOf(stock - 1);
                }
                successOrders.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] item : foodStok) {
            System.out.println(item[0] + " : " + item[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] item : drinkStok) {
            System.out.println(item[0] + " : " + item[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}