package rpis82.muhutdinov.oop.model;

public class Entity implements Client{
    private String name;
    private Node head;
    private Node taile;
    private int size;

    Entity(String name){
        this.name = name;
        head = null;
        taile = null;
    }

    @Override
    public boolean add(Account account) {
        return false;
    }

    @Override
    public boolean add(int index, Account account) {
        return false;
    }

    @Override
    public Account get(int index) {
        return null;
    }

    @Override
    public Account get(String accountNumber) {
        return null;
    }

    @Override
    public boolean hasAccount(String accountNumber) {
        return false;
    }

    @Override
    public Account set(int index, Account account) {
        return null;
    }

    @Override
    public Account remove(int index) {
        return null;
    }

    @Override
    public Account remove(String accountNumber) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Account[] getAccounts() {
        return new Account[0];
    }

    @Override
    public Account[] sortedAccountByBalance() {
        return new Account[0];
    }

    @Override
    public double totalBalance() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    private class Node {
        private Node next;
        private Account value;
        Node(Account value, Node next){
            this.value = value;
            this.next = next;
        }
    }
}

