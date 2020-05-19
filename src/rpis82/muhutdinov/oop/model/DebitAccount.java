package rpis82.muhutdinov.oop.model;


import java.time.LocalDate;

public class DebitAccount extends AbstractAccount implements Cloneable {

    public DebitAccount(String number, LocalDate expirationDate) {
        super(number, expirationDate);
    }

    public DebitAccount(String number, double balance, LocalDate creationDate, LocalDate expirationDate) {
        super(number, balance, creationDate, expirationDate);
        isBalanceAcceptable(balance);
    }

    @Override
    public String toString() {
        return String.format("Debit account - %s", super.toString());
    }

    @Override
    public int hashCode() {
        return 53 * super.hashCode();
    }


    @Override
    public DebitAccount clone() throws CloneNotSupportedException {
        //return new DebitAccount(this.getNumber(), this.getBalance());
        return (DebitAccount) super.clone();
    }

    private void isBalanceAcceptable(double balance){
        if (balance < 0)
            throw new IllegalArgumentException("balance is not acceptable for DebitAccount");
    }
}