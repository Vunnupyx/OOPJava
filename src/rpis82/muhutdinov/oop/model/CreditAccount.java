package rpis82.muhutdinov.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public class CreditAccount extends AbstractAccount implements Credit, Cloneable {


    private double APR;

    public CreditAccount(String number, LocalDate expirationDate) {
        super(number, expirationDate);
        APR = 30;
    }

    public CreditAccount(String number, double balance, double APR, LocalDate creationDate, LocalDate expirationDate) {
        super(number, balance, creationDate, expirationDate);
        isBalanceAcceptable(balance);
        this.APR = APR;
    }

    @Override
    public double getAPR() {
        return APR;
    }

    @Override
    public void setAPR(double APR) {
        this.APR = APR;
    }

    @Override
    public String toString() {
        return String.format("Credit account - %s APR: <%s>", super.toString(), APR);
    }

    @Override
    public int hashCode() {
        return 71 * super.hashCode() * Double.hashCode(APR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditAccount that = (CreditAccount) o;
        return Double.compare(that.APR, APR) == 0 && Double.compare(that.getBalance(), getBalance()) == 0 &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getCreationDate(), that.getCreationDate()) &&
                Objects.equals(getExpirationDate(), that.getExpirationDate());
    }

    @Override
    public CreditAccount clone() throws CloneNotSupportedException {
        //return new CreditAccount(this.getNumber(), this.getBalance(), this.APR);
        return (CreditAccount) super.clone();
    }

    private void isBalanceAcceptable(double balance) {
        if (balance > 0)
            throw new IllegalArgumentException("balance is not acceptable for CreditAccount");
    }

    @Override
    public double nextPaymentValue() {
        double countOfMonths = monthesQuantityBeforeExpiration();
        double countOfYear =  countOfMonths / 12;
        return getBalance() * (1 + APR / 100 * countOfYear) / countOfMonths;
    }

    @Override
    public LocalDate nextPaymentDate() {
        if (LocalDate.now().getDayOfMonth() < 26)
            return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 25);
        else return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 1, 25);
    }
}