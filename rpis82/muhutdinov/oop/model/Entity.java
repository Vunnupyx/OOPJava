package rpis82.muhutdinov.oop.model;


public class Entity implements Client {
    private String name;
    private Node head;
    private Node tail;
    private int size;

    public Entity(String name) {
        this.name = name;
        this.head = null;
        this.tail = null;
    }

    public Entity(String name, Account[] accounts) {
        this.name = name;
        for (Account account : accounts) {
            addBack(account);
        }
    }

    //Добавляющий узел в конец списка
    public void addBack(Account account) {
        Node node = new Node(null, account);

        if (head == null) {
            head = new Node(node, null);
            tail = node;
        } else {
            tail.next = node;
        }
        size++;
    }

    //Добавляющий узел в заданную позицию в списке
    public void addByIndex(int index, Account account) {
        Node node = new Node(null, account);
        Node arrayNode = head.next;

        for (int i = 0; i <= size - 1; i++) {

            if (i == index) {
                arrayNode = new Node(arrayNode.next, account);
            } else
                arrayNode = arrayNode.next;
        }

    }

    public void printList() {
        Node t = head.next;
        //System.out.println(t.next.value.getNumber());
        while (t != null) {
            System.out.println(t.value.getNumber());
            t = t.next;
        }
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
        return size;
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


}

