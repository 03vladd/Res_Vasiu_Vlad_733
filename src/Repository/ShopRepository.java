package Repository;

import Model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopRepository {

    public List<Customer> loadCustomers(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        List<Customer> list = new ArrayList<>();

        Pattern p = Pattern.compile(
                "\"id\"\\s*:\\s*(\\d+).*?" +
                        "\"name\"\\s*:\\s*\"([^\"]+)\".*?" +
                        "\"tier\"\\s*:\\s*\"([^\"]+)\".*?" +
                        "\"status\"\\s*:\\s*\"([^\"]+)\".*?" +
                        "\"loyaltyLevel\"\\s*:\\s*(\\d+)",
                Pattern.DOTALL
        );

        Matcher m = p.matcher(content);
        while (m.find()) {
            list.add(new Customer(
                    Integer.parseInt(m.group(1)),
                    m.group(2),
                    m.group(3),
                    CustomerStatus.valueOf(m.group(4)),
                    Integer.parseInt(m.group(5))
            ));
        }
        return list;
    }

    public List<OrderEvent> loadEvents(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        List<OrderEvent> list = new ArrayList<>();

        Pattern p = Pattern.compile(
                "\"id\"\\s*:\\s*(\\d+).*?" +
                        "\"customerId\"\\s*:\\s*(\\d+).*?" +
                        "\"day\"\\s*:\\s*(\\d+).*?" +
                        "\"type\"\\s*:\\s*\"([^\"]+)\".*?" +
                        "\"points\"\\s*:\\s*(-?\\d+)",
                Pattern.DOTALL
        );

        Matcher m = p.matcher(content);
        while (m.find()) {
            list.add(new OrderEvent(
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)),
                    OrderEventType.valueOf(m.group(4)),
                    Integer.parseInt(m.group(5))
            ));
        }
        return list;
    }

    public List<Refund> loadRefunds(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        List<Refund> list = new ArrayList<>();

        Pattern p = Pattern.compile(
                "\"id\"\\s*:\\s*(\\d+).*?" +
                        "\"customerId\"\\s*:\\s*(\\d+).*?" +
                        "\"reason\"\\s*:\\s*\"([^\"]+)\".*?" +
                        "\"amount\"\\s*:\\s*(\\d+)",
                Pattern.DOTALL
        );

        Matcher m = p.matcher(content);
        while (m.find()) {
            list.add(new Refund(
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    RefundReason.valueOf(m.group(3)),
                    Integer.parseInt(m.group(4))
            ));
        }
        return list;
    }

    public void writeLinesToFile(String filePath, List<String> lines) throws IOException {
        try (PrintWriter pw = new PrintWriter(filePath)) {
            for (String line : lines) {
                pw.println(line);
            }
        }
    }
}