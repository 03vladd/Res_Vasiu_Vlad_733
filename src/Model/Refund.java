package Model;

public class Refund {
    private int id;
    private int customerid;
    RefundReason reason;
    int amount;

    public Refund(int id, int customerid, RefundReason reason, int amount) {
        this.id = id;
        this.customerid = customerid;
        this.reason = reason;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public RefundReason getReason() {
        return reason;
    }

    public void setReason(RefundReason reason) {
        this.reason = reason;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
