import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a new clinic object
        Clinic clinic = new Clinic();

        // Create two dermatologist objects
        Dermatologist dermatologist1 = new Dermatologist("Dr. Fernando", "Fernando@gmail.com");
        Dermatologist dermatologist2 = new Dermatologist("Dr. Perera", "Perera@gmail.com");

        clinic.addDermatologist(dermatologist1);
        clinic.addDermatologist(dermatologist2);

        boolean running = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                System.out.println("----------------------------------------");
                System.out.println("-- Welcome to Aurora Skin Care Clinic --");
                System.out.println("----------------------------------------");
                System.out.println("1. Book an Appointment");
                System.out.println("2. View Appointment");
                System.out.println("3. Update Appointment");
                System.out.println("4. Generate Invoice");
                System.out.println("5. View All Dermatologists");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> bookAppointment(clinic, scanner);
                    case 2 -> viewAppointment(clinic, scanner);
                    case 3 -> updateAppointment(clinic, scanner, dermatologist1, dermatologist2);
                    case 4 -> generateInvoice(clinic, scanner);
                    case 5 -> viewAllPersons(clinic.getDermatologists()); // Polymorphic method call
                    case 6 -> {
                        running = false;
                        System.out.println("Exiting the system. Thank you for using Aurora Skin Care Clinic!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }

                System.out.println();
            }
        }
    }

    // Polymorphic method to view all persons
    private static void viewAllPersons(List<? extends Person> persons) {
        for (Person person : persons) {
            System.out.println(person.getInfo()); // Polymorphic method call
        }
    }

    // Book an appointment
    private static void bookAppointment(Clinic clinic, Scanner scanner) {
        System.out.print("Enter NIC: ");
        String nic = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Telephone: ");
        String telephone = scanner.nextLine();

        // New patient
        Patient patient = new Patient(nic, name, email, telephone);

        System.out.println("Select a Dermatologist:");
        for (int i = 0; i < clinic.getDermatologists().size(); i++) {
            System.out.println((i + 1) + ". " + clinic.getDermatologists().get(i).getName());
        }
        System.out.print("Choose a dermatologist (1 or 2): ");
        int dermChoice = scanner.nextInt();
        scanner.nextLine();

        Dermatologist selectedDermatologist = clinic.getDermatologists().get(dermChoice - 1);

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.println("Available slots for " + selectedDermatologist.getName() + " on " + date + ":");
        for (String slot : selectedDermatologist.getAvailableSlots()) {
            if (!clinic.isSlotBooked(selectedDermatologist, date, slot)) {
                System.out.println(slot);
            }
        }
        System.out.print("Enter Time (e.g., 10:00 AM): ");
        String time = scanner.nextLine();

        // Select treatment type
        System.out.println("Available Treatments:");
        System.out.println("1. Acne Treatment");
        System.out.println("2. Skin Whitening");
        System.out.println("3. Mole Removal");
        System.out.println("4. Laser Treatment");
        System.out.print("Select Treatment Type (1-4): ");
        int treatmentChoice = scanner.nextInt();
        scanner.nextLine();

        String treatmentType;
        switch (treatmentChoice) {
            case 1 -> treatmentType = "Acne Treatment";
            case 2 -> treatmentType = "Skin Whitening";
            case 3 -> treatmentType = "Mole Removal";
            case 4 -> treatmentType = "Laser Treatment";
            default -> {
                System.out.println("Invalid treatment choice. Defaulting to Acne Treatment.");
                treatmentType = "Acne Treatment";
            }
        }

        // Generate a new appointment ID
        String appointmentId = "A" + (clinic.getAppointments().size() + 1);
        Appointment appointment = new Appointment(appointmentId, date, time, selectedDermatologist, patient,
                treatmentType);

        clinic.addAppointment(appointment);
        patient.bookAppointment(appointment);

        System.out.println("Appointment booked successfully with ID: " + appointmentId);
    }

    // View an appointment
    private static void viewAppointment(Clinic clinic, Scanner scanner) {
        System.out.println("Search Appointment by:");
        System.out.println("1. Date");
        System.out.println("2. Patient Name");
        System.out.println("3. Appointment ID");
        System.out.println("4. View All Appointments");
        System.out.print("Choose an option (1-4): ");
        int searchOption = scanner.nextInt();
        scanner.nextLine();

        switch (searchOption) {
            case 1 -> {
                System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                String searchDate = scanner.nextLine();
                Appointment foundByDate = clinic.searchAppointmentByDate(searchDate);

                if (foundByDate != null) {
                    printAppointmentDetails(foundByDate);
                } else {
                    System.out.println("No appointment found for the given date.");
                }
            }
            case 2 -> {
                System.out.print("Enter Patient Name: ");
                String searchName = scanner.nextLine();
                Appointment foundByName = clinic.searchAppointmentByPatientName(searchName);

                if (foundByName != null) {
                    printAppointmentDetails(foundByName);
                } else {
                    System.out.println("No appointment found for the given patient name.");
                }
            }
            case 3 -> {
                System.out.print("Enter Appointment ID: ");
                String searchId = scanner.nextLine();
                Appointment foundById = clinic.searchAppointment(searchId);

                if (foundById != null) {
                    printAppointmentDetails(foundById);
                } else {
                    System.out.println("No appointment found for the given ID.");
                }
            }
            case 4 -> {
                List<Appointment> allAppointments = clinic.getAppointments();
                if (allAppointments.isEmpty()) {
                    System.out.println("No appointments found.");
                } else {
                    for (Appointment appointment : allAppointments) {
                        printAppointmentDetails(appointment);
                    }
                }
            }
            default -> System.out.println("Invalid search option.");
        }
    }

    // Update an appointment
    private static void updateAppointment(Clinic clinic, Scanner scanner, Dermatologist dermatologist1,
            Dermatologist dermatologist2) {
        System.out.print("Enter Appointment ID to update: ");
        String updateId = scanner.nextLine();
        Appointment appointmentToUpdate = clinic.getAppointmentById(updateId);

        if (appointmentToUpdate != null) {
            System.out.println("What would you like to update?");
            System.out.println("1. Date");
            System.out.println("2. Time");
            System.out.println("3. Dermatologist");
            System.out.println("4. Treatment Type");
            System.out.print("Choose an option (1-4): ");
            int updateChoice = scanner.nextInt();
            scanner.nextLine();

            switch (updateChoice) {
                case 1 -> {
                    System.out.print("Enter new Appointment Date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    appointmentToUpdate.setDate(newDate);
                    System.out.println("Appointment date updated successfully.");
                }
                case 2 -> {
                    System.out.print("Enter new Appointment Time (e.g., 10:00 AM): ");
                    String newTime = scanner.nextLine();
                    appointmentToUpdate.setTime(newTime);
                    System.out.println("Appointment time updated successfully.");
                }
                case 3 -> {
                    System.out.println("Select new Dermatologist:");
                    System.out.println("1. Dr. Fernando");
                    System.out.println("2. Dr. Perera");
                    System.out.print("Choose a dermatologist (1 or 2): ");
                    int newDermChoice = scanner.nextInt();
                    scanner.nextLine();
                    Dermatologist newDermatologist = newDermChoice == 1 ? dermatologist1 : dermatologist2;
                    appointmentToUpdate.setDermatologist(newDermatologist);
                    System.out.println("Dermatologist updated successfully.");
                }
                case 4 -> {
                    System.out.println("Available Treatments:");
                    System.out.println("1. Acne Treatment");
                    System.out.println("2. Skin Whitening");
                    System.out.println("3. Mole Removal");
                    System.out.println("4. Laser Treatment");
                    System.out.print("Select Treatment Type (1-4): ");
                    int newTreatmentChoice = scanner.nextInt();
                    scanner.nextLine();
                    String newTreatmentType;
                    switch (newTreatmentChoice) {
                        case 1 -> newTreatmentType = "Acne Treatment";
                        case 2 -> newTreatmentType = "Skin Whitening";
                        case 3 -> newTreatmentType = "Mole Removal";
                        case 4 -> newTreatmentType = "Laser Treatment";
                        default -> {
                            System.out.println("Invalid treatment choice. Defaulting to Acne Treatment.");
                            newTreatmentType = "Acne Treatment";
                        }
                    }
                    appointmentToUpdate.setTreatmentType(newTreatmentType);
                    System.out.println("Treatment type updated successfully.");
                }
                default -> System.out.println("Invalid update option.");
            }
        } else {
            System.out.println("No appointment found with the given ID.");
        }
    }

    // Generate an invoice
    private static void generateInvoice(Clinic clinic, Scanner scanner) {
        System.out.print("Enter Appointment ID to generate invoice: ");
        String appointmentId = scanner.nextLine();
        Appointment appointment = clinic.getAppointmentById(appointmentId);

        if (appointment != null) {
            double totalFee = appointment.calculateTotalFee();
            double tax = totalFee * 0.025;

            String invoiceId = "INV00" + (clinic.getInvoices().size() + 1);

            Invoice invoice = new Invoice(invoiceId, appointment, totalFee, tax);

            invoice.generateInvoiceDetails();
        } else {
            System.out.println("No appointment found with the given ID.");
        }
    }

    // Print appointment details
    private static void printAppointmentDetails(Appointment appointment) {
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        System.out.println("Patient Name: " + appointment.getPatient().getName());
        System.out.println("Dermatologist: " + appointment.getDermatologist().getName());
        System.out.println("Date: " + appointment.getDate());
        System.out.println("Time: " + appointment.getTime());
        System.out.println("Treatment Type: " + appointment.getTreatmentType());
        System.out.println("--------------------------------");
    }
}
