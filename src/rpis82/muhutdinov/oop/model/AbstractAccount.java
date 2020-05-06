package rpis82.muhutdinov.oop.model;

public abstract class AbstractAccount implements Account {
    private String number;
    private double balance;
    private final String EMPTY_NUMBER = "";
    private final int EMPTY_BALANCE = 0;

    protected AbstractAccount() {
        number = EMPTY_NUMBER;
        balance = EMPTY_BALANCE;
    }

    protected AbstractAccount(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
