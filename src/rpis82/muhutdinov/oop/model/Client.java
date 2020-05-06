package rpis82.muhutdinov.oop.model;

public interface Client {
    boolean add(Account account);

    boolean add(int index, Account account);

    Account get(int index);

    Account get(String accountNumber);

    boolean hasAccount(String accountNumber);

    Account set(int index, Account account);

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
//        for (ClientStatus clientStatus : ClientStatus.values()) {
//            if (clientStatus.getCreditScoreBound() == getCreditScores())
//                return clientStatus;
//        }
//        throw new RuntimeException();

        //или
        //        return Arrays.stream(ClientStatus.values())
        //                .filter(status -> status.getCreditScoreBound() == getCreditScores())
        //                .findFirst().orElseThrow(RuntimeException::new);
    }

    Account[] getCreditAccounts();

}
