package rpis82.muhutdinov.oop.model;


import java.util.LinkedList;

public class Entity implements Client {
    private String name;
    private int size;
    private Node head;
    private Node tail;
    private int creditScore;


    public Entity(String name) {
        this.name = name;
        this.head = null;
        this.tail = null;
        creditScore = 0;
    }

    public Entity(String name, Account[] accounts) {
        this.name = name;
        for (Account account : accounts) {
            addBack(account);
        }
        creditScore = 0;
    }

    //Добавляющий узел в конец списка
    private void addBack(Account account) {
        Node newNode = new Node(null, account);
        Node last = tail;
        tail = newNode;
        if (last == null) {
            head = new Node(newNode, null);
        } else {
            last.next = newNode;
        }
        size++;
    }

    //Добавляющий узел в заданную позицию в списке
    private void addByIndex(int index, Account account) {
        if (index == size) {
            addBack(account);
        } else {
            Node previousLink = linkByIndex(index - 1);
            previousLink.next = new Node(previousLink.next, account);
        }
        size++;
    }

    //возвращающий ссылку на узел по его номеру в списке
    private Node linkByIndex(int index) {
        Node arrayNode = head.next;
        for (int i = 0; i < index; i++) {
            arrayNode = arrayNode.next;
        }
        return arrayNode;
    }

    //удаляющий узел по его номеру в списке
    private void removeNodeByIndex(int index) {
        Node previousLink = linkByIndex(index - 1);
        Node nextLink = linkByIndex(index + 1);
        previousLink.next = new Node(nextLink.next, nextLink.value);
        size--;
    }

    //изменяющий узел с заданным номером
    private void editNode(int index, Account account) {
        Node editLink = linkByIndex(index);
        editLink.value = account;
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean add(Account account) {
        addBack(account);
        return true;
    }

    @Override
    public boolean add(int index, Account account) {
        addByIndex(index, account);
        return true;
    }

    @Override
    public Account get(int index) {
        Node node = linkByIndex(index);
        return node.value;
    }

    @Override
    public Account get(String accountNumber) {
        for (Node x = head.next; x != null; x = x.next) {
            if (x.value.getNumber().equals(accountNumber))
                return x.value;
        }
        return null;
    }

    @Override
    public boolean hasAccount(String accountNumber) {
        for (Node x = head.next; x != null; x = x.next) {
            if (x.value.getNumber().equals(accountNumber))
                return true;
        }
        return false;
    }

    @Override
    public Account set(int index, Account account) {
        Node lostLink = linkByIndex(index);
        editNode(index, account);
        return lostLink.value;
    }

    @Override
    public Account remove(int index) {
        Node lostLink = linkByIndex(index);
        removeNodeByIndex(index);
        return lostLink.value;
    }

    @Override
    public Account remove(String accountNumber) {
        Node link = head.next;
        for (int i = 0; i < size; i++) {
            if (link.value.getNumber().equals(accountNumber)) {
                return remove(i);
            }
            link = link.next;
        }
        return null;
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
        Account copy;
        for (int i = 0; i < returnAccounts.length; i++) {
            for (int j = 0; j < returnAccounts.length - 1; j++) {
                if (returnAccounts[j].getBalance() > returnAccounts[j + 1].getBalance()) {
                    copy = returnAccounts[j + 1];
                    returnAccounts[j + 1] = returnAccounts[j];
                    returnAccounts[j] = copy;
                }
            }
        }
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
        for (Account account : accounts){
            if (account.getClass().equals(CreditAccount.class)) {
                accountsWithNull[count] = account;
                count += 1;
            }
        }
        Account[] accountsWithoutNull = new Account[count];
        System.arraycopy(accountsWithNull, 0, accountsWithoutNull, 0, count);
        return accountsWithoutNull;
    }
}

