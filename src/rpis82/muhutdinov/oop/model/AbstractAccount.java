package rpis82.muhutdinov.oop.model;

import java.time.LocalDate;
import java.util.Objects;
import java.lang.Cloneable;
import java.time.Period;

public class AbstractAccount implements Account, Cloneable {

    private String number;
    private double balance;
    //private final String EMPTY_NUMBER = "";
    private final int EMPTY_BALANCE = 0;
    private LocalDate creationDate;
    private LocalDate expirationDate;

    protected AbstractAccount(String number, LocalDate expirationDate) throws NullPointerException, InvalidAccountNumberException {
        this.number = InvalidAccountNumberException.NumberException(Objects.requireNonNull(number, "number - is null"));
        this.expirationDate = Objects.requireNonNull(expirationDate, "expirationDate - is null");
        this.creationDate = isDateAcceptable(LocalDate.now());
        balance = EMPTY_BALANCE;
    }

    protected AbstractAccount(String number, double balance, LocalDate creationDate, LocalDate expirationDate) throws NullPointerException, InvalidAccountNumberException{
        this.number = InvalidAccountNumberException.NumberException(Objects.requireNonNull(number, "number - is null"));
        this.balance = balance;
        this.expirationDate = Objects.requireNonNull(expirationDate, "expirationDate - is null");
        this.creationDate = isDateAcceptable(creationDate);
    }
    private LocalDate isDateAcceptable(LocalDate localDate){
        Objects.requireNonNull(localDate, "creationDate is null");
        if (localDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("creationDate of creation from the future");
        }
        if (localDate.isAfter(expirationDate))
            throw new IllegalArgumentException("creationDate later than expirationDate");
        return localDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) throws NullPointerException, InvalidAccountNumberException {
        this.number = InvalidAccountNumberException.NumberException(Objects.requireNonNull(number, "number - is null"));
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("number: <%s> balance: <%s> creationDate: <%s> expirationDate: <%s>", number, balance, creationDate, expirationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAccount that = (AbstractAccount) o;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(number, that.number) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return number.hashCode() * Double.hashCode(balance) * creationDate.hashCode() * expirationDate.hashCode();
    }


    @Override
    protected AbstractAccount clone() throws CloneNotSupportedException {
        //return new AbstractAccount(this.number, this.balance);
        return (AbstractAccount) super.clone();
    }

    //lab5


    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(LocalDate expirationDate) throws NullPointerException {
        if (creationDate.isAfter(expirationDate))
            throw new IllegalArgumentException("creationDate later than expirationDate");
        this.expirationDate = Objects.requireNonNull(expirationDate, "expirationDate - is null");
    }

    @Override
    public int monthesQuantityBeforeExpiration() {
        int roundingOfMonths;
        roundingOfMonths = Period.between(creationDate, expirationDate).getMonths();
        if (Period.between(creationDate, expirationDate).getDays() < 26)
            roundingOfMonths ++;
        return roundingOfMonths;
    }
}