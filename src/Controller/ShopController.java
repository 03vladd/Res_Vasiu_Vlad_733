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

        //.4 reverse order from 3 and save in txt file
        List<String> reversedLines = new ArrayList<>();
        for (int i = sorted.size() - 1; i >= 0; i--) {
            reversedLines.add(sorted.get(i).toString());
        }
        repository.writeLinesToFile("customers_sorted.txt", reversedLines);

        //5. computed the point totals for the first 5 events
        List<String> computedLines = service.computeFirst5Events();
        for (String line : computedLines) {
            System.out.println(line);
        }

        //6. totalscore for customer and top 5
        List<String> rankingLines = service.computeTop5Ranking();
        for (String line : rankingLines) {
            System.out.println(line);
        }

        //7. final report for shop
        List<String> reportLines = service.getEventCountReport();
        repository.writeLinesToFile("shop_report.txt", reportLines);
    }


}
