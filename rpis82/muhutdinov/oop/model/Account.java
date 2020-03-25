package rpis82.muhutdinov.oop.model;

public class Account {
    public String number;
    public double balance;

    public Account(){
        number = "";
        balance = 0;
    }
    public Account(String number, double balance){
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
