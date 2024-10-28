import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Dermatologist extends Person {
    private Set<String> bookedSlots;

    public Dermatologist(String name, String email) {
        this.name = name;
        this.email = email;
        this.bookedSlots = new HashSet<>();
    }

    // Get all available slots for booking
    public String[] getAvailableSlots() {
        String[] allSlots = {
                "10:00 AM", "10:15 AM", "10:30 AM", "10:45 AM",
                "02:00 PM", "02:15 PM", "02:30 PM", "02:45 PM",
                "04:00 PM", "04:15 PM", "04:30 PM", "04:45 PM",
                "09:00 AM", "09:15 AM", "09:30 AM", "09:45 AM"
        };
        ArrayList<String> availableSlots = new ArrayList<>();
        for (String slot : allSlots) {
            if (!bookedSlots.contains(slot)) {
                availableSlots.add(slot);
            }
        }
        return availableSlots.toArray(new String[0]);
    }

    // Book a slot
    public void bookSlot(String time) {
        bookedSlots.add(time);
    }

    // Release a slot
    public void releaseSlot(String time) {
        bookedSlots.remove(time);
    }

    // Dermatologist information
    @Override
    public String getInfo() {
        return "Dermatologist: " + name + ", Email: " + email;
    }
}
