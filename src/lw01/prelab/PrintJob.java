package lw01.prelab;

public abstract class PrintJob implements Chargeable {

    String id;
    int pages;

    PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.pages = pages;
    }

    String getId() {
        return this.id;
    }

    int getPages() {
        return this.pages;
    }

    @Override
    public abstract int calculateCharge();

    int calculateCharge(int copies) {
        return copies * calculateCharge();
    }

    String label() {
        return "Print";
    }

    String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
