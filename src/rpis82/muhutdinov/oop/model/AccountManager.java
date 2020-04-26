package rpis82.muhutdinov.oop.model;

import java.time.chrono.MinguoDate;

public class AccountManager {
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
    public boolean add(Client individual) {
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

    public boolean add(int index, Client individual) {
        individuals[index] = individual;
        return true;
    }

    //Получить ссылку
    public Client get(int index) {
        return individuals[index];
    }

    //Изменить ссылку по номеру массива
    public Client set(int index, Client individual) {
        Client lostAccount = individuals[index];
        individuals[index] = individual;
        return lostAccount;
    }

    //Удалить ссылку
    public Client remove(int index) {
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

    public Account getAccount(String accountNumber) {
        for (Client individual : individuals) {
            return individual.get(accountNumber);
        }
        return null;
    }

    public Account removeAccount(String accountNumber) {

        for (Client individual : individuals) {
            return individual.remove(accountNumber);
        }
        return null;
    }

    public Account setAccount(String accountNumber, Account account) {
        for (int i = 0; i < individuals.length; i++) {
            Account[] arrayAccounts = individuals[i].getAccounts();
            for (int j = 0; j < arrayAccounts.length; j++) {
                if (compareAccountNumber(arrayAccounts[j], accountNumber)) {
                    individuals[i].set(j, account);
                    return arrayAccounts[j];
                }
            }
        }
        return null;
    }

    private boolean compareAccountNumber(Account account, String accountNumber) {
        return account != null && account.getNumber().equals(accountNumber);
    }
}
