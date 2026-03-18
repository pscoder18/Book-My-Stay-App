import java.util.LinkedList;
import java.util.Queue;

/**
 * =====================================================================
 * CLASS - RoomInventory (Thread-Safe)
 * =====================================================================
 */
class RoomInventory {
    private int availableRooms = 5; // Shared mutable state

    /**
     * Synchronized method to ensure room allocation is a critical section.
     * Prevents double-booking and negative inventory.
     */
    public synchronized boolean allocateRoom(String guestName) {
        if (availableRooms > 0) {
            System.out.println("[Thread " + Thread.currentThread().getId() + "] " +
                    "Allocating room for: " + guestName);
            availableRooms--;
            System.out.println("Remaining rooms: " + availableRooms);
            return true;
        } else {
            System.out.println("Booking failed for " + guestName + ": No rooms available.");
            return false;
        }
    }
}

/**
 * =====================================================================
 * CLASS - BookingRequest
 * =====================================================================
 */
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class BookMyStayUC1 {

    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation Started...\n");

        RoomInventory inventory = new RoomInventory();
        Queue<BookingRequest> bookingQueue = new LinkedList<>();

        // Pre-loading the shared queue with requests
        bookingQueue.add(new BookingRequest("Abhishek", "Single"));
        bookingQueue.add(new BookingRequest("John", "Double"));
        bookingQueue.add(new BookingRequest("Sarah", "Suite"));
        bookingQueue.add(new BookingRequest("Emma", "Single"));
        bookingQueue.add(new BookingRequest("Michael", "Double"));
        bookingQueue.add(new BookingRequest("Sophia", "Suite"));

        // Create threads to simulate concurrent guests/processors
        Runnable bookingTask = () -> {
            while (true) {
                BookingRequest request = null;

                // Synchronized block to safely retrieve from the shared queue
                synchronized (bookingQueue) {
                    if (!bookingQueue.isEmpty()) {
                        request = bookingQueue.poll();
                    } else {
                        break; // Queue is empty, exit thread
                    }
                }

                if (request != null) {
                    inventory.allocateRoom(request.guestName);
                }

                // Short sleep to simulate processing time and increase interleaving
                try { Thread.sleep(100); } catch (InterruptedException e) { break; }
            }
        };

        // Initialize and start multiple threads
        Thread t1 = new Thread(bookingTask);
        Thread t2 = new Thread(bookingTask);
        Thread t3 = new Thread(bookingTask);

        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to complete
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\nSimulation completed safely.");
    }
}