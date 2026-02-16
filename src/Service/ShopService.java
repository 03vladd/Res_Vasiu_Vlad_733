package Service;

import Model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ShopService {
    private List<Customer> customers;
    private List<OrderEvent> events;
    private List<Refund> refunds;

    public ShopService(List<Customer> customers, List<OrderEvent> events, List<Refund> refunds) {
        this.customers = customers;
        this.events = events;
        this.refunds = refunds;
    }

    public List<Customer> filterByTierAndActive(String tier) {
        return customers.stream()
                .filter(c -> c.getTier().equals(tier) && c.getStatus() == CustomerStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    public List<Customer> sortCustomers() {
        return customers.stream()
                .sorted(Comparator.comparingInt(Customer::getLoyaltyLevel).reversed()
                        .thenComparing(Customer::getName))
                .collect(Collectors.toList());
    }

    public List<String> computeFirst5Events() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 5 && i < events.size(); i++) {
            OrderEvent e = events.get(i);
            result.add("Event " + e.getId()
                    + " -> raw=" + e.getPoints()
                    + " -> computed=" + e.computePoints());
        }
        return result;
    }


}