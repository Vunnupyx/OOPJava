package rpis82.muhutdinov.oop.model;

import java.util.Objects;

public class CreditAccount extends AbstractAccount implements Credit, Cloneable {


    private double APR;

    public CreditAccount() {
        APR = 30;
    }

    public CreditAccount(String number, double balance, double APR) {
        super(number, balance);
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
                Objects.equals(getNumber(), that.getNumber());
    }

    @Override
    public CreditAccount clone() throws CloneNotSupportedException {
        //return new CreditAccount(this.getNumber(), this.getBalance(), this.APR);
        return (CreditAccount) super.clone();
    }

}