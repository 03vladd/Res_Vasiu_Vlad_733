package Controller;

import Model.*;
import Repository.ShopRepository;
import Service.ShopService;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;


public class ShopController {
    private ShopRepository repository;
    private ShopService service;

    public ShopController() {
        this.repository = new ShopRepository();
    }

    public void run() throws IOException {
        List<Customer> customers = repository.loadCustomers("data/customers.json");
        List<OrderEvent> events = repository.loadEvents("data/events.json");
        List<Refund> refunds = repository.loadRefunds("data/refunds.json");

        this.service = new ShopService(customers, events, refunds);

        System.out.println("Customers loaded: " + customers.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Refunds loaded: " + refunds.size());
        for (Customer c : customers) {
            System.out.println(c);
        }

        //2. filter nach status und tier
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input tier: ");
        String tier = scanner.nextLine();

        List<Customer> filtered = service.filterByTierAndActive(tier);
        for (Customer c : filtered) {
            System.out.println(c);
        }

        scanner.close();
        //3. sort nach loyaltyLevel and name
        List<Customer> sorted = service.sortCustomers();
        for (Customer c : sorted) {
            System.out.println(c);
        }
    }

}
