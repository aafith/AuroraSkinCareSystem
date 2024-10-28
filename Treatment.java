import java.util.HashMap;

public class Treatment {

    // HashMap to store the treatment prices
    private static final HashMap<String, Double> treatmentPrices = new HashMap<>();

    // Static block to initialize the treatment prices
    static {
        treatmentPrices.put("Acne Treatment", 2750.00);
        treatmentPrices.put("Skin Whitening", 7650.00);
        treatmentPrices.put("Mole Removal", 3850.00);
        treatmentPrices.put("Laser Treatment", 12500.00);
    }

    // Method to get the price of a treatment
    public static double getPrice(String treatmentType) {
        return treatmentPrices.getOrDefault(treatmentType, 0.0);
    }
}
