import java.util.Scanner;

public class BookMyStayUC1{

    public static void main(String[] args) {
        // Display application header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize required components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            // Prompt for User Input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate the input using the domain-specific validator
            validator.validate(guestName, roomType, inventory);

            // If validation passes, process the booking
            bookingQueue.enqueue(guestName, roomType);
            System.out.println("Booking successful for: " + guestName);

        } catch (InvalidBookingException e) {
            // Handle domain-specific validation errors gracefully
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            // Ensure resources are closed
            scanner.close();
        }
    }
}

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class ReservationValidator {

    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        // Check for empty name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Strict case-sensitive check for room types
        if (!(roomType.equals("Single") || roomType.equals("Double") || roomType.equals("Suite"))) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

class RoomInventory {
    // Logic for checking physical room availability would go here
}

class BookingRequestQueue {
    public void enqueue(String name, String type) {
        // Logic for adding to the processing queue would go here
    }
}