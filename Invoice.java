
public class Invoice {
    private final String invoiceId;
    private final Appointment appointment;
    private final double totalFee;
    private final double tax;
    private double finalAmount;

    public Invoice(String invoiceId, Appointment appointment) {
        this.invoiceId = invoiceId;
        this.appointment = appointment;
        this.totalFee = appointment.calculateTotalFee();
        this.tax = totalFee * 0.025; // 2.5% tax
        this.finalAmount = Math.ceil(totalFee + tax);
    }

    public Invoice(String invoiceId, Appointment appointment, double totalFee, double tax) {
        this.invoiceId = invoiceId;
        this.appointment = appointment;
        this.totalFee = totalFee;
        this.tax = tax;
        this.finalAmount = totalFee + tax;
    }

    // Generate invoice details
    public void generateInvoiceDetails() {
        System.out.println("-----------------------------");
        System.out.println("-- Aurora Skin Care Clinic --");
        System.out.println("-----------------------------");
        System.out.println("Invoice ID: " + invoiceId);
        System.out.println("Patient Name: " + appointment.getPatient().getName());
        System.out.println("Treatment: " + appointment.getTreatmentType());
        System.out.println("Registration + Treatment Fee: LKR : " + totalFee);
        System.out.println("Tax LKR: " + tax);
        System.out.println("Total Amount: LKR " + finalAmount);
        System.out.println("-----------------------------");
        System.out.println("Thank you for choosing Aurora Skin Care Clinic!");

    }
}
