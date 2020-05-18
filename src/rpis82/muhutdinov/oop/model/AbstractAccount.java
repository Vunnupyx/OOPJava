package rpis82.muhutdinov.oop.model;

import java.util.Objects;
import java.lang.Cloneable;
public class AbstractAccount implements Account, Cloneable {

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

    @Override
    public String toString() {
        return String.format("number: <%s> balance: <%s>", number, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAccount that = (AbstractAccount) o;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode() * Double.hashCode(balance);
    }


    @Override
    protected AbstractAccount clone() throws CloneNotSupportedException {
        //return new AbstractAccount(this.number, this.balance);
        return (AbstractAccount) super.clone();
    }
}