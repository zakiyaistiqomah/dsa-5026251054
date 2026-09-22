package lw01.unguided;

public abstract class WashService implements Billable {

    private String id;
    private int days;

    WashService(String id, int days){
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than 0");
        }

        this.id = id;
        this.days = days;

    }

    String getId() {
        return this.id;
    }

    int getDays() {
        return this.days;
    }

    @Override
    public abstract int calculateCharge();

    int calculateCharge(int units) {
        return units * calculateCharge();
    }

    String label() {
        return "Service";
    }

    String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
