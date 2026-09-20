package lw01.prelab;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("jobs.txt"));
        ArrayList<PrintJob> jobs = new ArrayList<>();

        while (input.hasNext()) {
            String type = input.next();
            String id = input.next();
            int pages = input.nextInt();

            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } else if (type.equals("COLOUR")) {
                jobs.add(new ColourPrint(id, pages));
            }
        }
        input.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}
