import Model.*;
import Repository.ShopRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ShopRepository repository = new ShopRepository();

        var customers = repository.loadCustomers("data/customers.json");
        var events = repository.loadEvents("data/events.json");
        var refunds = repository.loadRefunds("data/refunds.json");

        System.out.println("Customers loaded: " + customers.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Refunds loaded: " + refunds.size());

        System.out.println("\n--- Customers ---");
        for (var c : customers) {
            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getTier() + " | " + c.getStatus() + " | " + c.getLoyaltyLevel());
        }

        System.out.println("\n--- Events (first 5) ---");
        for (int i = 0; i < 5; i++) {
            var e = events.get(i);
            System.out.println(e.getId() + " | customerId=" + e.getCustomerId() + " | day=" + e.getDay() + " | " + e.getType() + " | points=" + e.getPoints());
        }

        System.out.println("\n--- Refunds (first 5) ---");
        for (int i = 0; i < 5; i++) {
            var r = refunds.get(i);
            System.out.println(r.getId() + " | customerId=" + r.getCustomerid() + " | " + r.getReason() + " | amount=" + r.getAmount());
        }
    }
}