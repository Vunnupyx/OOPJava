package rpis82.muhutdinov.oop.model;

import java.util.*;

public class Entity implements Client, Cloneable {
    private String name;
    private int size;
    private Node head;
    private Node tail;
    private int creditScore;

    public Entity(String name) {
        this.name = name;
        this.head = null;
        this.tail = null;
    }

    public Entity(String name, Account[] accounts) throws DublicateAccountNumberException {
        this.name = name;
        for (Account account : accounts) {
            add(account);
        }
    }

    //возвращающий ссылку на узел по его номеру в списке
    private Node linkByIndex(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Node arrayNode = head.next;
        for (int i = 0; i < index; i++) {
            arrayNode = arrayNode.next;
        }
        return arrayNode;
    }

    private class Node {
        Node next;
        Account value;

        Node(Node next, Account value) {
            this.next = next;
            this.value = value;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) throws NullPointerException {
        Objects.requireNonNull(name, "Name is null");
        this.name = name;
    }

    @Override
    public boolean add(Account account) throws NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "account is null");
        if (size > 0 && hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
        Node newNode = new Node(null, account);
        Node last = tail;
        tail = newNode;
        if (last == null) {
            head = new Node(newNode, null);
        } else {
            last.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, Account account) throws IndexOutOfBoundsException, NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "account is null");
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        if (hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
        if (index == size) {
            add(account);
        } else {
            Node previousLink = linkByIndex(index - 1);
            previousLink.next = new Node(previousLink.next, account);
        }
        size++;
        return true;
    }

    @Override
    public Account get(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Node node = linkByIndex(index);
        return node.value;
    }

    @Override
    public Account get(String accountNumber) throws InvalidAccountNumberException, NullPointerException, NoSuchElementException {
        Objects.requireNonNull(accountNumber, "AccountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (Node x = head.next; x != null; x = x.next) {
            if (x.value.getNumber().equals(accountNumber))
                return x.value;
        }
        throw new NoSuchElementException("Number not found");
    }

    @Override
    public boolean hasAccount(String accountNumber) throws InvalidAccountNumberException, NullPointerException {
        Objects.requireNonNull(accountNumber, "accountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (Node x = head.next; x != null; x = x.next) {
            if (x.value.getNumber().equals(accountNumber))
                return true;
        }
        return false;
    }

    @Override
    public Account set(int index, Account account) throws IndexOutOfBoundsException, NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(account, "account is null");
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        if (hasAccount(account.getNumber()))
            throw new DublicateAccountNumberException("Account number exists");
        Node lostLink = linkByIndex(index);
        Node editLink = linkByIndex(index);
        editLink.value = account;
        return lostLink.value;
    }

    @Override
    public Account remove(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Node lostLink = linkByIndex(index);
        Node previousLink = linkByIndex(index - 1);
        Node nextLink = linkByIndex(index + 1);
        previousLink.next = new Node(nextLink.next, nextLink.value);
        size--;
        return lostLink.value;
    }

    @Override
    public Account remove(String accountNumber) throws InvalidAccountNumberException, NullPointerException, NoSuchElementException {
        Objects.requireNonNull(accountNumber, "accountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        Node link = head.next;
        for (int i = 0; i < size; i++) {
            if (link.value.getNumber().equals(accountNumber)) {
                return remove(i);
            }
            link = link.next;
        }
        throw new NoSuchElementException("Number not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Account[] getAccounts() {
        Account[] accounts = new Account[size];
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            accounts[i] = node.value;
            node = node.next;
        }
        return accounts;
    }

    @Override
    public Account[] sortedAccountByBalance() {
        Account[] returnAccounts = getAccounts();
        Arrays.sort(returnAccounts);
        return returnAccounts;
    }

    @Override
    public double totalBalance() {
        double sumBalance = 0;
        for (Node x = head.next; x != null; x = x.next) {
            sumBalance += x.value.getBalance();
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
        Account[] accounts = getAccounts();
        for (int i = 0; i < size; i++) {
            xorAccountsHash ^= accounts[i].hashCode();
        }
        return xorAccountsHash ^ name.hashCode() ^ Integer.hashCode(creditScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity that = (Entity) o;
        return size == that.size &&
                creditScore == that.creditScore &&
                Arrays.equals(getAccounts(), that.getAccounts()) &&
                Objects.equals(name, that.name);
    }

    @Override
    public Entity clone() throws CloneNotSupportedException {
        return (Entity) super.clone();
    }

    @Override
    public boolean remove(Account account) throws NullPointerException {
        Objects.requireNonNull(account, "account is null");
        int index = indexOf(account);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Account account) throws NullPointerException {
        Objects.requireNonNull(account, "account is null");
        Account[] accounts = getAccounts();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    private class AccountIterator implements Iterator<Account> {

        private Node nextItem = head.next;

        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        @Override
        public Account next() {
            if (hasNext()) {
                throw new NoSuchElementException("Элементов больше нет");
            } else {
                Account value = nextItem.value;
                nextItem = nextItem.next;
                return value;
            }
        }
    }
    public Iterator<Account> iterator()
    {
        return new AccountIterator();
    }

    @Override
    public int compareTo(Client o) {
        return (int) Math.round(totalBalance() - o.totalBalance());
    }
}