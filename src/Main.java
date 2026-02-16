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

        for (var c : customers) {
            System.out.println(c);
        }
    }
}