package rpis82.muhutdinov.oop.model;

public interface Client {
    boolean add(Account account) throws DublicateAccountNumberException;

    boolean add(int index, Account account) throws DublicateAccountNumberException;

    Account get(int index);

    Account get(String accountNumber);

    boolean hasAccount(String accountNumber);

    Account set(int index, Account account) throws DublicateAccountNumberException;

    Account remove(int index);

    Account remove(String accountNumber);

    int size();

    Account[] getAccounts();

    Account[] sortedAccountByBalance();

    double totalBalance();

    String getName();

    void setName(String name);

    //лаб 3
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

    Account[] getCreditAccounts();

    boolean remove(Account account);

    int indexOf(Account account);

    double debtTotal();

}
