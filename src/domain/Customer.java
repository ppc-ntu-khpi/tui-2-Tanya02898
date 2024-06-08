package domain;

public class Customer {
    private int id;
    private String name;
    private String accountType;
    private double accountBalance;

    public Customer(int id, String name, String accountType, double accountBalance) {
        this.id = id;
        this.name = name;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }
}
