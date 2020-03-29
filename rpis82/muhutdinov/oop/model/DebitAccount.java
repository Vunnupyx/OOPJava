package rpis82.muhutdinov.oop.model;

public class DebitAccount implements Account{
    public String number;
    public double balance;

    public DebitAccount(){
        number = "";
        balance = 0;
    }
    public DebitAccount(String number, double balance){
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
