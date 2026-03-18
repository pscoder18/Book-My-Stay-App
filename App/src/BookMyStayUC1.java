import java.util.*;

// CLASS: AddOnService
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// CLASS: AddOnServiceManager
class AddOnServiceManager {

    // Key -> Reservation ID
    // Value -> List of services
    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    // Calculate total cost
    public double calculateTotalServiceCost(String reservationId) {

        double total = 0.0;

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (AddOnService service : services) {
                total += service.getCost();
            }
        }

        return total;
    }
}

// MAIN CLASS
public class BookMyStayUC1 {

    public static void main(String[] args) {

        System.out.println("===== Add-On Service Selection =====");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 300);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 700);

        // Add services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, airportPickup);

        // Calculate total
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        // Output
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}