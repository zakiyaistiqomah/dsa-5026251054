package lw01.unguided;

public class CarWash extends WashService {
    
    CarWash(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge(){
        int days = super.getDays();
        int charges = 35000;

        if (days > 3) {
            days -= 3;
            charges += ((3 * 35000) + (days * 25000));
        } else {
            charges += days * 35000;
        }
        return charges;
    }

    @Override
    public String label() {
        return "Car";
    }
}
