import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private final List<Appointment> appointments;
    private final List<Dermatologist> dermatologists;
    private final List<Invoice> invoices;

    public Clinic() {
        appointments = new ArrayList<>();
        dermatologists = new ArrayList<>();
        invoices = new ArrayList<>();
    }

    // Add an appointment to the list of appointments
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.getDermatologist().bookSlot(appointment.getTime());
    }

    // Add a dermatologist to the list of dermatologists
    public void addDermatologist(Dermatologist dermatologist) {
        dermatologists.add(dermatologist);
    }

    // Add an invoice to the list of invoices
    public List<Invoice> getInvoices() {

        return invoices;

    }

    // Get an appointment by its ID
    public Appointment getAppointmentById(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }

    // Get all appointments
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Get all dermatologists
    public List<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    // Cancel an appointment
    public void cancelAppointment(Appointment appointment) {
        appointment.getDermatologist().releaseSlot(appointment.getTime());
        appointments.remove(appointment);
    }

    // Check if a slot is booked
    public boolean isSlotBooked(Dermatologist dermatologist, String date, String slot) {

        for (Appointment appointment : appointments) {

            if (appointment.getDermatologist().equals(dermatologist) &&

                    appointment.getDate().equals(date) &&

                    appointment.getTime().equals(slot)) {

                return true;

            }

        }

        return false;

    }

    // Search for an appointment by date
    public Appointment searchAppointmentByDate(String date) {

        for (Appointment appointment : appointments) {

            if (appointment.getDate().equals(date)) {

                return appointment;

            }

        }

        return null;

    }

    // Search for an appointment by patient name
    public Appointment searchAppointmentByPatientName(String patientName) {

        for (Appointment appointment : appointments) {

            if (appointment.getPatient().getName().equalsIgnoreCase(patientName)) {

                return appointment;

            }

        }

        return null;

    }

    // Search for an appointment by appointment ID
    public Appointment searchAppointment(String appointmentId) {

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId().equals(appointmentId)) {

                return appointment;

            }

        }

        return null;

    }

}
