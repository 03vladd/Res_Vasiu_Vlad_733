package Model;

public class OrderEvent {
    private int id;
    private int customerId;
    private int day;
    private OrderEventType type;
    private int points;

    public OrderEvent(int id, int customerId, int day, OrderEventType type, int points) {
        this.id = id;
        this.customerId = customerId;
        this.day = day;
        this.type = type;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public OrderEventType getType() {
        return type;
    }

    public void setType(OrderEventType type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
