import java.util.Scanner;

/**
 * =====================================================================
 * CLASS - InvalidBookingException
 * =====================================================================
 * This custom exception represents domain-specific invalid booking
 * scenarios. Using a custom exception makes error handling clearer.
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * =====================================================================
 * CLASS - RoomInventory
 * =====================================================================
 * Manages hotel room availability and prevents negative inventory.
 */
class RoomInventory {
    // Logic for tracking available rooms would be implemented here
}

/**
 * =====================================================================
 * CLASS - BookingRequestQueue
 * =====================================================================
 * Handles the queuing of validated booking requests.
 */
class BookingRequestQueue {
    public void addRequest(String guestName, String roomType) {
        // Process the valid booking request
    }
}

/**
 * =====================================================================
 * CLASS - ReservationValidator
 * =====================================================================
 * Centralizes validation rules to avoid duplication and inconsistency.
 */
class ReservationValidator {
    /**
     * Validates booking input.
     * @throws InvalidBookingException if validation rules are violated.
     */
    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        // Rule 1: Guest name cannot be empty
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Rule 2: Room type must match allowed values exactly (Case Sensitive)
        if (!(roomType.equals("Single") || roomType.equals("Double") || roomType.equals("Suite"))) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Rule 3: Additional checks for inventory state could be added here
    }
}

public class BookMyStayUC1 {

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        // Display application header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize required components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            // Step 1: Accept user input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Step 2: Validate input centrally (Fail-Fast)
            validator.validate(guestName, roomType, inventory);

            // Step 3: If valid, proceed with booking
            bookingQueue.addRequest(guestName, roomType);
            System.out.println("Booking processed successfully for " + guestName);

        } catch (InvalidBookingException e) {
            // Step 4: Handle domain-specific validation errors gracefully
            System.out.println("Booking failed: " + e.getMessage());
        } catch (Exception e) {
            // Handle unexpected system errors
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Step 5: Ensure system resources are released
            scanner.close();
        }
    }
}