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

    public List<String> computeTop5Ranking() {
        //total score per customer
        Map<Integer, Integer> eventScores = new HashMap<>();
        for (OrderEvent e : events) {
            eventScores.merge(e.getCustomerId(), e.computePoints(), Integer::sum);
        }

        //total refund amount per customer
        Map<Integer, Integer> refundAmounts = new HashMap<>();
        for (Refund r : refunds) {
            refundAmounts.merge(r.getCustomerid(), r.getAmount(), Integer::sum);
        }

        //combine for final calculation
        List<Map.Entry<Customer, Integer>> ranking = new ArrayList<>();
        for (Customer c : customers) {
            int total = eventScores.getOrDefault(c.getId(), 0)
                    - refundAmounts.getOrDefault(c.getId(), 0);
            ranking.add(Map.entry(c, total));
        }

        //sort absteigend by totalscore and if equal aufsteigend after name
        ranking.sort(
                Comparator.<Map.Entry<Customer, Integer>, Integer>comparing(Map.Entry::getValue).reversed()
                        .thenComparing(e -> e.getKey().getName())
        );

        //final result
        List<String> result = new ArrayList<>();
        result.add("Top 5 Customers:");
        for (int i = 0; i < 5 && i < ranking.size(); i++) {
            Map.Entry<Customer, Integer> entry = ranking.get(i);
            result.add((i + 1) + ". " + entry.getKey().getName() + " -> " + entry.getValue());
        }
        return result;

    }


}