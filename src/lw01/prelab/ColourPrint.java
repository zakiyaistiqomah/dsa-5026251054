package lw01.prelab;

public class ColourPrint extends PrintJob {

    ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = super.getPages();
        int charges = 2000;

        if (pages > 10) {
            pages -= 10;
            charges += ((10 * 1500) + (pages * 1000));
        } else {
            charges += pages * 1500;
        }
        return charges;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
