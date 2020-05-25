package rpis82.muhutdinov.oop.model;

import java.util.*;

public class Individual implements Client, Cloneable {

    public Account[] accounts;
    private int size;
    private String name;
    private final int DEFAULT_ELEMENTS = 16;
    private int creditScore;

    public Individual() {
        accounts = new Account[DEFAULT_ELEMENTS];
        size = DEFAULT_ELEMENTS;
    }

    public Individual(String name, int size) {
        accounts = new DebitAccount[size];
        this.size = size;
        this.name = name;
    }

    public Individual(String name, Account[] accounts) {
        this.accounts = accounts;
        size = accounts.length;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) throws NullPointerException {
        Objects.requireNonNull(name, "Name is null");
        this.name = name;
    }

    // Доп. метод Расширить
    private void extendAccounts() {
        Account[] newAccount = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccount, 0, accounts.length);
        accounts = newAccount;
    }

    public boolean add(int index, Account account) throws IndexOutOfBoundsException, NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "Account is null");
        if (index >= this.size && index < 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        if (hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
        accounts[index] = account;
        return true;
    }

    public Account get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size && index < 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        return accounts[index];
    }

    private boolean compareAccountNumber(Account account, String accountNumber) {
        return account != null && account.getNumber().equals(accountNumber);
    }

    public Account get(String accountNumber) throws InvalidAccountNumberException, NullPointerException, NoSuchElementException {
        Objects.requireNonNull(accountNumber, "AccountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (Account account : accounts) {
            if (compareAccountNumber(account, accountNumber))
                return account;
        }
        throw new NoSuchElementException("Number not found");
    }

    public boolean hasAccount(String accountNumber) throws InvalidAccountNumberException, NullPointerException {
        Objects.requireNonNull(accountNumber, "AccountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (Account account : accounts) {
            if (compareAccountNumber(account, accountNumber))
                return true;
        }
        return false;
    }

    public Account set(int index, Account account) throws IndexOutOfBoundsException, NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "Account is null");
        if (index >= this.size && index < 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        if (hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
        Account lostAccount = accounts[index];
        accounts[index] = account;
        return lostAccount;
    }

    public Account remove(int index) throws IndexOutOfBoundsException {
        if (index >= this.size && index < 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Account lostAccount = accounts[index];
        System.arraycopy(accounts, index + 1, accounts, index, accounts.length - 1 - index);
        accounts[accounts.length - 1] = null;
        size--;
        return lostAccount;
    }

    public Account remove(String accountNumber) throws InvalidAccountNumberException, NullPointerException, NoSuchElementException {
        Objects.requireNonNull(accountNumber, "AccountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (int i = 0; i < accounts.length; i++) {
            if (compareAccountNumber(accounts[i], accountNumber)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Number not found");
    }

    public int size() {
        return size;
    }

    public List<Account> sortedAccountByBalance() {
        List<Account> accounts = new ArrayList<>();
        Account[] allAccounts = toArray();
        Arrays.sort(allAccounts);
        Collections.addAll(accounts, allAccounts);
        return accounts;
    }

    public double totalBalance() {
        double sumBalance = 0;
        for (Account account : accounts) {
            if (account != null) {
                sumBalance += account.getBalance();
            }
        }
        return sumBalance;
    }

    @Override
    public int getCreditScores() {
        return creditScore;
    }

    @Override
    public void addCreditScores(int creditScores) {
        creditScore += creditScores;
    }

    @Override
    public Collection<Account> getCreditAccounts() {
        Collection<Account> accounts = new LinkedList<>();
        Account[] allAccounts = toArray();
        for (Account account : allAccounts) {
            if (account.getClass().equals(CreditAccount.class)) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Client");
        stringBuilder.append("\nname: ").append(this.name);
        stringBuilder.append("\ncreditScore: ").append(this.creditScore);
        Account[] accounts = toArray();
        for (Account account : accounts)
            stringBuilder.append("\n").append(account.toString());
        stringBuilder.append("\ntotal: ").append(totalBalance());
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        int xorAccountsHash = 0;
        for (int i = 0; i < size; i++) {
            xorAccountsHash ^= accounts[i].hashCode();
        }
        return xorAccountsHash ^ name.hashCode() ^ Integer.hashCode(creditScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return size == that.size &&
                creditScore == that.creditScore &&
                Arrays.equals(accounts, that.accounts) &&
                Objects.equals(name, that.name);
    }

    @Override
    public Individual clone() throws CloneNotSupportedException {
        return (Individual) super.clone();
    }

    @Override
    public int indexOf(Object account) throws NullPointerException {
        Objects.requireNonNull(account, "Account is null");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    private class AccountIterator implements Iterator<Account> {
        int count = 0;

        @Override
        public boolean hasNext() {
            return size != count;
        }

        @Override
        public Account next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементов больше нет");
            } else {
                return accounts[count++];
            }
        }
    }

    public Iterator<Account> iterator() {
        return new Individual.AccountIterator();
    }

    @Override
    public int compareTo(Client o) {
        return (int) Math.round(totalBalance() - o.totalBalance());
    }

    //laba 7
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        Objects.requireNonNull(object, "входной параметр null");
        for (Account account : this) {
            if (object.equals(account)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Account[] toArray() {
        Account[] returnAccounts = new Account[size];
        System.arraycopy(accounts, 0, returnAccounts, 0, size);
        return returnAccounts;
    }

    @Override
    public <T> T[] toArray(T[] account) {
        return Arrays.copyOf(account, account.length);
    }

    @Override
    public boolean add(Account account) {
        Objects.requireNonNull(account, "входной параметр null");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                size++;
                return true;
            }
        }
        extendAccounts();
        accounts[accounts.length / 2] = account;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        Objects.requireNonNull(object, "входной параметр null");
        int index = indexOf(object);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Account> collection) {

        for (Account account : collection) {
            add(account);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {

        for (Object object : collection) {
            remove(object);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {

        for (Account account : this) {
            if (account != null) {
                if (!collection.contains(account)) {
                    remove(account);
                }
            }
        }
        return true;
    }

    @Override
    public void clear() {
        this.accounts = null;
        this.size = 0;
        this.creditScore = 0;
    }
}