import java.util.ArrayList;

public class Patient extends Person {
    private final String nic;
    private final String telephone;
    private final ArrayList<Appointment> appointments;

    public Patient(String nic, String name, String email, String telephone) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.appointments = new ArrayList<>();
    }

    // Add appointment to the patient
    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Remove appointment from the patient
    public void cancelAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    // Get all appointments of the patient
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    // Get all appointments of the patient as a string
    @Override
    public String getInfo() {
        return "Patient: " + name + ", NIC: " + nic + ", Email: " + email + ", Telephone: " + telephone;
    }

    public String getNic() {
        return nic;
    }

    public String getTelephone() {
        return telephone;
    }
}
