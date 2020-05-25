package rpis82.muhutdinov.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Individual implements Client, Cloneable {

    private Account[] accounts;
    private int size;
    private String name;
    private final int DEFAULT_ELEMENTS = 16;
    private int creditScore;

    //Конструкторы
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
        Account[] newAccount = new DebitAccount[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccount, 0, accounts.length);
        accounts = newAccount;
    }

    //Добавить ссылку
    @Override
    public boolean add(Account account) throws NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "Account is null");
        if (hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
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

    public boolean add(int index, Account account) throws IndexOutOfBoundsException, NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "Account is null");
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        if (hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
        accounts[index] = account;
        return true;
    }

    //Получить ссылку
    public Account get(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
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


    //Проверить есть ли ссылка с заданным номером
    public boolean hasAccount(String accountNumber) throws InvalidAccountNumberException, NullPointerException {
        Objects.requireNonNull(accountNumber, "AccountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (Account account : accounts) {
            if (compareAccountNumber(account, accountNumber))
                return true;
        }
        return false;
    }

    //Изменить ссылку по номеру массива
    public Account set(int index, Account account) throws IndexOutOfBoundsException, NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "Account is null");
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        if (hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
        Account lostAccount = accounts[index];
        accounts[index] = account;
        return lostAccount;
    }

    //Удалить ссылку
    public Account remove(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
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

    // возвращающий число физ. лиц
    public int size() {
        return size;
    }

    //возвращающий массив счетов (значений null в массиве быть не должно, его размер должен
    //быть равен числу элементов в исходном массиве)
    public Account[] getAccounts() {
        Account[] returnAccounts = new Account[size];
        System.arraycopy(accounts, 0, returnAccounts, 0, size);
        return returnAccounts;
    }

    public Account[] sortedAccountByBalance() {
        Account[] returnAccounts = getAccounts();
        Arrays.sort(returnAccounts);
        return returnAccounts;
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


    //лаб 3
    @Override
    public int getCreditScores() {
        return creditScore;
    }

    @Override
    public void addCreditScores(int creditScores) {
        creditScore += creditScores;
    }

    @Override
    public Account[] getCreditAccounts() {
        Account[] accounts = getAccounts();
        Account[] accountsWithNull = new Account[size];
        int count = 0;
        for (Account account : accounts) {
            if (account.getClass().equals(CreditAccount.class)) {
                accountsWithNull[count] = account;
                count += 1;
            }
        }
        Account[] accountsWithoutNull = new Account[count];
        System.arraycopy(accountsWithNull, 0, accountsWithoutNull, 0, count);
        return accountsWithoutNull;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Client");
        stringBuilder.append("\nname: ").append(this.name);
        stringBuilder.append("\ncreditScore: ").append(this.creditScore);
        Account[] accounts = getAccounts();
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
    public boolean remove(Account account) throws NullPointerException {
        Objects.requireNonNull(account, "Account is null");
        int index = indexOf(account);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Account account) throws NullPointerException {
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
    public Iterator<Account> iterator()
    {
        return new Individual.AccountIterator();
    }

    @Override
    public int compareTo(Client o) {
        return (int) Math.round(totalBalance() - o.totalBalance());
    }
}