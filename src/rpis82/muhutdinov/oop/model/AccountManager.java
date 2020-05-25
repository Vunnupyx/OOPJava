package rpis82.muhutdinov.oop.model;

import java.util.*;

public class AccountManager implements Iterable<Client>{
    public Client[] individuals;
    private int size;

    public AccountManager(int size) {
        individuals = new Individual[size];
        this.size = size;
    }

    public AccountManager(Client[] individuals) {
        this.individuals = individuals;
        size = individuals.length;
    }

    // Доп. метод Расширить
    public void extendAccounts() {
        Client[] newAccount = new Individual[individuals.length * 2];
        System.arraycopy(individuals, 0, newAccount, 0, individuals.length);
        individuals = newAccount;
    }

    public boolean add(Client individual) throws NullPointerException {
        Objects.requireNonNull(individual, "Client is null");
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i] == null) {
                individuals[i] = individual;
                size++;
                return true;
            }
        }
        extendAccounts();
        individuals[individuals.length / 2] = individual;
        size++;
        return true;
    }

    public boolean add(int index, Client individual) throws IndexOutOfBoundsException, NullPointerException {
        Objects.requireNonNull(individual, "Client is null");
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        individuals[index] = individual;
        return true;
    }

    public Client get(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        return individuals[index];
    }

    public Client set(int index, Client individual) throws IndexOutOfBoundsException, NullPointerException {
        Objects.requireNonNull(individual, "Client is null");
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Client lostAccount = individuals[index];
        individuals[index] = individual;
        return lostAccount;
    }

    public Client remove(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Client lostAccount = individuals[index];
        System.arraycopy(individuals, index + 1, individuals, index, individuals.length - 1 - index);
        individuals[individuals.length - 1] = null;
        size--;
        return lostAccount;
    }

    public int size() {
        return size;
    }

    public Client[] getClients() {
        Client[] returnClients = new Client[size];
        System.arraycopy(individuals, 0, returnClients, 0, size);
        return returnClients;
    }

    public List<Client> sortedByBalanceClients() {
        List<Client> clients = new ArrayList<>();
        Client[] allClients = getClients();
        Arrays.sort(allClients);
        Collections.addAll(clients, allClients);
        return clients;
    }

    public Account getAccount(String accountNumber) throws InvalidAccountNumberException, NullPointerException {
        Objects.requireNonNull(accountNumber, "accountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (Client individual : individuals) {
            return individual.get(accountNumber);
        }
        throw new NoSuchElementException("Number not found");
    }

    public Account removeAccount(String accountNumber) throws InvalidAccountNumberException, NullPointerException {
        Objects.requireNonNull(accountNumber, "accountNumber is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        for (Client individual : individuals) {
            return individual.remove(accountNumber);
        }
        throw new NoSuchElementException("Number not found");
    }

    public Account setAccount(String accountNumber, Account account) throws InvalidAccountNumberException, NullPointerException, DublicateAccountNumberException {
        Objects.requireNonNull(accountNumber, "accountNumber is null");
        Objects.requireNonNull(account, "account is null");
        InvalidAccountNumberException.NumberException(accountNumber);
        if (isAccountNumberAlready(account))
            throw new DublicateAccountNumberException("Account Number already");
        for (Client individual : individuals) {
            Account[] arrayAccounts = individual.toArray();
            for (int j = 0; j < arrayAccounts.length; j++) {
                if (compareAccountNumber(arrayAccounts[j], accountNumber)) {
                    individual.set(j, account);
                    return arrayAccounts[j];
                }
            }
        }
        throw new NoSuchElementException("Number not found");
    }

    private boolean isAccountNumberAlready(Account account) {
        for (Client individual : individuals) {
            Account[] arrayAccounts = individual.toArray();
            for (Account arrayAccount : arrayAccounts) {
                if (arrayAccount.getNumber().equals(account.getNumber())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean compareAccountNumber(Account account, String accountNumber) {
        return account != null && account.getNumber().equals(accountNumber);
    }

    //лаб 3
    public Set<Client> getDebtors() {
        Set<Client> clients = new HashSet<>();
        Client[] allClients = getClients();
        for (Client client : allClients) {
            if (client.getCreditAccounts().size() != 0) {
                clients.add(client);
            }
        }
        return clients;
    }

    public Set<Client> getWickedDebtors() {
        Set<Client> clients = new HashSet<>();
        Set<Client> allClients = getDebtors();
        for (Client client : allClients) {
            if (client.getStatus() == ClientStatus.BAD) {
                clients.add(client);
            }
        }
        return clients;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Client[] clients = getClients();
        for (Client client : clients)
            stringBuilder.append(client.toString()).append("\n");
        return stringBuilder.toString();
    }

    public boolean remove(Client client) throws NullPointerException {
        Objects.requireNonNull(client, "client is null");
        int index = indexOf(client);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public int indexOf(Client client) throws NullPointerException {
        Objects.requireNonNull(client, "client is null");
        Client[] clients = getClients();
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].equals(client)) {
                return i;
            }
        }
        return -1;
    }

    private class AccountIterator implements Iterator<Client> {
        int count = 0;

        @Override
        public boolean hasNext() {
            return size != count;
        }

        @Override
        public Client next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементов больше нет");
            } else {
                return individuals[count++];
            }
        }
    }
    public Iterator<Client> iterator()
    {
        return new AccountManager.AccountIterator();
    }
}