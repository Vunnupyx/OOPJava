package rpis82.muhutdinov.oop.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AccountManager implements Iterable<Client>{
    public Client[] individuals;
    private int size;

    //Конструкторы
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

    //Добавить ссылку
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

    //Получить ссылку
    public Client get(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        return individuals[index];
    }

    //Изменить ссылку по номеру массива
    public Client set(int index, Client individual) throws IndexOutOfBoundsException, NullPointerException {
        Objects.requireNonNull(individual, "Client is null");
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Client lostAccount = individuals[index];
        individuals[index] = individual;
        return lostAccount;
    }

    //Удалить ссылку
    public Client remove(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index <= 0)
            throw new IndexOutOfBoundsException("Index is not acceptable");
        Client lostAccount = individuals[index];
        System.arraycopy(individuals, index + 1, individuals, index, individuals.length - 1 - index);
        individuals[individuals.length - 1] = null;
        size--;
        return lostAccount;
    }

    // возвращающий число физ. лиц
    public int size() {
        return size;
    }

    //Возвращает массив физ. лиц
    public Client[] getClients() {
        Client[] returnClients = new Individual[size];
        System.arraycopy(individuals, 0, returnClients, 0, size);
        return returnClients;
    }

    public Client[] sortedByBalanceClients() {
        Client[] returnClients = getClients();
        Client copy;
        for (int i = 0; i < returnClients.length; i++) {
            for (int j = 0; j < returnClients.length - 1; j++) {
                if (returnClients[j].totalBalance() > returnClients[j + 1].totalBalance()) {
                    copy = returnClients[j + 1];
                    returnClients[j + 1] = returnClients[j];
                    returnClients[j] = copy;
                }
            }
        }
        return returnClients;
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
            Account[] arrayAccounts = individual.getAccounts();
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
            Account[] arrayAccounts = individual.getAccounts();
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
    public Client[] getDebtors() {
        Client[] clients = getClients();
        Client[] clientsWithNull = new Client[size];
        int count = 0;
        for (Client client : clients) {
            if (client.getCreditAccounts().length != 0) {
                clientsWithNull[count] = client;
                count += 1;
            }
        }
        Client[] clientsWithoutNull = new Client[count];
        System.arraycopy(clientsWithNull, 0, clientsWithoutNull, 0, count);
        return clientsWithoutNull;
    }

    public Client[] getWickedDebtors() {
        Client[] clients = getDebtors();
        Client[] clientsWithNull = new Client[clients.length];
        int count = 0;
        for (Client client : clients) {
            if (client.getStatus() == ClientStatus.BAD) {
                clientsWithNull[count] = client;
                count += 1;
            }
        }
        Client[] clientsWithoutNull = new Client[count];
        System.arraycopy(clientsWithNull, 0, clientsWithoutNull, 0, count);
        return clientsWithoutNull;
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