package rpis82.muhutdinov.oop.model;

public class Individual implements Client {
    public Account[] accounts;
    public int size;
    public String name;
    private final int DEFAULT_ELEMENTS = 16;

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


    public void setName(String name) {
        this.name = name;
    }

    // Доп. метод Расширить
    private void extendAccounts() {
        Account[] newAccount = new DebitAccount[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccount, 0, accounts.length);
        accounts = newAccount;
    }

    //Добавить ссылку
    public boolean add(Account account) {
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

    public boolean add(int index, Account account) {
        accounts[index] = account;
        return true;
    }

    //Получить ссылку
    public Account get(int index) {
        return accounts[index];
    }

    private boolean compareAccountNumber(Account account, String accountNumber) {
        return account != null && account.getNumber().equals(accountNumber);
    }

    public Account get(String accountNumber) {
        for (Account account : accounts) {
            if (compareAccountNumber(account, accountNumber))
                return account;
        }
        return null;
    }


    //Проверить есть ли ссылка с заданным номером
    public boolean hasAccount(String accountNumber) {
        for (Account account : accounts) {
            if (compareAccountNumber(account, accountNumber))
                return true;
        }
        return false;
    }

    //Изменить ссылку по номеру массива
    public Account set(int index, Account account) {
        Account lostAccount = accounts[index];
        accounts[index] = account;
        return lostAccount;
    }

    //Удалить ссылку
    public Account remove(int index) {
        Account lostAccount = accounts[index];
        System.arraycopy(accounts, index + 1, accounts, index, accounts.length - 1 - index);
        accounts[accounts.length - 1] = null;
        size--;
        return lostAccount;
    }

    public Account remove(String accountNumber) {
        for (int i = 0; i < accounts.length; i++) {
            if (compareAccountNumber(accounts[i], accountNumber)) {
                return remove(i);
            }
        }
        return null;
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

    public double totalBalance() {
        double sumBalance = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                sumBalance += accounts[i].getBalance();
            }
        }
        return sumBalance;
    }
}