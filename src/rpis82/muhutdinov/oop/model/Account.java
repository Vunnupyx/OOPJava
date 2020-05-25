package rpis82.muhutdinov.oop.model;

import java.time.LocalDate;

public interface Account extends Comparable<Account>{
    String getNumber();

    void setNumber(String number);

    double getBalance();

    void setBalance(double balance);

    LocalDate getCreationDate();

    LocalDate getExpirationDate();

    void setExpirationDate(LocalDate expirationDate);

    int monthesQuantityBeforeExpiration();
}
