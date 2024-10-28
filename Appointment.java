public class Appointment {
    private final String appointmentId;
    private String date;
    private String time;
    private Dermatologist dermatologist;
    private Patient patient;
    private String treatmentType;
    private double registrationFee;
    private double totalFee;

    public Appointment(String appointmentId, String date, String time, Dermatologist dermatologist, Patient patient,
            String treatmentType) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.dermatologist = dermatologist;
        this.patient = patient;
        this.treatmentType = treatmentType;
        this.registrationFee = 500.0;
    }

    public double calculateTotalFee() {
        double treatmentPrice = Treatment.getPrice(treatmentType);
        double tax = treatmentPrice * 0.025; // 2.5% tax
        totalFee = registrationFee + treatmentPrice + tax;
        return Math.ceil(totalFee);
    }

    // Getters for appointment details
    public String getAppointmentId() {
        return appointmentId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getTreatmentType() {
        return treatmentType;
    }


    // Setters for appointment details
    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }
}
