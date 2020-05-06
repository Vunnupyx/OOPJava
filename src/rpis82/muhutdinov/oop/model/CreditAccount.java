package rpis82.muhutdinov.oop.model;

public class CreditAccount extends AbstractAccount implements Credit {
    private double APR;

    public CreditAccount(){
        APR = 30;
    }
    public CreditAccount(String number, double balance, double APR){
        super(number,balance);
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
}
