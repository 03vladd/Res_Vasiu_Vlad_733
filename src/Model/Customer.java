package Model;

public class Customer {
    private int id;
    private String name;
    private String tier;
    CustomerStatus status;
    private int loyaltyLevel;

    public Customer(int id, String name, String tier, CustomerStatus status, int loyaltyLevel) {
        this.id = id;
        this.name = name;
        this.tier = tier;
        this.status = status;
        this.loyaltyLevel = loyaltyLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public int getLoyaltyLevel() {
        return loyaltyLevel;
    }

    public void setLoyaltyLevel(int loyaltyLevel) {
        this.loyaltyLevel = loyaltyLevel;
    }

}
