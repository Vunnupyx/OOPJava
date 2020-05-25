package rpis82.muhutdinov.oop.model;

import java.util.Collection;
import java.util.List;

public interface Client extends Iterable<Account>, Comparable<Client>, Collection<Account> {
    boolean add(int index, Account account) throws DublicateAccountNumberException;

    Account get(int index);

    Account get(String accountNumber);

    boolean hasAccount(String accountNumber);

    Account set(int index, Account account) throws DublicateAccountNumberException;

    Account remove(int index);

    Account remove(String accountNumber);

    int size();

    Account[] toArray();

    List<Account> sortedAccountByBalance();

    double totalBalance();

    String getName();

    void setName(String name);

    int getCreditScores();

    void addCreditScores(int creditScores);

    default ClientStatus getStatus() {
        int scores = getCreditScores();
        if (scores < -2)
            return ClientStatus.BAD;
        if (scores < 0)
            return ClientStatus.RISKY;
        if (scores < 3)
            return ClientStatus.GOOD;
        if (scores < 5)
            return ClientStatus.GOLD;

        return ClientStatus.PLATINUM;
    }

     Collection<Account> getCreditAccounts();

    int indexOf(Object account);

    default double debtTotal(){
        double sumDebt = 0;
        Collection<Account> accounts = getCreditAccounts();
        for (Account account : accounts)
            sumDebt += account.getBalance();

        return sumDebt;
    }



}
