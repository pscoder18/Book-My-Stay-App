import java.util.HashMap;
import java.util.Map;

// Inventory Class
class RoomInventory {
    private HashMap<String, Integer> inventory;

    // Constructor - initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Add room type with count
    public void addRoom(String type, int count) {
        inventory.put(type, count);
    }

    // Get availability
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    // Update availability
    public void updateAvailability(String type, int newCount) {
        if (inventory.containsKey(type)) {
            inventory.put(type, newCount);
        } else {
            System.out.println("Room type not found!");
        }
    }

    // Display all inventory
    public void displayInventory() {
        System.out.println("===== Room Inventory =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}

// Main Class
public class BookMyStayUC1 {
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");
        System.out.println("Version: 3.0\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Add room types
        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 3);
        inventory.addRoom("Suite Room", 2);

        // Display inventory
        inventory.displayInventory();

        // Update availability
        System.out.println("\nUpdating Single Room availability...");
        inventory.updateAvailability("Single Room", 4);

        // Check updated value
        System.out.println("Single Room Available: " +
                inventory.getAvailability("Single Room"));
    }
}