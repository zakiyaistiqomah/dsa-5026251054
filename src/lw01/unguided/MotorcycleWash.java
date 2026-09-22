package lw01.unguided;

public class MotorcycleWash extends WashService {

    MotorcycleWash(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge(){
        int days = super.getDays();
        int charges = 15000;

        days * charges
    }

    @Override
    public String label() {
        return "Motorcycle";
    }
    
}
